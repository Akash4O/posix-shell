import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input, typeSubString;
        String[] commands = {"echo", "exit", "type", "pwd", "cd" , "cat" , "ls"};
        String pwd = Paths.get("").toAbsolutePath().toString();

        while (true) {
            System.out.print("$ ");
            input = scanner.nextLine();

            if (input.equals("exit 0")) {
                break;
            } else if (input.startsWith("echo")) {
                String content = input.substring(4).trim();

                if (content.startsWith("\"")) {
                    Pattern pattern = Pattern.compile("\"(.*?)\"");
                    Matcher matcher = pattern.matcher(content);

                    StringBuilder concatenatedContent = new StringBuilder();
                    int count = 0;
                    while (matcher.find()) {
                        String match = matcher.group(1);

                        
                        
                        if (count>0) {
                            concatenatedContent.append(" "+match);
                        }else{
                            concatenatedContent.append(match);
                            count++;
                        }
                    }
                content = concatenatedContent.toString();
                }else
                if ((content.startsWith("'") && content.endsWith("'")) || (content.startsWith("\"") && content.endsWith("\""))) {
                    content = content.substring(1, content.length() - 1);
                } else {
                    content = content.replaceAll("\\s+", " ");
                }
                System.out.println(content);
            } else if (input.startsWith("type")) {
                typeSubString = input.substring(5);
                if (Arrays.asList(commands).contains(typeSubString)) {
                    System.out.println(typeSubString + " is a shell builtin");
                } else {
                    String path = getPath(typeSubString.trim());
                    if (path != null) {
                        System.out.println(typeSubString + " is " + path);
                    } else {
                        System.out.println(typeSubString + ": not found");
                    }
                }
            } else if (input.equals("pwd")) {
                System.out.println(pwd);
            } else if (input.startsWith("cd")) {
                String dir = input.substring(3).trim();

                if (dir.startsWith("./")) {
                    String cdir = input.substring(4);
                    dir = pwd + cdir;
                    if (Files.isDirectory(Path.of(dir)) && Files.exists(Path.of(dir))) {
                        pwd = dir;
                    } else {
                        System.out.printf("cd: %s: No such file or directory%n", dir);
                    }

                } else if (dir.startsWith("../")) {
                    Path newPath = Path.of(pwd).resolve(dir).normalize();
                    if (Files.isDirectory(Path.of(dir)) && Files.exists(Path.of(dir))) {
                        pwd = newPath.toString();
                    } else {
                        System.out.printf("cd: %s: No such file or directory%n", dir);
                    }
                } else if (dir.equals("..")) {
                    Path parentPath = Path.of(pwd).getParent();
                    if (parentPath != null) {
                        pwd = parentPath.toString();
                    }
                } else if (dir.equals("~")) {
                    String userHome = System.getProperty("user.home");
                    if (System.getenv("HOME") != null) {
                        userHome = System.getenv("HOME");
                    }
                    pwd = userHome;
                } else {
                    if (Files.isDirectory(Path.of(dir)) && Files.exists(Path.of(dir))) {
                        pwd = dir;
                    } else {
                        System.out.printf("cd: %s: No such file or directory%n", dir);
                    }
                }

            } else if (input.startsWith("cat")) {
                String[] argsArray = input.substring(4).trim().split(" ");
                for (String fileName : argsArray) {
                    if ((fileName.startsWith("'") && fileName.endsWith("'") )||(fileName.startsWith("\"")&&fileName.endsWith("\""))) {
                        fileName = fileName.substring(1, fileName.length() - 1);
                    }
                    Path filePath = Paths.get(pwd, fileName.trim());

                    if (Files.exists(filePath)) {
                        try {
                            System.out.println("\n\n"+fileName+": \n\n");
                            Files.lines(filePath).forEach(System.out::println);
                        } catch (IOException e) {
                            System.out.println("cat: " + fileName + ": Error reading file");
                        }
                    } else {
                        System.out.println("cat: " + fileName + ": No such file");
                    }
                }
                
            }else if (input.startsWith("ls")||input.equals("ls")) {
                File directory = new File(pwd);
                File[] files = directory.listFiles();
                int count = 0;
                if (files!=null) {
                    for(File file : files){
                        if (file.isDirectory()) {
                            System.out.printf("/"+file.getName()+"\t");    
                        }else{
                            System.out.printf(file.getName()+"\t\t");
                        }
                        count++;
                        if (count%4==0) {
                            System.out.println();
                            System.out.println();
                        }
                        
                    }
                }
                System.out.println();
            }
            
            else {
                String command = input.split(" ")[0];

                String path = getPath(command);

                if (path == null) {
                    System.out.printf("%s: command not found%n", command);
                } else {
                    String fullPath = path + input.substring(command.length());

                    Process p = Runtime.getRuntime().exec(fullPath.split(" "));

                    p.getInputStream().transferTo(System.out);
                }
            }

        }
    }

    private static String getPath(String parameter) {
        for (String path : System.getenv("PATH").split(":")) {
            Path fullPath = Path.of(path, parameter);
            if (Files.isRegularFile(fullPath)) {
                return fullPath.toString();
            }
        }
        return null;
    }
}

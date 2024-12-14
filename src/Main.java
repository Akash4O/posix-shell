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
    private static String pwd = Paths.get("").toAbsolutePath().toString();
    private static final String[] commands = {"echo", "exit", "type", "pwd", "cd", "cat", "ls"};

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to POSIX-Shell. Type 'exit 0' to quit.");
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            if (input.equals("exit 0")) {
                break;
            } else if (input.startsWith("echo")) {
                handleEcho(input);
            } else if (input.startsWith("type")) {
                handleType(input);
            } else if (input.equals("pwd")) {
                handlePwd();
            } else if (input.startsWith("cd")) {
                handleCd(input);
            } else if (input.startsWith("cat")) {
                handleCat(input);
            } else if (input.startsWith("ls") || input.equals("ls")) {
                handleLs();
            } else if (input.startsWith("touch")) {
                handleTouch(input);
            } else {
                handleExternalCommand(input);
            }
        }
        scanner.close();
    }

    private static void handleEcho(String input) {
        String content = input.substring(4).trim();

        if (content.startsWith("\"")) {
            Pattern pattern = Pattern.compile("\"(.*?)\"");
            Matcher matcher = pattern.matcher(content);
            StringBuilder concatenatedContent = new StringBuilder();
            int count = 0;

            while (matcher.find()) {
                String match = matcher.group(1);
                if (count > 0) {
                    concatenatedContent.append(" ").append(match);
                } else {
                    concatenatedContent.append(match);
                    count++;
                }
            }
            content = concatenatedContent.toString();
        } else if ((content.startsWith("'") && content.endsWith("'")) || (content.startsWith("\"") && content.endsWith("\""))) {
            content = content.substring(1, content.length() - 1);
        } else {
            content = content.replaceAll("\\s+", " ");
        }
        System.out.println(content);
    }

    private static void handleType(String input) {
        String typeSubString = input.substring(5).trim();
        if (Arrays.asList(commands).contains(typeSubString)) {
            System.out.println(typeSubString + " is a shell builtin");
        } else {
            String path = getPath(typeSubString);
            if (path != null) {
                System.out.println(typeSubString + " is " + path);
            } else {
                System.out.println(typeSubString + ": not found");
            }
        }
    }

    private static void handlePwd() {
        System.out.println(pwd);
    }

    private static void handleCd(String input) {
        String dir = input.substring(3).trim();

        try {
            if (dir.startsWith("./")) {
                dir = pwd + "/" + dir.substring(2);
            } else if (dir.startsWith("../")) {
                dir = Path.of(pwd).resolve(dir).normalize().toString();
            } else if (dir.equals("..")) {
                Path parentPath = Path.of(pwd).getParent();
                if (parentPath != null) pwd = parentPath.toString();
                return;
            } else if (dir.equals("~")) {
                pwd = System.getProperty("user.home");
                return;
            }

            if (Files.isDirectory(Path.of(dir)) && Files.exists(Path.of(dir))) {
                pwd = dir;
            } else {
                System.out.printf("cd: %s: No such file or directory%n", dir);
            }
        } catch (Exception e) {
            System.out.printf("cd: %s: Error occurred%n", dir);
        }
    }

    private static void handleCat(String input) {
        String[] argsArray = input.substring(4).trim().split(" ");
        for (String fileName : argsArray) {
            if ((fileName.startsWith("'") && fileName.endsWith("'")) || (fileName.startsWith("\"") && fileName.endsWith("\""))) {
                fileName = fileName.substring(1, fileName.length() - 1);
            }
            Path filePath = Paths.get(pwd, fileName.trim());

            if (Files.exists(filePath)) {
                try {
                    System.out.println("\n" + fileName + ": \n");
                    Files.lines(filePath).forEach(System.out::println);
                } catch (IOException e) {
                    System.out.println("cat: " + fileName + ": Error reading file");
                }
            } else {
                System.out.println("cat: " + fileName + ": No such file");
            }
        }
    }

    private static void handleLs() {
        File directory = new File(pwd);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.printf("/%s\n\n", file.getName());
                } else {
                    System.out.printf("%s\n\n", file.getName());
                }

                
            }
        }
    }

    private static void handleTouch(String input) throws IOException{
        String command = input.substring(6);
        File file = new File(pwd+"/"+command);
        try {
            boolean isFileCreated = file.createNewFile();
            if (isFileCreated) {
                System.out.println("File Created Successfully.");
            }else{
                System.out.println("File already exists or an error occured");
            }
        } catch (IOException e) {
            
        }
        
        
    }

    private static void handleExternalCommand(String input) throws IOException {
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String pwd = Paths.get("").toAbsolutePath().toString();
    private static final String[] commands = {"echo", "exit", "type", "pwd", "cd", "cat", "ls","touch","rm","mkdir","mv","rename","cp","clear"};

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
            } else if (input.startsWith("pwd")) {
                handlePwd(input);
            } else if (input.startsWith("cd")) {
                handleCd(input);
            } else if (input.startsWith("cat")) {
                handleCat(input);
            } else if (input.startsWith("ls")) {
                handleLs(input);
            } else if (input.startsWith("touch")) {
                handleTouch(input);
            } else if (input.startsWith("rm")) {
                handleRm(input);
            } else if (input.startsWith("mkdir")) {
                handleMkdir(input);
            }else if (input.startsWith("mv")) {
                handleMv(input);
            } else if (input.startsWith("rename")) {
                handleRename(input);
            } else if (input.startsWith("cp")) {
                handleCp(input);
            } else if (input.startsWith("clear")) {
                handleClear(input);
            } else if (input.equals("--help")) {
                handleHelp();
            } else{
                handleExternalCommand(input);
            }
        }
        scanner.close();
    }

    private  static  void handleHelp(){

        System.out.println("This Java-based POSIX shell supports a variety of core commands: \n");
        System.out.println("echo: Display messages with ease.");
        System.out.println("pwd: See where you are in the file system.");
        System.out.println("cd: Navigate through directories like a pro.");
        System.out.println("cat: View file contents instantly.");
        System.out.println("ls: List files and directories in your current location.");
        System.out.println("touch: Create new files effortlessly.");
        System.out.println("mkdir: Generate directories for your projects.");
        System.out.println("rm: Remove files with a single command.");
        System.out.println("mv: Move or rename files and directories.");
        System.out.println("type: Quickly identify if a command is internal or external.");
        System.out.println("rename: Rename a file to the current directory.");
        System.out.println("cp: Create a copy of a file to the current directory.");
        System.out.println("clear: Clear the console screen.");
        System.out.println("exit 0: Close the shell gracefully.\n");

        System.out.println("For more help use '-help' for commands explaination.");
    }

    private static void handleEcho(String input) {
        String content = input.substring(4).trim();

        if (content.equals("-help")){
            System.out.println("Usage: echo <statement>");
            System.out.println("Echo: Display messages with ease.");
        }else {
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
    }

    private static void handleType(String input) {
        String typeSubString = input.substring(5).trim();

        if (typeSubString.equals("-help")){
            System.out.println("Usage: type <command>");
            System.out.println("Type: Quickly identify if a command is internal or external.");
        } else if (Arrays.asList(commands).contains(typeSubString)) {
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

    private static void handlePwd(String input) {
        String pwdSubString = input.substring(4).trim();

        if (pwdSubString.equals("-help")){
            System.out.println("Usage: pwd");
            System.out.println("Pwd: See where you are in the file system.");
        }else {
            System.out.println(pwd);
        }
    }

    private static void handleCd(String input) {
        String dir = input.substring(3).trim();

        if (dir.equals("-help")){
            System.out.println("Usage: cd <directory>");
            System.out.println("Cd: Change the current working directory.");
            return;
        }

        try {
            if (dir.startsWith("./")) {
                dir = pwd + File.separator + dir.substring(2);
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

        if (argsArray.length == 1 && argsArray[0].equals("-help")){
            System.out.println("Usage: cat <file1> <file2> ...");
            System.out.println("Cat: Display contents of files.");
            return;
        }

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

    private static void handleLs(String input) {
        String lsSubString = input.substring(2).trim();

        if (lsSubString.equals("-help")){
            System.out.println("Usage: ls");
            System.out.println("Ls: List files and directories.");
            return;
        }

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

    private static void handleTouch(String input) {
        String command = input.substring(6).trim();

        if (command.equals("-help")) {
            System.out.println("Usage: touch <filename>");
            System.out.println("Touch: Create an empty file in the current directory.");
            return;
        }

        File file = new File(pwd + File.separator + command);
        try {
            boolean isFileCreated = file.createNewFile();
            if (isFileCreated) {
                System.out.println("File Created Successfully.");
            } else {
                System.out.println("File already exists or an error occurred.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleMkdir(String input) {
        String dirname = input.substring(6).trim();

        if (dirname.equals("-help")) {
            System.out.println("Usage: mkdir <directory_name>");
            System.out.println("Mkdir: Create a new directory in the current directory.");
            return;
        }

        String cwd = pwd + File.separator + dirname;
        File dir = new File(cwd);
        boolean isDirectoryCreated = dir.mkdir();
        if (isDirectoryCreated) {
            System.out.println("Directory created Successfully!");
        } else {
            System.out.println("Failed to create Directory!");
        }
    }

    private static void handleRm(String input) {
        String fname = input.substring(3).trim();

        if (fname.equals("-help")) {
            System.out.println("Usage: rm <filename>");
            System.out.println("Rm: Remove a file from the current directory.");
            return;
        }

        Path file = Paths.get(pwd + File.separator + fname);
        try {
            Files.delete(file);
            System.out.println("File deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error: Unable to delete " + fname + ". " + e.getMessage());
        }
    }

    private static void handleMv(String input) {
        String[] fname = input.substring(3).trim().split(" ");

        if (fname[0].equals("-help")) {
            System.out.println("Usage: mv <source> <destination>");
            System.out.println("Mv: Move or rename a file/directory to a new location.");
            return;
        }

        if (fname.length > 2) {
            System.out.println("mv: can't pass more than 2 parameters");
            return;
        }

        Path existPath = Paths.get(pwd + File.separator + fname[0]);
        if (fname[1].startsWith("./")) {
            fname[1] = pwd + fname[1].substring(1);
        }
        Path newPath = Paths.get(fname[1] + File.separator + fname[0]);
        if (newPath != null) {
            try {
                Files.move(existPath, newPath);
                System.out.println("Moved successfully.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("There is no such directory: " + newPath);
        }
    }

    private static void handleRename(String input) {
        String[] fname = input.substring(7).trim().split(" ");

        if (fname[0].equals("-help")) {
            System.out.println("Usage: rename <old_name> <new_name>");
            System.out.println("Rename: Rename a file or directory in the current directory.");
            return;
        }

        File file = new File(pwd + File.separator + fname[0]);
        File rename = new File(pwd + File.separator + fname[1]);

        boolean isRenamed = file.renameTo(rename);
        if (isRenamed) {
            System.out.println("File Renamed Successfully!");
        } else {
            System.out.println("File already exists or an error occurred.");
        }
    }

    private static void handleCp(String input) {
        String[] fname = input.substring(3).trim().split(" ");

        if (fname[0].equals("-help")) {
            System.out.println("Usage: cp <source> <destination>");
            System.out.println("Cp: Copy a file to a new location.");
            return;
        }

        if (fname.length > 2) {
            System.out.println("cp: can't pass more than 2 parameters");
            return;
        }

        Path existingFile = Paths.get(pwd + File.separator + fname[0]);
        Path newFile = Paths.get(pwd + File.separator + fname[1]);
        try {
            File readFile = new File(existingFile.toString());
            FileWriter writer = new FileWriter(newFile.toString());
            Scanner scanner = new Scanner(readFile);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                writer.write(data + "\n");
            }
            scanner.close();
            writer.close();
            System.out.println("File copied successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleClear(String input) {
        if (input.trim().equals("clear -help")) {
            System.out.println("Usage: clear");
            System.out.println("Clear: Clear the console screen.");
            return;
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
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
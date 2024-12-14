# Java Shell Emulator

This Java program simulates a basic shell (terminal) environment with commands such as `echo`, `exit`, `type`, `pwd`, `cd`, `cat`, and `ls`. It demonstrates file and directory management, command parsing, and execution of external commands in Java.

---

## Features

1. **`echo` Command**
   - Prints the provided input text to the terminal.
   - Supports both single (`'`) and double (`"`) quotes.
   - Handles concatenation of quoted strings.

   **Example:**
   ```
   $ echo "Hello World"
   Hello World
   ```

2. **`exit` Command**
   - Terminates the shell program when the input is `exit 0`.

   **Example:**
   ```
   $ exit 0
   ```

3. **`type` Command**
   - Checks if the specified parameter is a built-in shell command or a system command.
   - Displays its type or returns "not found" if the command is unavailable.

   **Example:**
   ```
   $ type echo
   echo is a shell builtin

   $ type ls
   ls is /bin/ls
   ```

4. **`pwd` Command**
   - Prints the current working directory.

   **Example:**
   ```
   $ pwd
   /home/user
   ```

5. **`cd` Command**
   - Changes the current working directory.
   - Supports:
     - Relative paths (`./`, `../`)
     - Absolute paths
     - Home directory (`~`)
     - Parent directory (`..`)
   - Displays an error if the directory does not exist.

   **Example:**
   ```
   $ cd ../Documents
   $ pwd
   /home/user/Documents
   ```

6. **`cat` Command**
   - Displays the contents of one or more files in the terminal.
   - Handles file paths relative to the current working directory.

   **Example:**
   ```
   $ cat file1.txt file2.txt

   file1.txt:
   [contents of file1]

   file2.txt:
   [contents of file2]
   ```

7. **`ls` Command**
   - Lists all files and directories in the current working directory.
   - Directories are prefixed with a `/`.

   **Example:**
   ```
   $ ls
   /Documents
   file1.txt
   file2.txt
   ```

8. **External Commands**
   - Executes external system commands by searching the system's `PATH` environment variable.

   **Example:**
   ```
   $ grep "search" file.txt
   [grep output]
   ```

---

## Usage

1. Compile the program:
   ```bash
   javac Main.java
   ```

2. Run the program:
   ```bash
   java Main
   ```

3. Use the shell commands interactively.

---

## Implementation Details

- **File and Directory Management:**
  - The `java.nio.file` package is used for operations like verifying file existence and handling paths.
  - Methods like `Files.isDirectory` and `Files.exists` ensure directory and file validity.

- **Command Parsing:**
  - Input commands are parsed using Java's `String` methods and regular expressions (via the `Pattern` and `Matcher` classes).

- **External Command Execution:**
  - External commands are executed using `Runtime.getRuntime().exec()`.
  - Output streams are transferred directly to the terminal using `transferTo()`.

---

## Notes

- The program assumes a UNIX-like environment for commands like `ls` and `grep`.
- Ensure the `PATH` environment variable is correctly set for external command execution.
- Error handling is implemented for invalid paths, files, or commands.

---

## Future Enhancements

- Add support for piping (`|`) and redirection (`>`, `>>`).
- Enhance error handling for system-specific scenarios.
- Add tab completion for commands and paths.
- Implement more built-in commands like `rm` and `mkdir`.


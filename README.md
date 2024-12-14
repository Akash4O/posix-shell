# POSIX Shell Implementation in Java 🚀

Welcome to the **POSIX Shell** project! This simple shell implementation, written in **Java**, mimics the behavior of a POSIX-like shell, providing essential commands like `echo`, `pwd`, `cd`, `cat`, `ls`, and more. Whether you're an aspiring developer or just looking to explore how shells work, this project is a great way to get hands-on experience!

## 🌟 Features

This Java-based POSIX shell supports a variety of core commands:

- **Echo**: Display messages with ease.
- **Pwd**: See where you are in the file system.
- **Cd**: Navigate through directories like a pro.
- **Cat**: View file contents instantly.
- **Ls**: List files and directories in your current location.
- **Touch**: Create new files effortlessly.
- **Mkdir**: Generate directories for your projects.
- **Rm**: Remove files with a single command.
- **Mv**: Move or rename files and directories.
- **Type**: Quickly identify if a command is internal or external.
- **Exit**: Close the shell gracefully.

## 📜 Supported Commands

Here are the basic commands you can use within the shell:

### 1. **`echo [text]`** - Display a message
Easily print messages or variables to the terminal.

```bash
$ echo "Hello, World!"
Hello, World!
```

### 2. **`pwd`** - Print Working Directory
This command shows the full path of your current directory.

```bash
$ pwd
/Users/username
```

### 3. **`cd [directory]`** - Change Directory
Move between directories with ease.

```bash
$ cd /path/to/directory
$ pwd
/path/to/directory
```

### 4. **`cat [file]`** - Concatenate (Display) File Content
Quickly view the contents of a file.

```bash
$ cat file.txt
This is the content of the file.
```

### 5. **`ls`** - List Directory Contents
Get a list of files and directories in the current location.

```bash
$ ls
file1.txt  file2.txt  directory1
```

### 6. **`touch [filename]`** - Create a New File
Create a new empty file with a simple command.

```bash
$ touch newfile.txt
New file created successfully!
```

### 7. **`mkdir [directory]`** - Make a New Directory
Easily create new directories for organizing your files.

```bash
$ mkdir newdir
Directory created successfully!
```

### 8. **`rm [filename]`** - Remove a File
Delete files with a single command.

```bash
$ rm file1.txt
file1.txt deleted successfully.
```

### 9. **`mv [source] [destination]`** - Move or Rename Files
Move files to new locations or rename them.

```bash
$ mv file1.txt newdir/
$ ls newdir
file1.txt
```

### 10. **`type [command]`** - Identify Command Type
Find out whether a command is built-in or external.

```bash
$ type ls
ls is a shell builtin
```

### 11. **`exit`** - Exit the Shell
Exit gracefully from the shell.

```bash
$ exit 0
```

---

## 🛠️ How to Use

### Usage Examples

Here are individual examples showing how to use each command:

#### 1. `echo` Command
Use `echo` to display text in the terminal.

```bash
$ echo "Hello, World!"
Hello, World!
```

#### 2. `pwd` Command
Display your current directory with `pwd`.

```bash
$ pwd
/Users/username
```

#### 3. `cd` Command
Navigate to a different directory with `cd`.

```bash
$ cd /path/to/directory
$ pwd
/path/to/directory
```

#### 4. `cat` Command
View the contents of a file with `cat`.

```bash
$ cat file.txt
This is the content of the file.
```

#### 5. `ls` Command
List all files and directories in the current directory.

```bash
$ ls
file1.txt  file2.txt  directory1
```

#### 6. `touch` Command
Create a new empty file with `touch`.

```bash
$ touch newfile.txt
File created successfully.
```

#### 7. `mkdir` Command
Make a new directory with `mkdir`.

```bash
$ mkdir newdir
Directory created successfully.
```

#### 8. `rm` Command
Delete a file with `rm`.

```bash
$ rm file1.txt
File1.txt deleted successfully.
```

#### 9. `mv` Command
Move a file to another directory with `mv`.

```bash
$ mv file1.txt newdir/
$ ls newdir
file1.txt
```

#### 10. `type` Command
Check whether a command is built-in or external.

```bash
$ type ls
ls is a shell builtin
```

#### 11. `exit` Command
Exit the shell using the `exit` command.

```bash
$ exit
```

---

## ⚠️ Error Handling

- **Moving Files**: If you try to pass more than two arguments to the `mv` command, the shell will display an error:
  - Example: `mv: can't pass more than 2 parameters`
- **Directory Issues**: If a directory does not exist when using commands like `mv`, you'll get an error message:
  - Example: `There is no such directory: [path]`

## 📦 Installation

1. **Clone the Repository**: Start by cloning the repo to your local machine.

   ```bash
   git clone https://github.com/yourusername/posix-shell.git
   ```

2. **Compile the Code**: Next, compile the Java files using the `javac` command.

   ```bash
   javac Main.java
   ```

3. **Run the Shell**: Finally, execute the compiled code with the `java` command.

   ```bash
   java Main
   ```
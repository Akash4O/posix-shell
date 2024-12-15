
# POSIX Shell Implementation in Java 🚀  

Welcome to the **POSIX Shell** project! This lightweight, Java-based shell brings the functionality of a POSIX-like environment to your fingertips. Whether you’re a programming enthusiast, a systems student, or just curious about shell scripting, this project is a perfect starting point! 🌟  

---

## 🌟 Features  

### 🎯 Core Functionality  
This shell supports a range of essential POSIX-like commands:  
- **`echo`**: Print messages or variables 🗨️.  
- **`pwd`**: Show the current directory path 📂.  
- **`cd`**: Navigate directories effortlessly 🌐.  
- **`cat`**: View the content of files 📖.  
- **`ls`**: List directory contents 🗃️.  
- **`touch`**: Create new files 📝.  
- **`mkdir`**: Create directories 📁.  
- **`rm`**: Remove files or directories 🗑️.  
- **`mv`**: Move or rename files and directories 🔄.  
- **`type`**: Identify whether a command is built-in or external 🔍.  
- **`exit 0`**: Exit the shell gracefully 🚪.  

### 🆕 New Features  
- **`cp [source] [destination]`**: Copy files effortlessly 📤.  
   ```bash
   $ cp file.txt /new/location
   File copied successfully!
   ```
- **`rename [oldName] [newName]`**: Rename files directly 📑.
   ```bash
   $ rename oldfile.txt newfile.txt
   File renamed successfully!
   ```
- **`clear`**: Clear the terminal screen 🧹.
   ```bash
   $ clear
   ```
- **Enhanced Help System**: Use `--help` with any command for detailed guidance.
   ```bash
   $ ls --help
   Usage: ls [options]
   Lists files and directories.
   ```

---

## 📜 Supported Commands

### 1️⃣ **`echo [text]`**
Print messages or variables:
```bash
$ echo "Hello, World!"
Hello, World!
```  

### 2️⃣ **`pwd`**
Display the current directory:
```bash
$ pwd
/home/user
```  

### 3️⃣ **`cd [directory]`**
Navigate between directories:
```bash
$ cd /path/to/directory
```  

### 4️⃣ **`ls`**
List files and directories:
```bash
$ ls
file1.txt  file2.txt  folder/
```  

### 5️⃣ **`cat [file]`**
View the content of a file:
```bash
$ cat file.txt
This is a sample file.
```  

### 6️⃣ **`touch [filename]`**
Create a new file:
```bash
$ touch newfile.txt
File created successfully.
```  

### 7️⃣ **`mkdir [directory]`**
Create a directory:
```bash
$ mkdir new_folder
Directory created successfully.
```  

### 8️⃣ **`rm [filename]`**
Remove a file or directory:
```bash
$ rm file.txt
File deleted successfully.
```  

### 9️⃣ **`mv [source] [destination]`**
Move or rename files/directories:
```bash
$ mv file1.txt new_folder/
```  

### 🔟 **`cp [source] [destination]`**
Copy a file:
```bash
$ cp file.txt backup/
File copied successfully!
```  

### 1️⃣1️⃣ **`rename [oldName] [newName]`**
Rename files directly:
```bash
$ rename oldfile.txt newfile.txt
```  

### 1️⃣2️⃣ **`clear`**
Clear the terminal screen:
```bash
$ clear
```  

### 1️⃣3️⃣ **`type [command]`**
Check if a command is built-in or external:
```bash
$ type ls
ls is a shell builtin
```  

### 1️⃣4️⃣ **`exit 0`**
Close the shell gracefully:
```bash
$ exit 0
```  

---

## ⚠️ Error Handling

This shell has robust error handling:
- **Invalid Commands**: Suggestions provided for common typos.
- **Missing Files/Directories**: Alerts if paths or files don’t exist.
- **Syntax Errors**: Detailed usage hints provided for incorrect syntax.

---

## 📦 Installation

### Prerequisites
Ensure **Java 8+** is installed on your system.

### Steps to Install
1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/posix-shell.git
   cd posix-shell
   ```  

2. **Compile the Code**
   ```bash
   javac Main.java
   ```  

3. **Run the Shell**
   ```bash
   java Main
   ```  

---

## 🎮 Usage

Simply type any supported command and press **Enter** ⏎ to execute.

### Example Session:
```bash
$ pwd
/home/user

$ mkdir test
Directory created successfully.

$ touch file1.txt
File created successfully.

$ ls
file1.txt  test/

$ mv file1.txt test/
$ ls test
file1.txt

$ exit 0
Goodbye!
```  

Use `--help` with any command for detailed guidance!

---
## 📞 Contact

For queries or feedback, connect with us:
- **Null Baba**
- Email: [akashkolde342@gmail.com](mailto:akashkolde342@gmail.com)


---

🌟 **Happy Shell-ing!** 🎉
```  

Let me know if any additional details or customizations are needed! 😊
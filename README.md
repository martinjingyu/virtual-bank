# virtual-bank --41 group

## Table of Contents
- [Introduction](#Introduction)
- [Features](#features)
- [Framework](#framework)
- [Getting Started](#getting-started)
- [Acknowledgement](#acknowledgement)

## Introduction
Our virtual-bank project is a comprehensive banking system designed to assist users in managing their accounts effectively. It provides features for creating, modifying, and deleting accounts, as well as performing various financial transactions such as deposits, withdrawals, transfers, and balance inquiries. The system is user-friendly and intuitive, making it suitable for users of all ages.

## Features
- **Account Management**: Create, modify, and delete saving and current accounts.
- **Transaction Handling**: Perform deposits, withdrawals, transfers, and balance inquiries.
- **User-Friendly Interface**: Intuitive command-line interface for easy interaction.
- **Validation**: Input validation to ensure data integrity and prevent errors.
- **History Tracking**: Keep track of transaction history for auditing and analysis.

## Framework
Our project is developed using Java and follows 
object-oriented principles for better code organization and 
maintainability. We have utilized packages to structure the code logically 
and enhance modularity. Additionally, we have incorporated unit testing using 
JUnit to ensure the reliability and correctness of our code.

Our project is organized into five main folders:

1. **data**: This folder is dedicated to storing all user data. It's essential not to modify any files inside this folder, as the data is encrypted for security purposes. Any tampering with the files may result in data corruption and render the software inoperable.

2. **image**: This folder contains images that are used within the software's interface. These images enhance the user experience and contribute to the visual appeal of the application.

3. **javadoc**: This folder contains the JavaDoc documentation generated for developers. The JavaDoc provides comprehensive and readable documentation for all classes, methods, and variables, making it easier for developers to understand and utilize the codebase.

4. **src**: The source folder contains all the source code files of the project. It includes packages and classes responsible for implementing the functionality of the virtual banking system. Developers can explore, modify, and contribute to the source code in this folder.

5. **User_manual**: This folder contains the user manual, which serves as a guide for users on how to use the software effectively. Users are encouraged to read this document before using the application to familiarize themselves with its features and functionalities.

## Getting Started
To run the virtual-bank software, follow these steps:

1. **Using JAR Package:**
   - If you have a Java environment with JDK 17 or later installed, you can run the program from the command line.
   - Open a terminal in the folder `virtual-bank-main`.
   - Enter the following command:
       ```bash
       java --enable-preview -cp virtual-bank-main.jar Controller.Start
       ```
   - The software will start without any further configuration.

2. **Using Clickable Executable (Virtual.exe):**
   - If you don't have the Java JDK, you need to download JDK17. Then, name the JDK flie as "jdk-17.0.11" and put it in the virtual-bank-main library. Simply click on the `Virtual_Bank.exe` file to run the software directly.

## Acknowledgement
As the team leader, I would like to express my gratitude to all my team members who 
have helped make this project possible.

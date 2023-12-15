# Remote File Transfer with SSHJ

This application allows you to transfer the contents of a local folder to a remote folder using SFTP (SSH File Transfer Protocol). It utilizes the SSHJ library to establish a secure connection and perform the file transfer.

## Prerequisites

Before running the application, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 11 or higher
- Maven

## Getting Started

1. Clone the repository:

    ```shell
    git clone https://github.com/your-username/remote-file-transfer-sshj.git
    ```

2. Build the project:

    ```shell
    cd remote-file-transfer-sshj
    mvn clean install
    ```

3. Configure the application:

    Open the `config.properties` file and provide the necessary configuration details, such as the local folder path, remote server details (host, port, username, password), and the remote folder path.

4. Run the application:

    ```shell
    mvn exec:java
    ```

    The application will establish an SFTP connection to the remote server and transfer the contents of the local folder to the specified remote folder.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
MIT License
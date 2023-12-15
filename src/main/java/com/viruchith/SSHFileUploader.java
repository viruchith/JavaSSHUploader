package com.viruchith;

import java.io.File;
import java.io.IOException;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;

public class SSHFileUploader {

    private String REMOTE_HOST;
    private String USERNAME;
    private String PASSWORD;

    private SSHClient sshClient;
    private SFTPClient sftpClient;

    SSHFileUploader(String REMOTE_HOST, String USERNAME, String PASSWORD) {
        this.REMOTE_HOST = REMOTE_HOST;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        connectToServer();
    }

    private void connectToServer(){
        System.out.println("CONNECTING TO : "+REMOTE_HOST);
        sshClient = new SSHClient();
        // https://stackoverflow.com/questions/7873909/dealing-with-host-key-not-verifiable-could-not-verify-ssh-rsa-host-key-with
        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        try {
            sshClient.loadKnownHosts(); // Load known hosts file
            sshClient.connect(REMOTE_HOST); // Connect to the remote server
            sshClient.authPassword(USERNAME, PASSWORD); // Authenticate with username and password
            System.out.println("CONNECTED : "+sshClient.isConnected());
            System.out.println("AUTHENTICATED : "+sshClient.isAuthenticated());
            sshClient.useCompression(); // Use compression
            sftpClient = sshClient.newSFTPClient(); // Create SFTP client
    }catch(IOException e){
        e.printStackTrace();
    }
    }

    public void uploadFiles(File srcDir, String dest) {

        File[] files = srcDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("directory:" + file.getAbsolutePath());
                uploadFiles(file, dest);
            } else {
                System.out.println("file:" + file.getAbsolutePath());
                try {
                    FileSystemFile srcFileSystemFile = new FileSystemFile(file);
                    System.out.println("PUTTING : "+file.getAbsolutePath());
                    sftpClient.put(srcFileSystemFile, dest);
                    System.out.println("PUT : "+file.getAbsolutePath()+" SUCCESS");
                } catch (IOException e) {
                    System.out.println("PUT : "+file.getAbsolutePath()+" FAILED");
                }
            }
        }
    }

    public void closeConnection() {
        try {
            sftpClient.close();
            sshClient.close();
            System.out.println("CONNECTION CLOSED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

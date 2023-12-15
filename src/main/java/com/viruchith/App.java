package com.viruchith;

import java.io.File;
import java.io.IOException;


public class App {

    private static final String REMOTE_HOST = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";


    public static void main(String[] args) throws IOException {
        SSHFileUploader sshFileUploader = new SSHFileUploader(REMOTE_HOST, USERNAME, PASSWORD);
        sshFileUploader.uploadFiles(new File(""), "");
        sshFileUploader.closeConnection();
    }
}

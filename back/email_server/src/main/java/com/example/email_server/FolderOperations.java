package com.example.email_server;

import java.io.File;

public class FolderOperations implements IFolderOperations{
    public FolderOperations(String folderName, String userName) {
        this.folderName = folderName;
        this.userName = userName;
    }

    private String folderName = "";
    private String userName = "";
    private String path = "C:\\Users\\maria\\Desktop\\users\\";

    @Override
    public void create() {
        File newFolder = new File(this.path + userName + "\\" + folderName);
        if(newFolder.mkdir()){


            return;
        }
        else{
            System.out.println("folder Already Exists");
        }

    }

    @Override
    public void rename(String name) {

    }

    @Override
    public void delete() {

    }

}

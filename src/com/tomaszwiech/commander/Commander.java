package com.tomaszwiech.commander;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Commander {
    Scanner scanner  = new Scanner(System.in);

    private String getInputDataAsString() {
        return scanner.nextLine();
    }

    private File getFolder(String path) {
        return new File(path);
    }

    private File[] getFiles(File file) {
        return file.listFiles();
    }

    private void printSimpleFiles(File[] files) {
        for(File file : files) System.out.println(file.getName());
    }

    private BasicFileAttributes getFileDetails(Path file) throws Exception {
       return Files.readAttributes(file, BasicFileAttributes.class);
    }

    private void printAtributes(BasicFileAttributes attr, Path file) {
        DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
        System.out.print(file.toString() + "  ");
        System.out.print(df.format(attr.creationTime().toMillis()) + "  ");
        System.out.print(df.format(attr.lastModifiedTime().toMillis()) + "  ");
        System.out.print(attr.size() + "  ");
        System.out.print(attr.isDirectory() + "\n");
    }

    private FilenameFilter getFileNameFilter(String str) {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
            	return s.endsWith(str);
            }
        };
        return filter;
    }

    private File[] filterFiles(File file, FilenameFilter filter) {
        return file.listFiles(filter);
    }

    private void printTree(File file, String str) {
        File[] files = file.listFiles();
        for(java.io.File f : files) {
            if(f.isDirectory()) {
                System.out.println(f.toString());
                printTree(f, str);
            }
            else {
                System.out.println(f.toString());
            }
        }
    }

    private void procedureSimpleList(String path) {
        File folder = getFolder(path);
        File[] files = getFiles(folder);
        printSimpleFiles(files);
    }

    private void procedureDetailsList(String path) throws Exception{
        File folder = getFolder(path);
        File[] files = folder.listFiles();
        for(File f : files) {
            Path p = f.toPath();
            BasicFileAttributes attr = getFileDetails(p);
            printAtributes(attr, p);
        }
    }

    private void procedureFilterList(String path, String extension) {
        File folder = getFolder(path);
        FilenameFilter filter = getFileNameFilter(extension);
        File[] files = filterFiles(folder, filter);
        for(File f : files) System.out.println(f.getName());
    }

    private void procedureFolderTree(String path) {
        File folder = getFolder(path);
        printTree(folder, "");
    }

    void makeChoice(String choice, String path) throws Exception {
        switch (choice) {
            case "1": procedureSimpleList(path);
                    break;
            case "2": procedureDetailsList(path);
                    break;
            case "3":
            	System.out.println("Write down extension ....:");
                String extension = getInputDataAsString();
                procedureFilterList(path, extension);
                break;
            case "4": procedureFolderTree(path);
        }
    }

    public static void main(String[] args) {
        Commander comm = new Commander();
        try {
            comm.makeChoice(args[0], args[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
























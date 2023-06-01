package app;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileNavigator fileNavigator = new FileNavigator();

        fileNavigator.add( new FileData("file1.txt", 100, "/path/to/file"));
        fileNavigator.add( new FileData("file2.txt", 200, "/path/to/file"));
        fileNavigator.add( new FileData("file3.txt", 150, "/another/path"));
        fileNavigator.add( new FileData("file5.txt", 400, "/another/path"));
        fileNavigator.add( new FileData("file6.txt", 300, "/another/path"));

        fileNavigator.printCol();


        List<FileData> filesAtPath = fileNavigator.find("/path/to/file");
        System.out.println("Files at /path/to/file: ");
        for(FileData file : filesAtPath) {
            System.out.println(file.getName());
        }

        List<FileData> filteredFiles = fileNavigator.filterBySize( 150);
        System.out.println("\n Files at /path/to/file with size <= 150:");
        for (FileData file : filteredFiles) {
            System.out.println(file.getName());
        }

        fileNavigator.remove("/path/to/file");
        List<FileData> removedFiles = fileNavigator.find("/path/to/file");
        System.out.println("\n Files at /path/to/file after removal:");
        for (FileData file : removedFiles) {
            System.out.println(file.getName());
        }
        List<FileData> sortedFiles = fileNavigator.sortBySize();
        System.out.println("\n All files sorted by size: ");
        for (FileData file : sortedFiles) {
            System.out.println(file.getName() + " - " + file.getSize());
        }

    }
}

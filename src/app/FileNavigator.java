package app;

import java.util.*;

public class FileNavigator {
    private final Map<String, List<FileData>> filesByPath;

    public FileNavigator() {
        filesByPath = new HashMap<>();
    }

    public void add(FileData file) {
        if (!filesByPath.containsKey(file.getPath())) {
            filesByPath.put(file.getPath(), new ArrayList<>());
        }
        List<FileData> files = filesByPath.get(file.getPath());
        files.add(file);
    }

    public List<FileData> find(String path) {
        return filesByPath.getOrDefault(path, new ArrayList<>());
    }

    public List<FileData> filterBySize(long maxSize) {
        List<FileData> filteredFiles = new ArrayList<>();
        for (String path : filesByPath.keySet()) {
            List<FileData> files = filesByPath.getOrDefault(path, new ArrayList<>());
            for (FileData file : files) {
                if (file.getSize() <= maxSize) {
                    filteredFiles.add(file);
                }
            }
        }

        return filteredFiles;
    }

    public void remove(String path) {
        filesByPath.remove(path);
    }

    public List<FileData> sortBySize() {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> files : filesByPath.values()) {
            allFiles.addAll(files);
        }
        allFiles.sort(Comparator.comparingLong(FileData::getSize));
        return allFiles;
    }

    public void printCol() {
        for(String key : filesByPath.keySet())
            System.out.println(key + " : " + filesByPath.get(key));
    }
}

package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNavigator {
    private Map<String, List<FileData>> filesByPath;

    public FileNavigator() {
        filesByPath = new HashMap<>();
    }

    public void add(String path, FileData file) {
        if (!pathExists(path)) {
            filesByPath.put(path, new ArrayList<>());
        }
        List<FileData> files = filesByPath.get(path);
        files.add(file);
    }

    public List<FileData> find(String path) {
        return  filesByPath.getOrDefault(path, new ArrayList<>());
    }

    public List<FileData> filterBySize(String path, long maxSize) {
        List<FileData> filteredFiles = new ArrayList<>();
        List<FileData> files = filesByPath.getOrDefault(path, new ArrayList<>());
        for (FileData file : files) {
            if (file.getSize() <= maxSize) {
                filteredFiles.add(file);
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
        allFiles.sort((file1, file2) -> Long.compare(file1.getSize(), file2.getSize()));
        return allFiles;
    }

    private boolean pathExists(String path) {
        return filesByPath.containsKey(path);
    }
}

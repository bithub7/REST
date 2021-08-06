package service;

import controller.FileController;
import model.File;

import java.sql.Timestamp;
import java.util.List;

public class FileService {

    private FileController fileController = new FileController();

    public File save(String name, String path, Timestamp created){
        fileController.setFileName(name);
        fileController.setFilePath(path);
        fileController.setFileCreated(created);
        return fileController.seveFile();
    }

    public File update(Long id, String name, String path, Timestamp updated) {
        fileController.setFileId(id);
        fileController.setFileName(name);
        fileController.setFilePath(path);
        fileController.setFileCreated(updated);
        return fileController.updateFile();
    }

    public File getById(Long id) {
        fileController.setFileId(id);
        return fileController.getFileById();
    }

    public List<File> getAll() {
        return fileController.getAllFiles();
    }

    public void deleteById(Long id) {
        fileController.deleteByIdFile(id);
    }
}

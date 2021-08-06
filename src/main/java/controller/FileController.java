package controller;

import model.File;
import model.User;
import repository.FileRepository;
import repository.hibernate.FileRepositoryImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FileController {

    private FileRepository fileRepository = new FileRepositoryImpl();
    private File model = new File();
    private File file;

    public void setFileId(Long id){
        this.model.setId(id);
    }

    public void setFileName(String name){
        this.model.setName(name);
    }

    public void setFilePath(String path){
        this.model.setPath(path);
    }

    public void setFileCreated(Timestamp created){
        this.model.setCreated(created);
    }

    public void setFileUpdated(Timestamp updated){
        this.model.setUpdated(updated);
    }

    public File seveFile(){
        try {
            file = fileRepository.save(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return file;
    }

    public File updateFile(){
        file = fileRepository.update(model);
        return file;
    }

    public File getFileById(){
        file = fileRepository.getById(model.getId());
        return file;
    }

    public List<File> getAllFiles(){
        List<File> fileList = fileRepository.getAll();
        return fileList;
    }

    public void deleteByIdFile(Long id){
        fileRepository.deleteById(model.getId());
    }


}

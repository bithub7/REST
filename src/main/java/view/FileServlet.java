package view;

import com.google.gson.Gson;
import model.EventType;
import model.File;
import model.User;
import service.EventService;
import service.FileService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;


@WebServlet("/files")
@MultipartConfig
public class FileServlet extends HttpServlet {

    private FileService fileService = new FileService();
    private Gson gson = new Gson();
    private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\uploaded_file\\"; // с этим нет
    //private String filePath = "C:\\Users\\PC\\Desktop\\work\\"; //с этим путем все сохраняется

    private EventService eventService = new EventService();
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        List<File> fileList = fileService.getAll();
        String fileListJSON = gson.toJson(fileList);
        printWriter.println(fileListJSON );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream inputStream = filePart.getInputStream();
        String path = filePath + fileName;
        Files.copy(inputStream, new java.io.File(path).toPath());
        Timestamp created =  new Timestamp(new Date().getTime());
        File file = fileService.save(fileName, path, created);
        eventService.save(user_id, file.getId(), created, EventType.CREATE);
        doPost(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        fileService.deleteById(id);
        doGet(request, response);
    }
}
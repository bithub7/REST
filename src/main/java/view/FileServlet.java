package view;

import model.EventType;
import model.File;
import service.EventService;
import service.FileService;
import utils.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;


@WebServlet("/files")
@MultipartConfig
public class FileServlet extends HttpServlet {

    private FileService fileService = new FileService();
    private EventService eventService = new EventService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        List<File> fileList = fileService.getAll();
        String fileListJSON = JSONUtils.toJson(fileList);
        printWriter.println(fileListJSON );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        String fileName = request.getParameter("file");
        Timestamp created =  new Timestamp(new Date().getTime());
        File file = fileService.save(fileName, null, created);
        eventService.save(user_id, file.getId(), created, EventType.CREATE);
        doGet(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        Long file_id = Long.valueOf(request.getParameter("file_id"));
        String fileName = request.getParameter("file");
        Timestamp updated =  new Timestamp(new Date().getTime());
        fileService.update(file_id, fileName, null, updated);
        eventService.save(user_id, file_id, updated, EventType.UPDATE);
        doGet(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.valueOf(request.getParameter("id"));
        fileService.deleteById(id);
        doGet(request, response);
    }
}
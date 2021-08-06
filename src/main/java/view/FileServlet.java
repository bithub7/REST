package view;

import model.File;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.FileService;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@WebServlet("/files")
public class FileServlet extends HttpServlet {

    private FileService fileService = new FileService();
    private UserService userService = new UserService();
    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;
    private String filePath = System.getProperty("C:\\Users\\PC\\Desktop\\work\\");
    //private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\uploaded_file\\";
    private java.io.File file;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        List<User> userList = userService.getAll();

        StringBuilder html = new StringBuilder("<html>\n" +
                "<body>\n" +
                "<form method=\"POST\" enctype=\"multipart/form-data\">\n" +
                "    \t<input type=\"file\" name=\"file\" size=\"100\"/>\n" +
                "\tSelected user:\n" +
                "\t<select name = \"user_id\">");

        for(User user : userList){
            html.append("<option value=\"").append(user.getId()).append("\">").
                    append(user.getId() + " " + user.getName()).append("</option>");
        }
        html.append("\t</select>\n" +
                "    \t<input type=\"submit\" value=\"create\"/>\n" +
                "</form>\n" +
                "<br>");

        html.append("<form method=\"POST\" enctype=\"multipart/form-data\">\n" +
                "\tId: <input type=\"text\" name=\"id\">\n" +
                "\t<input type=\"file\" name=\"file\" size=\"100\"/>\n" +
                "\tSelected user:\n" +
                "\t<select name = \"user_id\">");

        for(User user : userList){
            html.append("<option value=\"").append(user.getId()).append("\">").
                    append(user.getId() + " " + user.getName()).append("</option>");
        }

        html.append("    \t<input type=\"submit\" value=\"update\"/>\n" +
                "</form>\n" +
                "<br>");

        html.append("<form method=\"POST\">\n" +
                "Id: <input type=\"text\" name=\"id\">\n" +
                "<input type=\"submit\" value=\"delete\"/>\n" +
                "</form>\n" +
                "<br>");

        List<File> fileList = fileService.getAll();

        html.append("<table border=\"1\"><tr><th>Id</th><th>Name</th><th>Path</th><th>Created</th><th>Updated</th></tr>");

        for(File file : fileList) {
            html.append("<tr><td>").append(file.getId()).append("</td><td>")
                    .append(file.getName()).append("</td><td>")
                    .append(file.getPath()).append("</td><td>")
                    .append(file.getCreated()).append("</td><td>")
                    .append(file.getUpdated()).append("</td><td>")
                    .append("</td></tr>");
        }

        html.append("</table> </body> </html>");
        writer.println(html);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long id = null;
        java.io.File file = null;
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new java.io.File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            List fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {

                    String fileName = fileItem.getName();

                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new java.io.File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new java.io.File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(file);
                }
            }

            if (id == null) {
                Date date = new Date();
                fileService.save(file.getName(), file.getPath(), new Timestamp(date.getTime()));
            } else if (file == null) {
                fileService.deleteById(id);
            } else {
                Date date = new Date();
                fileService.update(id, file.getName(), file.getPath(), new Timestamp(date.getTime()));
            }

            doGet(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
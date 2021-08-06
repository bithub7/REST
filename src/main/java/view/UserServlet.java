package view;

import model.User;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        List<User> userList = userService.getAll();

        StringBuilder html = new StringBuilder("<html>\n" +
                "<body>\n" +
                "<form method=\"POST\">\n" +
                "Name: <input type=\"text\" name=\"name\"/>\n" +
                "<input type=\"submit\" value=\"save\"/>\n" +
                "</form>\n" +
                "<br>\n" +
                "<form method=\"POST\">\n" +
                "Id: <input type=\"text\" name=\"id\">\n" +
                "Name: <input type=\"text\" name=\"name\"/>\n" +
                "<input type=\"submit\" value=\"update\"/>\n" +
                "</form>\n" +
                "<br>\n" +
                "<form method=\"POST\">\n" +
                "Id: <input type=\"text\" name=\"id\">\n" +
                "<input type=\"submit\" value=\"delete\"/>\n" +
                "</form>\n" +
                "<br>");

        html.append("<table border=\"1\"><tr><th>Id</th><th>Name</th></tr>");

        for(User user : userList){
            html.append("<tr><td>").append(user.getId()).append("</td><td>").append(user.getName()).append("</td></tr>");
        }
        html.append("</table> </body> </html>");

        writer.println(html);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id;
        try {
            id = Long.valueOf(request.getParameter("id"));
        }catch (NumberFormatException e){
            id = null;
        }

        String name;
        try {
            name = request.getParameter("name");
        }catch (NumberFormatException e){
            name = null;
        }

        if(id == null) {
            userService.save(name);
            doGet(request, response);
        } else if (name == null){
            userService.deleteById(id);
            doGet(request, response);
        } else {
            userService.update(id, name);
            doGet(request, response);
        }

    }

    //формы html не имеют методов PUT и DELETE
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}

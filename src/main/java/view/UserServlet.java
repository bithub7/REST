package view;

import model.User;
import service.UserService;
import utils.JSONUtils;

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
        PrintWriter printWriter = response.getWriter();
        List<User> userList = userService.getAll();
        String userListJSON = JSONUtils.toJson(userList);
        printWriter.println(userListJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        String name = request.getParameter("name");
        User user = userService.save(name);
        String userJSON = JSONUtils.toJson(user);
        printWriter.println(userJSON);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        User user = userService.update(id, name);
        String userJSON = JSONUtils.toJson(user);
        printWriter.println(userJSON);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        Long id = Long.valueOf(request.getParameter("id"));
        userService.deleteById(id);
        printWriter.println("User with id "+ id +" was remote");
    }
}
package ru.otus.servlet;

import com.google.gson.Gson;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


public class UsersApiServlet extends HttpServlet {

    private static final int ID_PATH_PARAM_POSITION = 1;

    private final DBServiceUser serviceUser;
    private final Gson gson;

    public UsersApiServlet(DBServiceUser serviceUser, Gson gson) {
        this.serviceUser = serviceUser;
        this.gson = gson;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var text = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        var tempUser = gson.fromJson(text, User.class);
        serviceUser.saveUser(tempUser);
        var user = new User(tempUser.getId(), tempUser.getName(), tempUser.getLogin(), tempUser.getPassword());
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        out.print(gson.toJson(user));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = serviceUser.getUser(extractIdFromRequest(request)).orElse(null);
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.print(gson.toJson(user));
    }

    private long extractIdFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String id = (path.length > 1) ? path[ID_PATH_PARAM_POSITION] : String.valueOf(-1);
        return Long.parseLong(id);
    }

}

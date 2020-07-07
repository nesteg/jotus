package ru.otus.servlet;

import com.google.gson.Gson;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class CreateUser extends HttpServlet {

    private final DBServiceUser serviceUser;
    private final Gson gson;


    public CreateUser(DBServiceUser serviceUser, Gson gson) {
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
}

// UserServlet类，用来处理用户账户相关的请求和响应
package com.example.picforum;

import com.example.picforum.dao.UserDao;
import com.example.picforum.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    // 处理GET请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String action = request.getParameter("action");
        // 根据不同的action执行不同的操作
        switch (action) {
            case "login":
                // 调用登录方法
                login(request, response);
                break;
            case "logout":
                // 调用登出方法
                logout(request, response);
                break;
            case "register":
                // 调用注册方法
                register(request, response);
                break;
            default:
                // 返回错误信息
                response.sendError(404, "Invalid action");
        }
    }

    // 处理POST请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用GET方法
        doGet(request, response);
    }

    // 登录方法
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 验证用户名和密码是否为空
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // 返回错误信息
            response.sendError(400, "Username and password are required");
            return;
        }
        // 调用数据库操作类，查询用户是否存在
        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsernameAndPassword(username, password);
        // 判断用户是否存在
        if (user == null) {
            // 返回错误信息
            response.sendError(401, "Invalid username or password");
            return;
        }
        // 将用户对象存入session中
        request.getSession().setAttribute("user", user);
        // 重定向到主页
        response.sendRedirect("index.jsp");
    }

    // 登出方法
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将session中的用户对象移除
        request.getSession().removeAttribute("user");
        // 重定向到登录页
        response.sendRedirect("login.jsp");
    }

    // 注册方法
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 验证用户名和密码是否为空
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // 返回错误信息
            response.sendError(400, "Username and password are required");
            return;
        }
        // 调用数据库操作类，查询用户名是否已存在
        UserDao userDao = new UserDao();
        boolean exists = userDao.checkUsernameExists(username);
        // 判断用户名是否已存在
        if (exists) {
            // 返回错误信息
            response.sendError(409, "Username already exists");
            return;
        }
        // 调用数据库操作类，插入新用户数据
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDao.insertUser(user);
        // 将用户对象存入session中
        request.getSession().setAttribute("user", user);
        // 重定向到主页
        response.sendRedirect("index.jsp");
    }
}

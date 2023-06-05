package com.example.picforum;

import com.example.picforum.dao.PostInfoDao;
import com.example.picforum.model.PostInfo;
import com.example.picforum.model.PostInfoReply;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/post")
public class PostInfoServlet extends HttpServlet {

    private PostInfoDao dao;  // 声明数据库操作类对象

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new PostInfoDao();  // 创建数据库操作类对象
    }

    @Override
    public void destroy() {
        super.destroy();
        dao = null;  // 销毁数据库操作类对象
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,
            IOException {

        request.setCharacterEncoding("UTF-8");  // 设置请求编码为UTF-8

        String action = request.getParameter("action");  // 获取请求参数action

        switch (action) {
            case "list":   // 如果action为list，则调用列表方法
                list(request, response);
                break;
            case "detail":   // 如果action为detail，则调用详情方法
                detail(request, response);
                break;
            case "reply":   // 如果action为reply，则调用回复方法
                reply(request, response);
                break;
            case "add":   // 如果action为add，则调用添加方法
                add(request, response);
                break;
            case "delete":   // 如果action为delete，则调用删除方法
                delete(request, response);
                break;
            default:
                response.sendError(404, "Invalid action");  // 否则返回错误信息
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException,
            IOException {
        doGet(request, response);  // 调用doGet方法
    }

    // 列表方法
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前页码参数，默认为1
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        // 获取每页显示的记录数参数，默认为10
        int size = 10;
        String sizeStr = request.getParameter("size");
        if (sizeStr != null && !sizeStr.isEmpty()) {
            try {
                size = Integer.parseInt(sizeStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        // 获取分类参数，默认为all
        String type = "all";
        String typeStr = request.getParameter("type");
        if (typeStr != null && !typeStr.isEmpty()) {
            type = typeStr;
        }
        // 调用数据库操作类，查询帖子列表数据
        List<PostInfo> postList = dao.getPostListByPageAndType(page, size, type);
        // 将帖子列表数据存入request中
        request.setAttribute("postList", postList);
        // 转发到列表页面
        request.getRequestDispatcher("post_list.jsp").forward(request, response);
        //System.out.println(postList);
    }

    // 详情方法
    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取帖子id参数
        String pidStr = request.getParameter("pid");
        // 验证帖子id是否为空或非数字
        if (pidStr == null || pidStr.isEmpty()) {
            // 返回错误信息
            response.sendError(400, "Post id is required");
            return;
        }
        int pid = 0;
        try {
            pid = Integer.parseInt(pidStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // 返回错误信息
            response.sendError(400, "Post id must be a number");
            return;
        }
        // 调用数据库操作类，查询帖子详细数据
        PostInfo post = dao.getPostByPid(pid);
        // 判断帖子是否存在
        if (post == null) {
            // 返回错误信息
            response.sendError(404, "Post not found");
            return;
        }
        // 调用数据库操作类，查询帖子被回复的数据
        List<PostInfoReply> replyList = dao.getPostReplyByPid(pid);
        // 将帖子详细数据和回复数据存入request中
        request.setAttribute("post", post);
        request.setAttribute("replyList", replyList);
        // 转发到详情页面
        request.getRequestDispatcher("post_detail.jsp").forward(request, response);
    }

    // 回复方法
    private void reply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取帖子id参数
        String pidStr = request.getParameter("pid");
        // 验证帖子id是否为空或非数字
        if (pidStr == null || pidStr.isEmpty()) {
            // 返回错误信息
            response.sendError(400, "Post id is required");
            return;
        }
        int pid = 0;
        try {
            pid = Integer.parseInt(pidStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // 返回错误信息
            response.sendError(400, "Post id must be a number");
            return;
        }
        // 获取回复内容参数
        String content = request.getParameter("content");
        // 验证回复内容是否为空或过长（超过1000个字符）
        if (content == null || content.isEmpty() || content.length() > 1000) {
            // 返回错误信息
            response.sendError(400, "Reply content is invalid");
            return;
        }
        // 获取发帖用户id参数（这里假设已经登录，从session中获取）
        int postUid = (int) request.getSession().getAttribute("uid");
        // 获取发帖用户名参数（这里假设已经登录，从session中获取）
        String postName = (String) request.getSession().getAttribute("username");
        // 封装PostInfoReply对象
        PostInfoReply reply = new PostInfoReply();
        reply.setPid(pid);
        reply.setContent(content);
        reply.setPostUid(postUid);
        reply.setPostName(postName);
        // 调用数据库操作类，添加回复数据，返回是否成功的标志
        boolean result = dao.addNewReply(reply);
        if (result) {
            // 如果成功，重定向到详情页面（传递帖子id参数）
            response.sendRedirect("post?action=detail&pid=" + pid);
        } else {
            // 如果失败，返回错误信息
            response.sendError(500, "Reply failed");
        }
    }

    // 添加方法
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取帖子标题参数
        String title = request.getParameter("title");
        // 验证帖子标题是否为空或过长（超过50个字符）
        if (title == null || title.isEmpty() || title.length() > 50) {
            // 返回错误信息
            response.sendError(400, "Post title is invalid");
            return;
        }
        // 获取帖子内容参数
        String content = request.getParameter("content");
        // 验证帖子内容是否为空或过长（超过1000个字符）
        if (content == null || content.isEmpty() || content.length() > 1000) {
            // 返回错误信息
            response.sendError(400, "Post content is invalid");
            return;
        }
        // 获取帖子类型参数
        String type = request.getParameter("type");
        // 验证帖子类型是否为show或apo
        if (type == null || type.isEmpty() || !(type.equals("show") || type.equals("apo"))) {
            // 返回错误信息
            response.sendError(400, "Post type is invalid");
            return;
        }
        // 获取发帖用户id参数（这里假设已经登录，从session中获取）
        int postUid = (int) request.getSession().getAttribute("uid");
        // 获取发帖用户名参数（这里假设已经登录，从session中获取）
        String postName = (String) request.getSession().getAttribute("username");
        // 封装PostInfo对象
        PostInfo post = new PostInfo();
        post.setTitle(title);
        post.setContent(content);
        post.setType(type);
        post.setPostUid(postUid);
        post.setPostName(postName);
        // 调用数据库操作类，添加新帖子数据，返回是否成功的标志
        boolean result = dao.addNewPost(post);
        if (result) {
            // 如果成功，重定向到列表页面（传递分类参数）
            response.sendRedirect("post?action=list&type=" + type);
        } else {
            // 如果失败，返回错误信息
            response.sendError(500, "Add failed");
        }
    }

    // 删除方法
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取帖子id参数
        String pidStr = request.getParameter("pid");
        // 验证帖子id是否为空或非数字
        if (pidStr == null || pidStr.isEmpty()) {
            // 返回错误信息
            response.sendError(400, "Post id is required");
            return;
        }
        int pid = 0;
        try {
            pid = Integer.parseInt(pidStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // 返回错误信息
            response.sendError(400, "Post id must be a number");
            return;
        }
        // 调用数据库操作类，删除帖子数据，返回是否成功的标志
        boolean result = dao.deletePostByPid(pid);
        if (result) {
            // 如果成功，重定向到列表页面（不传递分类参数，默认为all）
            response.sendRedirect("post?action=list");
        } else {
            // 如果失败，返回错误信息
            response.sendError(500, "Delete failed");
        }
    }

    public void setDao(PostInfoDao dao) {
        this.dao = dao;
    }
}
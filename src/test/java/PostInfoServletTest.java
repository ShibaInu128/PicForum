///*
//import com.example.picforum.PostInfoServlet;
//import com.example.picforum.dao.PostInfoDao;
//import com.example.picforum.model.PostInfo;
//import com.example.picforum.model.PostInfoReply;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PostInfoServletTest {
//
//    @Mock
//    private HttpServletRequest request;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @Mock
//    private HttpSession session;
//
//    @Mock
//    private ServletConfig config;
//
//    @Mock
//    private ServletContext context;
//
//    @Mock
//    private RequestDispatcher dispatcher;
//
//    @Mock
//    private PostInfoDao dao;
//
//    private PostInfoServlet servlet;
//
//    @Before
//    public void setUp() throws ServletException {
//        servlet = new PostInfoServlet();
//        servlet.init(config);
//        servlet.setDao(dao);
//        Mockito.when(request.getSession()).thenReturn(session);
//        Mockito.when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
//        Mockito.when(config.getServletContext()).thenReturn(context);
//        Mockito.when(context.getContextPath()).thenReturn("/picforum");
//    }
//
//    @Test
//    public void testList() throws Exception {
//        Mockito.when(request.getParameter("action")).thenReturn("list");
//        Mockito.when(request.getParameter("page")).thenReturn("1");
//        Mockito.when(request.getParameter("size")).thenReturn("10");
//        Mockito.when(request.getParameter("type")).thenReturn("all");
//
//        List<PostInfo> postList = new ArrayList<>();
//        postList.add(new PostInfo(1, "Test title", "Test content", "show", 1, "Test user"));
//        postList.add(new PostInfo(2, "Another title", "Another content", "apo", 2, "Another user"));
//
//        Mockito.when(dao.getPostListByPageAndType(1, 10, "all")).thenReturn(postList);
//
//        servlet.doGet(request, response);
//
//        Mockito.verify(request).setAttribute("postList", postList);
//        Mockito.verify(request).getRequestDispatcher("post_list.jsp");
//        Mockito.verify(dispatcher).forward(request, response);
//    }
//
//    @Test
//    public void testDetail() throws Exception {
//        Mockito.when(request.getParameter("action")).thenReturn("detail");
//        Mockito.when(request.getParameter("pid")).thenReturn("1");
//
//        PostInfo post = new PostInfo(1, "Test title", "Test content", "show", 1, "Test user");
//        List<PostInfoReply> replyList = new ArrayList<>();
//        replyList.add(new PostInfoReply(1, 1, "Test reply", 2, "Another user"));
//
//        Mockito.when(dao.getPostByPid(1)).thenReturn(post);
//        Mockito.when(dao.getPostReplyByPid(1)).thenReturn(replyList);
//
//        servlet.doGet(request, response);
//
//        Mockito.verify(request).setAttribute("post", post);
//        Mockito.verify(request).setAttribute("replyList", replyList);
//        Mockito.verify(request).getRequestDispatcher("post_detail.jsp");
//        Mockito.verify(dispatcher).forward(request, response);
//    }
//
//    @Test
//    public void testReply() throws Exception {
//        Mockito.when(request.getParameter("action")).thenReturn("reply");
//        Mockito.when(request.getParameter("pid")).thenReturn("1");
//        Mockito.when(request.getParameter("content")).thenReturn("Test reply");
//        Mockito.when(session.getAttribute("uid")).thenReturn(2);
//        Mockito.when(session.getAttribute("username")).thenReturn("Another user");
//
//        PostInfoReply reply = new PostInfoReply();
//        reply.setPid(1);
//        reply.setContent("Test reply");
//        reply.setPostUid(2);
//        reply.setPostName("Another user");
//
//        Mockito.when(dao.addNewReply(reply)).thenReturn(true);
//
//        servlet.doGet(request, response);
//
//        Mockito.verify(response).sendRedirect("/picforum/post?action=detail&pid=1");
//    }
//
//    @Test
//    public void testAdd() throws Exception {
//        Mockito.when(request.getParameter("action")).thenReturn("add");
//        Mockito.when(request.getParameter("title")).thenReturn("New title");
//        Mockito.when(request.getParameter("content")).thenReturn("New content");
//        Mockito.when(request.getParameter("type")).thenReturn("show");
//        Mockito.when(session.getAttribute("uid")).thenReturn(3);
//        Mockito.when(session.getAttribute("username")).thenReturn("New user");
//
//        PostInfo post = new PostInfo();
//        post.setTitle("New title");
//        post.setContent("New content");
//        post.setType("show");
//        post.setPostUid(3);
//        post.setPostName("New user");
//
//        Mockito.when(dao.addNewPost(post)).thenReturn(true);
//
//        servlet.doGet(request, response);
//
//        Mockito.verify(response).sendRedirect("/picforum/post?action=list&type=show");
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        // 模拟请求参数
//        Mockito.when(request.getParameter("action")).thenReturn("delete");
//        Mockito.when(request.getParameter("pid")).thenReturn("1");
//
//        // 模拟dao方法返回true
//        Mockito.when(dao.deletePostByPid(1)).thenReturn(true);
//
//        // 调用servlet的doGet方法，传入请求和响应
//        servlet.doGet(request, response);
//
//        // 验证响应重定向
//        Mockito.verify(response).sendRedirect("/picforum/post?action=list");
//    }
//
//    @Test
//    public void testInit() throws Exception {
//        // 模拟配置和dao
//        Mockito.when(config.getServletContext()).thenReturn(context);
//        Mockito.when(context.getAttribute("postInfoDao")).thenReturn(null);
//
//        // 调用servlet的init方法，传入配置
//        servlet.init(config);
//
//        // 验证上下文属性被设置
//        Mockito.verify(context).setAttribute(Mockito.eq("postInfoDao"), Mockito.any(PostInfoDao.class));
//    }
//}*/

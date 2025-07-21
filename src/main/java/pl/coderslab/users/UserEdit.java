package pl.coderslab.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.User;
import pl.coderslab.UserDao;

import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            UserDao userDao = new UserDao();
            User user = userDao.read(id); // implement read(int id) in UserDao

            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/users/edit.jsp")
                    .forward(request, response);
        } else {
            // handle missing id
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(id,username,email,password);
        UserDao userDao = new UserDao();
        userDao.update(newUser);

        response.sendRedirect(request.getContextPath() + "/user/list");
    }
}
 
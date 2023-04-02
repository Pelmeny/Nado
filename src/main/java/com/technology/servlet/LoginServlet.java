package com.technology.servlet;

import com.technology.model.User;
import com.technology.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        try {
            if (userService.validate(name, password)) {
                Long signedInUserId = userService.findUserIdByName(name);
                request.getSession().setAttribute("signedInUserId", signedInUserId);

                List<User> suggestedFriends = userService.findSuggestedFriends(signedInUserId);
                request.setAttribute("suggestedFriends", suggestedFriends);
                request.getServletContext().getRequestDispatcher("/suggestedFriends.jsp").forward(request, response);
            } else {
                response.sendRedirect("registration");
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}

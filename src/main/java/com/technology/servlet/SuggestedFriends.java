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
import java.util.List;

@WebServlet(urlPatterns = "/suggestedFriends")
public class SuggestedFriends extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Long signedInUserId = (Long) request.getSession().getAttribute("signedInUserId");

        try {
            List<User> suggestedFriends = userService.findSuggestedFriends(signedInUserId);
            request.setAttribute("suggestedFriends", suggestedFriends);
            request.getServletContext().getRequestDispatcher("/suggestedFriends.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

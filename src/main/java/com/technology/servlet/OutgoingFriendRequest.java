package com.technology.servlet;

import com.technology.model.User;
import com.technology.service.FriendRequestService;
import com.technology.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/outgoingRequests")
public class OutgoingFriendRequest extends HttpServlet {
    private UserService userService;
    private FriendRequestService friendRequestService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        friendRequestService =(FriendRequestService) config.getServletContext().getAttribute("friendRequestsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Long senderId = (Long) request.getSession().getAttribute("signedInUserId");

        try {
            List<User> outgoingRequests = userService.findOutgoingRequestsList(senderId);
            request.setAttribute("outgoingRequests", outgoingRequests);
            request.getServletContext().getRequestDispatcher("/outgoingRequests.jsp").forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }


}

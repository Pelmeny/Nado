package com.technology.servlet;

import com.technology.service.FriendRequestService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/createFriendRequest")
public class CreateFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        friendRequestService = (FriendRequestService) config.getServletContext().getAttribute("friendRequestService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Long senderId = (Long) request.getSession().getAttribute("signedInUserId");
        Long recipientId = Long.parseLong(request.getParameter("requestFriendId"));

        try {
            if (!friendRequestService.isRequestExists(senderId, recipientId)) {
                friendRequestService.createFriendRequest(senderId, recipientId);
            }
            response.sendRedirect("./suggestedFriends");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

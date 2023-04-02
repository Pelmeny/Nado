package com.technology.servlet;

import com.technology.model.User;
import com.technology.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    userService = (UserService) config.getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String name = request.getParameter("name");
    final String password = request.getParameter("password");

    userService.addUser(name, password);
    response.sendRedirect("./suggestedFriends");
  }
}

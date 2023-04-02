package com.technology.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.technology.repository.FriendRequestRepository;
import com.technology.repository.JdbcFriendRequestRepository;
import com.technology.repository.JdbcUserRepository;
import com.technology.repository.UserRepository;
import com.technology.service.FriendRequestService;
import com.technology.service.UserService;

@WebListener
public class DependencyInitializationContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(final ServletContextEvent sce) {
    final String dbDriver = "org.postgresql.Driver";
    final String username = sce.getServletContext().getInitParameter("db_user");
    final String password = sce.getServletContext().getInitParameter("db_password");
    final String dbUrl = sce.getServletContext().getInitParameter("db_url");

    try {
      Class.forName(dbDriver);
      final Connection connection = DriverManager.getConnection(dbUrl, username, password);

      UserRepository userRepository = new JdbcUserRepository(connection);
      UserService userService = new UserService(userRepository);
      sce.getServletContext().setAttribute("userService", userService);

      FriendRequestRepository friendRequestRepository = new JdbcFriendRequestRepository(connection);
      FriendRequestService friendRequestService = new FriendRequestService(friendRequestRepository);
      sce.getServletContext().setAttribute("friendRequestService", friendRequestService);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    try {
      final Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

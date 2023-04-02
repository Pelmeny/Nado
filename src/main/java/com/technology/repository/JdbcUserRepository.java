package com.technology.repository;

import com.technology.model.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;


public class JdbcUserRepository implements UserRepository {
  private static final String CREATE_USER = "INSERT INTO users(name, password) VALUES (?, ?)";
  private static final String FIND_USERS = "SELECT * FROM users";
  private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
  private static final String FIND_USER_BY_NAME = "SELECT * FROM users WHERE name=?";
  private static final String VALIDATE_USER = "SELECT * FROM users WHERE name=? AND password=?";
  private static final String SUGGESTED_FRIENDS = "SELECT * FROM users WHERE id!=? AND id NOT IN (SELECT second_friend_id FROM friends WHERE first_friend_id=?)";

  private final Connection connection;

  public JdbcUserRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void addUser(String name, String password) {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
      statement.setString(1, name);
      statement.setString(2, password);
      statement.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean validate(String name, String password) {
    try (PreparedStatement statement = connection.prepareStatement(VALIDATE_USER)) {
      statement.setString(1, name);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();

      return resultSet.next();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<User> findUsers() {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(FIND_USERS);
      final List<User> users = new ArrayList<>();

      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }

      return users;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<User> findSuggestedFriends(Long signedUserId) {
    try (PreparedStatement statement = connection.prepareStatement(SUGGESTED_FRIENDS)) {
      statement.setLong(1, signedUserId);
      statement.setLong(2, signedUserId);
      ResultSet resultSet = statement.executeQuery();
      final List<User> users = new ArrayList<>();

      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }

      return users;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public User findUserById(Long id) {
    try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return buildUser(resultSet);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  @Override
  public User findUserByName(String name) {
    try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_NAME)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return buildUser(resultSet);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  @Override
  public Long findUserIdByName(String name) {
    try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_NAME)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return buildUser(resultSet).getId();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  private User buildUser(ResultSet resultSet) throws SQLException {
    return new User(
        resultSet.getLong("id"),
        resultSet.getString("name"),
        resultSet.getString("password")
    );
  }
}

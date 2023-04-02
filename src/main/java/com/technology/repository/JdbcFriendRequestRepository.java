package com.technology.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcFriendRequestRepository implements FriendRequestRepository {
    private static final String CREATE_REQUEST = "INSERT INTO requests(sender_id, recipient_id) VALUES(?,?)";
    private static final String DELETE_REQUEST = "DELETE FROM requests WHERE sender_id=? AND recipient_id=?";
    private static final String EXIST_REQUEST = "SELECT * FROM requests WHERE sender_id=? AND recipient_id=?";
    private Connection connection;

    public JdbcFriendRequestRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createFriendRequest(Long senderId, Long recipientId) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_REQUEST)) {
            statement.setLong(1, senderId);
            statement.setLong(2, recipientId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFriendRequest(Long senderId, Long recipientId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST)) {
            statement.setLong(1, senderId);
            statement.setLong(2, recipientId);
            statement.executeUpdate();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isRequestExists(Long senderId, Long recipientId) {
        try (PreparedStatement statement = connection.prepareStatement(EXIST_REQUEST)){
            statement.setLong(1, senderId);
            statement.setLong(2, recipientId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }
}

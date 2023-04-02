package com.technology.repository;

import java.util.List;

import com.technology.model.User;

public interface UserRepository {
  void addUser(String name, String password);

  boolean validate(String name, String password);

  List<User> findUsers();

  List<User> findSuggestedFriends(Long signedUserId);

  List<User> findOutgoingRequestsList(Long senderId);

  List<User> findIncomingRequestsList(Long recipientId);

  User findUserById(Long id);

  User findUserByName(String name);

  Long findUserIdByName(String name);

}

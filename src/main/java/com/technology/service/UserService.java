package com.technology.service;

import com.technology.model.User;
import com.technology.repository.UserRepository;

import java.util.List;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void addUser(String name, String password) {
      userRepository.addUser(name, password);
  }

  public boolean validate(String name, String password) {
    return userRepository.validate(name, password);
  }

  public List<User> findUsers() {
    return userRepository.findUsers();
  }

  public List<User> findSuggestedFriends(Long signedUserId){
    return userRepository.findSuggestedFriends(signedUserId);
  }

  public User findUserById(Long id){
    return userRepository.findUserById(id);
  }

  public User findUserByName(String name){
    return userRepository.findUserByName(name);
  }

  public Long findUserIdByName(String name){
    return userRepository.findUserIdByName(name);
  }

}

package com.technology.repository;

public interface FriendRequestRepository {

    void createFriendRequest(Long senderId, Long recipientId);

    void deleteFriendRequest(Long senderId, Long recipientId);
    boolean isRequestExists(Long senderId, Long recipientId);

}

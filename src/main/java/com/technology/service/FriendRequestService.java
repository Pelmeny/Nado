package com.technology.service;

import com.technology.repository.FriendRequestRepository;

public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    public void createFriendRequest(Long senderId, Long recipientId) {
        friendRequestRepository.createFriendRequest(senderId, recipientId);
    }

    public void deleteFriendRequest(Long senderId, Long recipientId) {
        friendRequestRepository.deleteFriendRequest(senderId, recipientId);
    }

    public boolean isRequestExists(Long senderId, Long recipientId) {
        return friendRequestRepository.isRequestExists(senderId, recipientId);
    }
}

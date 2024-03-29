package com.shahryarkiani.chatbackend.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    record friendData (Set<String> friends, Set<String> incomingRequests, Set<String> outgoingRequests) {}

    @Query("{ _id: ?0  }, { friends: true, incomingRequests : true, outgoingRequests : true, _id : false }")
    Optional<friendData> getFriendsById(String id);

    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);


}

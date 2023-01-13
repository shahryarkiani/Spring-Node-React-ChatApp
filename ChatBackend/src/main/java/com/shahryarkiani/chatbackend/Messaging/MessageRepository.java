package com.shahryarkiani.chatbackend.Messaging;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{ $or : [ {$and:[{to: ?0}, {from : ?1}]}, {$and: [{to: ?1}, {from:?0}]}] }")
    List<Message> getMessagesBetweenUseres(String user1, String user2);


    //{ $or : [ {$and:[{to: 'bob'}, {from : 'joe'}]}, {$and: [{to: 'joe'}, {from:'bob'}]}] }
}

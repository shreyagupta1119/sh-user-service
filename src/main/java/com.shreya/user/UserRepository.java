package com.shreya.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import sun.nio.cs.US_ASCII;

import java.util.List;

/**
 * Created by shreya on 1/12/16.
 */
@Repository
public class UserRepository {

    public static final String COLLECTION_NAME = "user";

    @Autowired MongoTemplate mongoTemplate;

    public List<UserData> getAllUsers(){
        return mongoTemplate.findAll(UserData.class);
    }

    public UserData getUserByContactNumber(String number){
        return mongoTemplate.findOne(Query.query(Criteria.where("contactNumber").is(number)), UserData.class);
    }

    public List<UserData> getUserByName(String name){
        return mongoTemplate.find(Query.query(Criteria.where("name").regex(name, "i")), UserData.class);
    }

    public List<UserData> getOnlineUsers(){
        return mongoTemplate.find(Query.query(Criteria.where("online").is("true")), UserData.class);
    }

    public void addUser(UserData user1){
        if (!mongoTemplate.collectionExists(UserData.class))
            mongoTemplate.createCollection(UserData.class);

        mongoTemplate.insert(user1);
    }

    public UserData updateUser(String number, UserData user1){
        UserData myUser=mongoTemplate.findOne(Query.query(Criteria.where("contactNumber").is(number)),UserData.class);

        if (myUser != null) {
            myUser.setContactNumber(user1.getContactNumber());
            myUser.setName(user1.getName());
            myUser.setPassword(user1.getPassword());
            myUser.setFriends(user1.getFriends());
            myUser.setNumberOfDraw(user1.getNumberOfDraw());
            myUser.setNumberOfLose(user1.getNumberOfLose());
            myUser.setNumberOfWins(user1.getNumberOfWins());
            myUser.setOnline(user1.getOnline());

            mongoTemplate.save(myUser);
            return myUser;

        }
        else
            return null;
    }

    public UserData deleteUser(String number){
        UserData user1=mongoTemplate.findOne(Query.query(Criteria.where("contactNumber").is(number)),UserData.class);
        mongoTemplate.remove(user1);
        return user1;
    }
}



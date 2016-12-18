package com.shreya.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
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
        return mongoTemplate.find(Query.query(Criteria.where("online").is(true)), UserData.class);
    }

    public void addUser(UserData user1){
        if (!mongoTemplate.collectionExists(UserData.class))
            mongoTemplate.createCollection(UserData.class);

        mongoTemplate.insert(user1);
    }

    public UserData updateUser(String number, UserData user1){
        Update update=new Update();
        if(!StringUtils.isEmpty(user1.getName()))
            update.set("name",user1.getName());
        if(!StringUtils.isEmpty(user1.getPassword()))
            update.set("password",user1.getPassword());
        if(!StringUtils.isEmpty(user1.getFriends()))
            update.set("friends",user1.getFriends());
        if(!StringUtils.isEmpty(user1.getNumberOfDraw()))
            update.set("numberOfDraw",user1.getNumberOfDraw());
        if(!StringUtils.isEmpty(user1.getNumberOfLose()))
            update.set("numberOfLose",user1.getNumberOfLose());
        if(!StringUtils.isEmpty(user1.getNumberOfWins()))
            update.set("numberOfWins",user1.getNumberOfWins());
        if(!StringUtils.isEmpty(user1.getOnline()))
            update.set("online",user1.getOnline());

        return mongoTemplate.findAndModify(Query.query(Criteria.where("contactNumber").is(number)),update, new FindAndModifyOptions().returnNew(true),UserData.class);
    }

    public UserData deleteUser(String number){
        UserData user1=mongoTemplate.findOne(Query.query(Criteria.where("contactNumber").is(number)),UserData.class);
        mongoTemplate.remove(user1);
        return user1;
    }
}



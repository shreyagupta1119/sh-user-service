package com.shreya.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by shreya on 1/12/16.
 */
@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public UserService(){}

    public List<UserData> getAllUsers(){
        List<UserData> allUsers=userRepository.getAllUsers();
        return allUsers;
    }

    public UserData getUserByContactNumber(String number){
        return userRepository.getUserByContactNumber(number);
    }

    public List<UserData> getUserByName(String name){
        return userRepository.getUserByName(name);
    }

    public List<UserData> getOnlineUsers() {return userRepository.getOnlineUsers();}

    public List<UserData> getUserFriendsList(String number) {
      UserData user1=getUserByContactNumber(number);
      return user1.getFriends();
    }

    public void addUser(UserData user1){
        userRepository.addUser(user1);
    }

    public UserData updateUser(String number, UserData user1){
        return userRepository.updateUser(number,user1);
    }

    public UserData deleteUser(String number){
        return userRepository.deleteUser(number);
    }

    public List<UserData> getOnlineFriends(String number){
       List<UserData> users1=getUserFriendsList(number);
       List<UserData> users2=getOnlineUsers();
       List<UserData> users=new ArrayList<UserData>();
       for(UserData user:users1){
           if(users2.contains(user))
               users.add(user);
       }
        return users;
    }

}

package com.shreya.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
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
        user1.setNumberOfDraw("0");user1.setNumberOfLose("0");user1.setNumberOfWins("0");
        user1.setOnline(false);
        user1.setFriends(new ArrayList<UserData>());
        userRepository.addUser(user1);
    }

    public UserData updateUser(String number, UserData user1){
        if(!StringUtils.isEmpty(user1.getFriends()))
        user1.setFriends(setFriends(user1.getFriends(),number));
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

    public Boolean checkFriends(List<UserData> friends){
        List<UserData> friendUsers=new ArrayList<UserData>();
        for(UserData usertemp:friends){
            UserData user1=getUserByContactNumber(usertemp.getContactNumber());
            if(user1!=null ){
                continue;
            }
            else
                return false;
        }
        return true;
    }

    public List<UserData> setFriends(List<UserData> friends,String number){
        List<UserData> friendUsers=new ArrayList<UserData>();
        for(UserData user:friends) {
            UserData user1 = getUserByContactNumber(user.getContactNumber());
            if (user1 != null) {
                friendUsers.add(user1);

            }
        }
        friendUsers.remove(getUserByContactNumber(number));
        return friendUsers;
    }

}

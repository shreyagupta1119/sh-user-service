package com.shreya.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shreya on 1/12/16.
 */

@Data
@Document(collection="user")
public class UserData {

    @Id
    private String contactNumber;
    private String name;
    private String password;
    private String numberOfWins;
    private String  numberOfLose;
    private String  numberOfDraw;
    private Boolean online;
    private List<UserData> friends;

    public UserData(){}

    public UserData(String contactNumber, String name, String password) {
        this.contactNumber = contactNumber;
        this.name = name;
        this.password = password;
        this.numberOfWins = "0";
        this.numberOfLose = "0";
        this.numberOfDraw = "0";
        this.online = false;
        this.friends = new ArrayList<UserData>();
    }

    @Override
    public String toString() {
        return "User [ contactnumber=" + contactNumber + ", name =" + name + ", password=" + password + ", numberOfWins=" +
                Integer.parseInt(numberOfWins) + ", numberOflose=" + Integer.parseInt(numberOfLose) + ", numberOfdraws=" +
                Integer.parseInt(numberOfDraw) +  ", player online status= " + online + ", friends=" + friends;
   }

}


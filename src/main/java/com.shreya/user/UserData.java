package com.shreya.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static sun.plugin.javascript.navig.JSType.Document;


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
    private int numberOfWins;
    private int numberOfLose;
    private int numberOfDraw;
    private Boolean online;
    private List<UserData> friends;

    public UserData(){}

    public UserData(String contactNumber, String name, String password) {
        this.contactNumber = contactNumber;
        this.name = name;
        this.password = password;
        this.numberOfWins = 0;
        this.numberOfLose = 0;
        this.numberOfDraw = 0;
        this.online = true;
        this.friends = null;
    }

    @Override
    public String toString() {
        return "player [ contactnumber=" + contactNumber + ", name =" + name + ", password=" + password + ", numberOfWins=" +
                numberOfWins + ", numberOflose=" + numberOfLose + ", numberOfdraws=" + numberOfDraw + ", total match played=" +
                numberOfWins + numberOfLose + numberOfDraw + ", player online status= " + online + ", friends=" + friends;
    }

}


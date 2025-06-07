package com.drawingPlayer.org.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Drawing> drawings;

    public User(){
        this.drawings = new ArrayList<Drawing>();
        this.userName = null;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public List<Drawing> getDrawings() {
        return drawings;
    }

    public void setDrawings(List<Drawing> drawings) {
        this.drawings = drawings;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

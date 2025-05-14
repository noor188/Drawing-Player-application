package com.drawingPlayer.org.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    private Integer userID;

    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Drawing> drawings;

    public User(){
        this.drawings = new ArrayList<Drawing>();
        this.userName = null;
    }

}

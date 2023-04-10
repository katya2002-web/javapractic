package com.example.demo.entity;

public class Employer {
    private Long Id;
    private String  Name;
    private String Position;
    private int Expirience;

    public Employer() {
    }

    public Employer(Long id, String name, String position, int expirience) {
        Id = id;
        Name = name;
        Position = position;
        Expirience = expirience;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getExpirience() {
        return Expirience;
    }

    public void setExpirience(int expirience) {
        Expirience = expirience;
    }
}


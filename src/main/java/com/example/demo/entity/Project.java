package com.example.demo.entity;

public class Project {
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public Project() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

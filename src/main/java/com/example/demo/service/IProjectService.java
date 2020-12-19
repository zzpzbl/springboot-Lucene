package com.example.demo.service;

import com.example.demo.entity.Project;

import java.util.List;

public interface IProjectService {

    public Project getProjectById(String id);

    public List<Project> getAllProject();

}

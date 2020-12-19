package com.example.demo.service.Impl;

import com.example.demo.Dao.ILuceneDao;
import com.example.demo.entity.Project;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectMapper mapper;

    @Autowired
    private ILuceneDao luceneDao;


    @Override
    public Project getProjectById(String id) {
        return mapper.getProjectById(id);
    }

    @Override
    public List<Project> getAllProject() {
        return mapper.getAllProject();
    }
}

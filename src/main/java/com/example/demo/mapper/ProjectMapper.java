package com.example.demo.mapper;

import com.example.demo.entity.PageQuery;
import com.example.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProjectMapper {

    public Project getProjectById(String id);

    public List<Project> getAllProject();

}

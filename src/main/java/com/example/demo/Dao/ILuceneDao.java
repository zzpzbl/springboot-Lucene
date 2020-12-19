package com.example.demo.Dao;

import com.example.demo.entity.PageQuery;
import com.example.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

@Mapper
public interface ILuceneDao {

    public void createProjectIndex(List<Project> projectList) throws IOException;

    public PageQuery<Project> searchProject(PageQuery<Project> pageQuery) throws IOException, ParseException;

    public void addProjectIndex(Project project) throws IOException;

    public List<Project> searchByName(String name) throws IOException, ParseException;
    /**
     * 通过id删除商品索引
     * @param id
     * @throws IOException
     */
    public void deleteProjectIndexById(int id) throws IOException;

}

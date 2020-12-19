package com.example.demo.service;

import com.example.demo.entity.PageQuery;
import com.example.demo.entity.Project;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

public interface ILuceneService {

    public void synProjectCreateIndex() throws IOException;

    public PageQuery<Project> searchProject(PageQuery<Project> pageQuery) throws IOException, ParseException;

    public List<Project> searchByName(String name) throws IOException, ParseException;
}

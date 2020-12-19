package com.example.demo.service.Impl;

import com.example.demo.Dao.ILuceneDao;
import com.example.demo.entity.PageQuery;
import com.example.demo.entity.Project;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.service.ILuceneService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LuceneServiceImpl implements ILuceneService {

    @Autowired
    private ILuceneDao luceneDao;

    @Autowired
    private ProjectMapper mapper;


    @Override
    public void synProjectCreateIndex() throws IOException {
        List<Project> allProject = mapper.getAllProject();
        luceneDao.createProjectIndex(allProject);
    }

    @Override
    public PageQuery<Project> searchProject(PageQuery<Project> pageQuery) throws IOException, ParseException {
        return luceneDao.searchProject(pageQuery);
    }

    @Override
    public List<Project> searchByName(String name) throws IOException, ParseException {
        return luceneDao.searchByName(name);
    }


}

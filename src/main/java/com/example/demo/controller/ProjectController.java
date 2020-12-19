package com.example.demo.controller;


import com.example.demo.Dao.ILuceneDao;
import com.example.demo.entity.PageQuery;
import com.example.demo.entity.Project;
import com.example.demo.entity.ResultBean;
import com.example.demo.service.ILuceneService;
import com.example.demo.utils.ResultUtil;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/project/search")
public class ProjectController {

    @Autowired
    private ILuceneService service;

    @PostMapping("/pageInfo")
    public ResultBean<PageQuery<Project>> searchProject(@RequestBody PageQuery<Project> pageQuery) throws IOException, ParseException {
        PageQuery<Project> pageResult = service.searchProject(pageQuery);
        return ResultUtil.success(pageResult);
    }

    @GetMapping("/helloProject")
    public String helloProduct() {
        return "Hello Product !";
    }

    @PostMapping("/name")
    public ResultBean<List<Project>> searchByName(@RequestBody String name) throws IOException, ParseException {
        List<Project> result = service.searchByName(name);
        return ResultUtil.success(result);
    }

}

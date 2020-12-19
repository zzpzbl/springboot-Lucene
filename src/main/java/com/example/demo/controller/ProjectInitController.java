package com.example.demo.controller;


import com.example.demo.entity.PageInfo;
import com.example.demo.entity.PageQuery;
import com.example.demo.entity.Project;
import com.example.demo.entity.ResultBean;
import com.example.demo.service.IProjectService;
import com.example.demo.utils.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectInitController {

    @Autowired
    private IProjectService service;

}

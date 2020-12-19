package com.example.demo.init;

import com.example.demo.entity.Project;
import com.example.demo.service.ILuceneService;
import com.sun.xml.internal.org.jvnet.fastinfoset.VocabularyApplicationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;

@Configuration
public class ProjectInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ILuceneService service;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            try {
                service.synProjectCreateIndex();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

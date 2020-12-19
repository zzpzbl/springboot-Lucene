package com.example.demo.Dao.Impl;

import com.example.demo.Dao.ILuceneDao;
import com.example.demo.entity.PageInfo;
import com.example.demo.entity.PageQuery;
import com.example.demo.entity.Project;
import org.apache.lucene.document.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "luceneDao")
public class LuceneDaoImpl implements ILuceneDao {

    @Autowired(required = false)
    private IndexWriter indexWriter;

    @Autowired
    private Analyzer analyzer;

    @Autowired
    private SearcherManager searcherManager;

    @Override
    public void createProjectIndex(List<Project> projectList) throws IOException {
        List<Document> docs = new ArrayList<Document>();
        for(Project p : projectList) {
            Document document = new Document();
            document.add(new Field("id", p.getId() + "", TextField.TYPE_STORED));
            document.add(new Field("name", p.getName() + "", TextField.TYPE_STORED));
            document.add(new Field("description", p.getDescription() + "", TextField.TYPE_STORED));
//            document.add(new StoredField("name", p.getName()));
            docs.add(document);
        }
        indexWriter.addDocuments(docs);
//        indexWriter.close();
        indexWriter.commit();
    }

    @Override
    public PageQuery<Project> searchProject(PageQuery<Project> pageQuery) throws IOException, ParseException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();
        Project params = pageQuery.getParams();
        Map<String, String> queryParam = pageQuery.getQueryParam();
        Builder builder = new BooleanQuery.Builder();
        Sort sort = new Sort();
        com.example.demo.entity.Sort sort1 = pageQuery.getSort();
        if(sort1 != null && sort1.getOrder() != null) {
            if("ASC".equals((sort1.getOrder()).toUpperCase())) {
                sort.setSort(new SortField(sort1.getField(), SortField.Type.STRING, false));
            } else if ("DESC".equals((sort1.getOrder()).toUpperCase())) {
                sort.setSort(new SortField(sort1.getField(), SortField.Type.STRING, true));
            }
        }
        if(params.getName() != null) {
            builder.add(new TermQuery(new Term("name", params.getName())), BooleanClause.Occur.MUST);
        }
        PageInfo pageInfo = pageQuery.getPageInfo();
        TopDocs topDocs = indexSearcher.search(builder.build(), pageInfo.getPageNum() * pageInfo.getPageSize(), sort);
        pageInfo.setTotal(topDocs.totalHits);
        ScoreDoc[] hits = topDocs.scoreDocs;
        List<Project> projectList = new ArrayList<Project>();
        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);
            System.out.println(doc.toString());
            Project project = new Project();
            project.setId(Integer.parseInt(doc.get("id")));
            project.setName(doc.get("name"));
            project.setDescription(doc.get("description"));

            projectList.add(project);
        }
        pageQuery.setResults(projectList);
        return pageQuery;
    }

    @Override
    public void addProjectIndex(Project project) throws IOException {
        Document document = new Document();
        document.add(new IntPoint("id", project.getId()));
        document.add(new StringField("name", project.getName() + "", Field.Store.YES));
        document.add(new StringField("description", project.getDescription() + "", Field.Store.YES));
        document.add(new StoredField("name", project.getName()));
        indexWriter.addDocument(document);
        indexWriter.commit();
    }

    @Override
    public List<Project> searchByName(String name) throws IOException, ParseException {
        searcherManager.maybeRefresh();
        List<Project> ret = new ArrayList<>();
        IndexSearcher indexSearcher = searcherManager.acquire();
        Analyzer analyzer = new IKAnalyzer();
        String[] fields = {"name", "description"};
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String line = name != null ? name : in.readLine();
        Query query = parser.parse(line);

//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("<font color=")

        TopDocs topDocs = indexSearcher.search(query, 10);
        for(ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Project project = new Project();
            project.setId(Integer.parseInt(document.get("id")));
            project.setName(document.get("name"));
            project.setDescription(document.get("description"));
            ret.add(project);
        }
        return ret;
    }

    @Override
    public void deleteProjectIndexById(int id) throws IOException {
        indexWriter.deleteDocuments(new Term("id", String.valueOf(id)));
        indexWriter.commit();
    }
}

package com.xyy.cache;

import com.xyy.cache.bean.elastic.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CachedemoApplication.class)
public class ElsticTest {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test1(){

        /*Item item = new Item();
        item.setId(1);
        item.setContent("港独灭亡之日");
        item.setTitle("新闻：香港是中国不可分割的一部分");
        item.setWriter("撒贝宁");*/
        elasticsearchTemplate.createIndex(Item.class);
        elasticsearchTemplate.getMapping(Item.class);
    }

}

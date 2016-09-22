package com.jomilanez.repository;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "integration")
public class ArticleRepositoryEmbeddedTest {

    @Autowired
    private ArticleRepository repository;

    private EmbeddedElasticsearchServer embeddedElasticsearchServer;

    @Test
    public void indexSimpleDocument() throws IOException {
        repository.save(new Article("id", "title", "text"));

        String indexName = "articles-" + ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        GetResponse fields = getClient().prepareGet(indexName, "article", "id").execute().actionGet();
        assertThat(fields.getSource().get("title")).isEqualTo("title");
        assertThat(fields.getSource().get("description")).isEqualTo("text");
    }

    @Before
    public void startEmbeddedElasticsearchServer() {
        embeddedElasticsearchServer = new EmbeddedElasticsearchServer();
    }

    @After
    public void shutdownEmbeddedElasticsearchServer() {
        embeddedElasticsearchServer.shutdown();
    }

    private Client getClient() {
        return embeddedElasticsearchServer.getClient();
    }

}
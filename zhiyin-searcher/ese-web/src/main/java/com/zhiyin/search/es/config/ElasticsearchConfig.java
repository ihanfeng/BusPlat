package com.zhiyin.search.es.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@PropertySource(value = "classpath:/config/es.properties")
@EnableElasticsearchRepositories(basePackages = "com.zhiyin.search.es")
public class ElasticsearchConfig {

    @Resource
    private Environment environment;

    @Bean
    @Profile({ "product" })
    public Client client() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new TransportClient(settings);
        TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"), Integer.parseInt(environment.getProperty("elasticsearch.port")));
        client.addTransportAddress(address);

        return client;
    }

    @Bean
    @Profile({ "product" })
    ElasticsearchOperations elasticsearchTemplate() throws IOException {
        System.out.println("ElasticsearchConfig start");
        return new ElasticsearchTemplate(client());
    }

    /**
     * 本地启动ES
     * http://localhost:9200/
     * @return
     */
    @Bean
    @Profile({ "test","default" })
    public Client localClient() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch")
                .put("http.enabled", "true")
                .build();
        String clusterName = UUID.randomUUID().toString();
        clusterName = "elasticsearch";
        NodeClient nodeClient = (NodeClient) nodeBuilder().settings(settings).clusterName(clusterName).local(true).node()
                .client();
        return nodeClient;
    }
    @Bean
    @Profile({ "test","default" })
    ElasticsearchOperations testElasticsearchTemplate() throws IOException {
        System.out.println("ElasticsearchConfig start");
        return new ElasticsearchTemplate(localClient());

    }


}
package com.zhiyin.search.es.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Slf4j
@Configuration
@PropertySource(value = "classpath:/config/es.properties")
@EnableElasticsearchRepositories(basePackages = "com.zhiyin.search.es")
public class ElasticsearchConfig {

    @Resource
    private Environment environment;

    @Bean
    @Profile({"product"})
    public Client client() {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        Client client = null;
        try {
            client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(
                    InetAddress.getByName(environment.getProperty("elasticsearch.host")),
                    Integer.parseInt(environment.getProperty("elasticsearch.port")) ));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

//        TransportClient client = new TransportClient(settings);
//        TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"), Integer.parseInt(environment.getProperty("elasticsearch.port")));
//        client.addTransportAddress(address);

        return client;
    }

    @Bean
    @Profile({"product"})
    ElasticsearchOperations elasticsearchTemplate() throws IOException {
        System.out.println("ElasticsearchConfig start");
        return new ElasticsearchTemplate(client());
    }

    /**
     * 本地启动ES
     * http://localhost:9200/
     *
     * @return
     */
    @Bean
    @Profile({"test", "default"})
    public Client localClient() {
        try {
            final Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");

            Settings settings = Settings.builder()
                    .put("cluster.name", "elasticsearch")
                    .put("http.enabled", "true") // 可以通过 http://localhost:9200/_search 访问ES
                    .put("path.data", tmpDir.toAbsolutePath().toString()) // 2
//                    .put("path.home", "/tmp") // 3
                    .build();

            log.info("es data path:" + tmpDir.toAbsolutePath().toString());

            String clusterName = UUID.randomUUID().toString();
            clusterName = "elasticsearch";
            NodeClient nodeClient = (NodeClient) nodeBuilder().settings(settings).clusterName(clusterName).local(true).node()
                    .client();
            return nodeClient;
        } catch (final IOException ioex) {
            log.error("Cannot create temp dir", ioex);
            throw new RuntimeException();
        }
    }

    @Bean
    @Profile({"test", "default"})
    ElasticsearchOperations testElasticsearchTemplate() throws IOException {
        System.out.println("ElasticsearchConfig start");
        return new ElasticsearchTemplate(localClient());

    }


}
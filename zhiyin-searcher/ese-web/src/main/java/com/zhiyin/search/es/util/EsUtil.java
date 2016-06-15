package com.zhiyin.search.es.util;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import java.io.*;
import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 *
 * http://my.oschina.net/chiyong/blog/552622
 * Created by wangqinghui on 2016/4/11.
 */
public class EsUtil {

    public static void importData(Client client,String fileName,String index,String type){
//        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
//        Client client = new TransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
//        NodeClient client = (NodeClient) nodeBuilder().clusterName(UUID.randomUUID().toString()).local(true).node()
//                .client();
        try {
            //读取刚才导出的ES数据
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String json = null;
            int count = 0;
            //开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            while ((json = br.readLine()) != null) {
                bulkRequest.add(client.prepareIndex(index,type).setSource(json));
                //每一千条提交一次
                if (count% 1000==0) {
                    bulkRequest.execute().actionGet();
                    System.out.println("提交了：" + count);
                }
                count++;
            }
            bulkRequest.execute().actionGet();
            System.out.println("插入完毕");
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void export(){
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch-bigdata").build();
        Client client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("10.58.71.6", 9300));
        SearchResponse response = client.prepareSearch("bigdata").setTypes("student")
                .setQuery(QueryBuilders.matchAllQuery()).setSize(10000).setScroll(new TimeValue(600000))
                .setSearchType(SearchType.SCAN).execute().actionGet();//setSearchType(SearchType.Scan) 告诉ES不需要排序只要结果返回即可 setScroll(new TimeValue(600000)) 设置滚动的时间
        String scrollid = response.getScrollId();
        try {
            //把导出的结果以JSON的格式写到文件里
            BufferedWriter out = new BufferedWriter(new FileWriter("es", true));

            //每次返回数据10000条。一直循环查询直到所有的数据都查询出来
            while (true) {
                SearchResponse response2 = client.prepareSearchScroll(scrollid).setScroll(new TimeValue(1000000))
                        .execute().actionGet();
                SearchHits searchHit = response2.getHits();
                //再次查询不到数据时跳出循环
                if (searchHit.getHits().length == 0) {
                    break;
                }
                System.out.println("查询数量 ：" + searchHit.getHits().length);
                for (int i = 0; i < searchHit.getHits().length; i++) {
                    String json = searchHit.getHits()[i].getSourceAsString();
                    out.write(json);
                    out.write("\r\n");
                }
            }
            System.out.println("查询结束");
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
//        importData();
    }
}

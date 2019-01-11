package com.simon.mysqltoelasticsearch.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2019/1/5 11:20
 **/
public class ESUtil {
    public static RestHighLevelClient restClient;
    private static int port = 9200;
    private static String hostName = "192.168.126.130";
    private static String userName = "";
    private static String password = "";
    private static String scheme = "http";

    static {
        init();
    }

    public static void init() {
        restClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostName, port, scheme)));
    }

    /**
     * 创建索引
     *
     * @param indexName
     * @return
     * @throws IOException
     */
    public static String createIndex(String indexName) throws IOException {
        if (StringUtils.isEmpty(indexName)) {
            return "索引为空";
        }
        if (isExist(indexName)) {
            return "索引已存在";
        }
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(hostName, port, scheme)));
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_shards", 5)); //# 有5个碎片

        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.toString() + "  " + response.isAcknowledged());
        return "OK";
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return
     * @throws IOException
     */
    public static boolean isExist(String... indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        return restClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建文档
     *
     * @param index
     * @param doc
     * @param id
     * @param data 需要保存的数据
     * @return String
     * @throws IOException
     */
    public static String createOneDocument(String index, String doc, String id,Object data) throws IOException {
        IndexRequest request = new IndexRequest(index, doc, id); // 这里最后一个参数是es里储存的id，如果不填，es会自动生成一个，个人建议跟自己的数据库表里id保持一致
        request.source(data);
        IndexResponse response = restClient.index(request, RequestOptions.DEFAULT);
        if (response.status().getStatus() != 200) {
            return "创建失败," + response.status().name();
        }
        return "OK";
    }

    /**
     * 异步批量保持诗句
     * @param index
     * @param doc
     * @param dataList 需要保存的数据
     * @return
     * @throws IOException
     */
    public static void createBulkDocument(String index, String doc,List dataList) throws NoSuchFieldException, IllegalAccessException {
        BulkRequest bulkRequest = new BulkRequest();
        if(dataList == null || dataList.isEmpty()){
            return;
        }
        for (int i = 0; i < dataList.size(); i++) {
            Object data = dataList.get(i);
            Field field = data.getClass().getDeclaredField("id");
            Object id = field.get(data);
            if(StringUtils.isEmpty(id)){
                throw new RuntimeException("id of dataList is null");
            }
            bulkRequest.add(new IndexRequest(index,doc,id.toString()).source(BeanMap.create(data)));
        }
        bulkRequest.timeout(TimeValue.timeValueSeconds(15));
        ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkResponse) {
                try {
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Exception e) {
                throw new RuntimeException("create document fail".concat(e.toString()));
            }
        };
        restClient.bulkAsync(bulkRequest,RequestOptions.DEFAULT,listener);
    }

    public static String deleteDocument(String index, String doc, String id) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);

        return "OK";
    }
    /**
     * 关闭客户端
     *
     * @throws IOException
     */
    public static void close() throws IOException {
        restClient.close();
    }
}

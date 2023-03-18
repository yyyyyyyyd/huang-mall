package com.huang.search.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.huang.common.es.SkuEsModel;
import com.huang.search.config.HuangElasticSearchConfig;
import com.huang.search.constant.EsConstant;
import com.huang.search.service.ProductSaveService;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 夏沫止水
 * @createTime: 2020-06-06 16:54
 **/

@Slf4j
@Service("productSaveService")
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    private RestHighLevelClient esRestClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {

//1.在es中建立索引，建立号映射关系（doc/json/product-mapping.json）

        //2. 在ES中保存这些数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            //构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsModel.getSkuId().toString());
            String jsonString = JSON.toJSONString(skuEsModel);
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }


        BulkResponse bulk = esRestClient.bulk(bulkRequest, HuangElasticSearchConfig.COMMON_OPTIONS);

        //TODO 如果批量错误
        boolean hasFailures = bulk.hasFailures();

        List<String> collect = Arrays.stream(bulk.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());

        log.info("商品上架完成：{}",collect);

        return hasFailures;
    }
}

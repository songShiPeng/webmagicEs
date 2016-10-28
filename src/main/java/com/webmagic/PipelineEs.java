package com.webmagic;

import com.es.EsBuilder;
import com.es.EsClinet;
import com.es.EsItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.regex.Matcher;

/**
 * Created by songshipeng on 2016/10/22.
 */
public class PipelineEs implements Pipeline{
    @Override
    public void process(ResultItems resultItems, Task task) {
        EsItem esItem = new EsItem();
        esItem.setTitle(resultItems.get("title").toString());
        esItem.setContent(resultItems.get("content").toString());
        ObjectMapper mapper = new ObjectMapper();
        try {
                byte[] jsonObj = mapper.writeValueAsBytes(esItem);
                EsClinet.setToEs(jsonObj, esItem.getTitle());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

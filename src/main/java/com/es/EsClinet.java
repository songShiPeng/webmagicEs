package com.es;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * Created by songshipeng on 2016/10/22.
 */
public class EsClinet {
    private static TransportClient client = EsBuilder.client;
    public static volatile int count = 0;

    public static void setToEs(byte[] jsonObj,String title){
        try {
            IndexResponse response = client.prepareIndex("hbnews", "content", title)
                    .setSource(jsonObj)
                    .execute()
                    .actionGet();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

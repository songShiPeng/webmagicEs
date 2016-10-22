package com.es;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by songshipeng on 2016/10/22.
 */

public class EsBuilder {
    public static TransportClient client;

    static{
        try {
            Settings settings = Settings.settingsBuilder()
                    .put("client.transport.sniff", false)
                    .put("cluster.name", "elasticsearch")
                    .build();
            client = TransportClient.builder().settings(settings).build();
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            System.out.println("失败");
            e.printStackTrace();
        }


    }
}

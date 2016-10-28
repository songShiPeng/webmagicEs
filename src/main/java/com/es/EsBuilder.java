package com.es;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songshipeng on 2016/10/22.
 */

public class EsBuilder {
    public static TransportClient client;
    public static Pattern pattern;

    static{
        try {
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", false)
                    .put("cluster.name", "elasticsearch")
                    .build();
            //client = TransportClient.settings(settings).build();
            client = new PreBuiltTransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            pattern = Pattern.compile("\\[(,|\\s)+]");
        } catch (UnknownHostException e) {
            System.out.println("失败");
            e.printStackTrace();
        }


    }
}

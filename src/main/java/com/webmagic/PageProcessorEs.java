package com.webmagic;

import com.es.EsBuilder;
import com.es.EsClinet;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by songshipeng on 2016/10/22.
 */
public class PageProcessorEs implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(500);

    @Override
    public void process(Page page) {
        //
        page.putField("content", page.getHtml().xpath("//div[@class='text']/p/text()").all());
        page.putField("title", page.getHtml().xpath("//h1[@class='title']/text()"));
        if (page.getResultItems().get("content") == null || page.getResultItems().get("content").toString().trim().equals("[]")) {
            page.setSkip(true);
        }else {
            EsClinet.count++;
        }
        if(EsClinet.count > 5){
            //EsBuilder.client.close();
            System.exit(0);
        }
        //添加url，暂时不防重
        page.addTargetRequests(page.getHtml().links().regex("http://\\S+.htm").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        new EsBuilder();
        Spider spider = Spider.create(new PageProcessorEs()).addUrl("http://www.hebnews.cn").thread(2);
        spider.addPipeline(new ConsolePipeline()).addPipeline(new PipelineEs()).run();
    }
}

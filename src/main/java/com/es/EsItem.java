package com.es;

import java.io.Serializable;

/**
 * Created by songshipeng on 2016/10/21.
 */
public class EsItem implements Serializable {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

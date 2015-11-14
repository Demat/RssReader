package com.android.md.rssreader.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

public class RssItem implements Serializable{
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField
    public String title;
    @DatabaseField
    public String description;
    @DatabaseField
    public String link;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm")
    public Date pubDate;

    public RssItem(){}

    public RssItem(String title, String description, String link, Date pubDate) {
        this.title = title;
        this.description = description;
        this.link= link;
        this.pubDate = pubDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}

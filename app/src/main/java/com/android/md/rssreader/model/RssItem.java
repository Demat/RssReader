package com.android.md.rssreader.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RssItem {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String subject;
    @DatabaseField
    String description;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm")
    Date pubDate;

    public RssItem(){}

    public RssItem(String subject, String description, Date pubDate) {
        this.subject = subject;
        this.description = description;
        this.pubDate = pubDate;
    }

    @Override
    public String toString(){
                    return "RssItem [id = "+id+", " +
                    "subject = "+subject+", " +
                    "description = "+description+", " +
                    "date = "+pubDate+"]\n";
    }
}

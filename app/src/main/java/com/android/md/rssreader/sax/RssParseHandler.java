package com.android.md.rssreader.sax;

import com.android.md.rssreader.model.RssItem;
import com.android.md.rssreader.model.XmlItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RssParseHandler extends DefaultHandler {

    boolean currentElement;
    String currentValue = "";
    RssItem item = null;
    private ArrayList<RssItem> rssItems = new ArrayList<RssItem>();

    DateFormat dateFormat = new SimpleDateFormat(XmlItem.xmlDateFormat);

    public ArrayList<RssItem> getItemsList() {
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        currentValue = "";
        if (localName.equals(XmlItem.item)) {
            currentElement = true;
            item = new RssItem();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentElement == true) {
            if (localName.equalsIgnoreCase(XmlItem.title)) {
                item.setTitle(currentValue.trim());
            } else if (localName.equalsIgnoreCase(XmlItem.description)) {
                item.setDescription(currentValue.trim());
            } else if (localName.equalsIgnoreCase(XmlItem.link)) {
                item.setLink(currentValue.trim());
            } else if (localName.equalsIgnoreCase(XmlItem.pubDate))
                try {
                    item.setPubDate(dateFormat.parse(currentValue.trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            else if (localName.equalsIgnoreCase(XmlItem.item)) {
                currentElement = false;
                rssItems.add(item);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement) {
            currentValue = currentValue + new String(ch, start, length);
        }
    }
}

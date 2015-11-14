package com.android.md.rssreader.sax;

import com.android.md.rssreader.model.RssItem;
import org.xml.sax.InputSource;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssReader {

    private String rssUrl;

    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    public InputSource urlEncodeing(String rssUrl) throws Exception{
        URL url = new URL(rssUrl);
        Reader reader = new InputStreamReader(url.openStream(),"iso-8859-2");

        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
        return is;
    }

    public List<RssItem> getItems() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        RssParseHandler handler = new RssParseHandler();
        saxParser.parse(urlEncodeing(rssUrl), handler);

        return handler.getItemsList();
    }
}

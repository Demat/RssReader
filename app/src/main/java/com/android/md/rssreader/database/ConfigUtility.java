package com.android.md.rssreader.database;

import com.android.md.rssreader.model.RssItem;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

public class ConfigUtility extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[]{RssItem.class};

    public static void main(String[] args) throws SQLException, IOException {
    writeConfigFile("ormlite_config.txt", classes);
    }
}

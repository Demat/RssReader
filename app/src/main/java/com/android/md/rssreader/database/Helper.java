package com.android.md.rssreader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.md.rssreader.R;
import com.android.md.rssreader.model.RssItem;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Helper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "rssItems.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<RssItem, Integer> rssItemDao = null;
    private RuntimeExceptionDao<RssItem, Integer> rssItemRunTimeDao = null;

    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, RssItem.class);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, RssItem.class, true);
            onCreate(database, connectionSource);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<RssItem, Integer> getRssItemDao() throws SQLException{
        if (rssItemDao == null){
            rssItemDao = getDao(RssItem.class);
        }
        return rssItemDao;
    }

    public RuntimeExceptionDao<RssItem, Integer> getRssItemRunTimeDao(){
        if (rssItemRunTimeDao == null){
            rssItemRunTimeDao = getRuntimeExceptionDao(RssItem.class);
        }
        return rssItemRunTimeDao;
    }
}


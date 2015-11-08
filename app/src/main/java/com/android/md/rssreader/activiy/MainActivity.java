package com.android.md.rssreader.activiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.md.rssreader.R;
import com.android.md.rssreader.database.Helper;
import com.android.md.rssreader.model.RssItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Helper dbHelper;

    private void addRssItem() throws ParseException {
        dbHelper = OpenHelperManager.getHelper(this, Helper.class);
        RuntimeExceptionDao<RssItem, Integer> rssItemDao = dbHelper.getRssItemRunTimeDao();

        //TEST START
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateS = "2014-10-07 19:36";
        Date date = dateFormat.parse(dateS);

        rssItemDao.create(new RssItem("Temat", "Treść", "http://wiadomosci.wp.pl/kat,1342,title,Kim-jest-Wladyslaw-Kosiniak-Kamysz-nowy-prezes-PSL,wid,17959153,wiadomosc.html",  date));

        List<RssItem> items = rssItemDao.queryForAll();

        Log.d("Demo", items.toString());
        // TEST END

        OpenHelperManager.releaseHelper();
        /*DELETE ALL
        List<RssItem> items = rssItemDao.queryForAll();
        rssItemDao.delete(items);
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            addRssItem();
        } catch(ParseException pex) {}
    }
}

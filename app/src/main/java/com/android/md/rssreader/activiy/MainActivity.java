package com.android.md.rssreader.activiy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.android.md.rssreader.ItemAdapter;
import com.android.md.rssreader.ListListener;
import com.android.md.rssreader.R;
import com.android.md.rssreader.database.Helper;
import com.android.md.rssreader.model.RssItem;
import com.android.md.rssreader.sax.RssReader;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton refresh;
    ListView listViewItems;

    Helper dbHelper;
    RuntimeExceptionDao<RssItem, Integer> rssItemDao;

    String url = "http://wiadomosci.wp.pl/ver,rss,rss.xml";
    RssReader rssReader;
    List<RssItem> rssItemList;

    AsyncTaskGetRssItems task = new AsyncTaskGetRssItems();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewItems = (ListView) findViewById(R.id.listView);
        refresh = (FloatingActionButton) findViewById(R.id.roundButton);

        dbHelper = OpenHelperManager.getHelper(this, Helper.class);
        rssItemDao = dbHelper.getRssItemRunTimeDao();

        if(isOnline()){
            clearList();
            task.execute(url);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshListButton();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OpenHelperManager.releaseHelper();
    }

    public void clearList() {
        List<RssItem> items = rssItemDao.queryForAll();
        rssItemDao.delete(items);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void insertItems(ArrayList<RssItem> rssItemList) {
        for (RssItem item : rssItemList) {
            rssItemDao.create(new RssItem(item.getTitle(), item.getDescription(),
                    item.getLink(), item.getPubDate()));
        }
    }

    public void populateListView() {
        ArrayList<RssItem> rssItems = (ArrayList) rssItemDao.queryForAll();
        final ItemAdapter rssList = new ItemAdapter(MainActivity.this, rssItems, dbHelper);
        listViewItems.setAdapter(rssList);
        listViewItems.setOnItemClickListener(new ListListener(rssItems, MainActivity.this));
    }

    private void refreshListButton() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    clearList();
                    AsyncTaskGetRssItems task = new AsyncTaskGetRssItems();
                    task.execute(url);
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.connectionToast),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class AsyncTaskGetRssItems extends AsyncTask<String, Void, List<RssItem>> {
        @Override
        protected List<RssItem> doInBackground(String... urls) {
            try {
                rssReader = new RssReader(urls[0]);
                rssItemList = rssReader.getItems();
                return rssItemList;

            } catch (Exception e) {
                Log.e("ITCRssReader", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {
            insertItems((ArrayList)result);
            populateListView();
        }
    }
}

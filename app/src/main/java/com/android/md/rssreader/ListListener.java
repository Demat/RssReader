package com.android.md.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import com.android.md.rssreader.activiy.InformationActivity;
import com.android.md.rssreader.model.RssItem;
import java.util.List;

public class ListListener implements AdapterView.OnItemClickListener{

    List<RssItem> rssItems;
    Activity activity;

    public ListListener(List<RssItem> rssItems, Activity activity) {
        this.rssItems = rssItems;
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          RssItem ri = rssItems.get(position);
          //InformationActivity ia = new InformationActivity(ri);
          Intent i = new Intent(activity, InformationActivity.class);
          i.putExtra("rssItem", ri);
          activity.startActivity(i);
    }
}

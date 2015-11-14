package com.android.md.rssreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.android.md.rssreader.database.Helper;
import com.android.md.rssreader.model.RssItem;

public class ItemAdapter extends ArrayAdapter<RssItem> {
    Helper dbHelper;

    public ItemAdapter(Context context, ArrayList<RssItem> rssItems, Helper dbHelper) {
        super(context, 0, rssItems);
        this.dbHelper = dbHelper;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RssItem rssItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                TextView tvTitle = (TextView) convertView.findViewById(R.id.itemRssTitle);
                TextView tvPubDate = (TextView) convertView.findViewById(R.id.itemRssPubDate);

        tvTitle.setText(rssItem.getTitle());
        tvPubDate.setText(dateFormat.format(rssItem.getPubDate()));

        return convertView;
    }
}
package com.android.md.rssreader.activiy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android.md.rssreader.R;
import com.android.md.rssreader.model.RssItem;

public class InformationActivity extends AppCompatActivity {

    Intent i = getIntent();

    TextView title, description, link;

    RssItem rssItem;

    public InformationActivity() {
    }

    public InformationActivity(RssItem rssItem) {
        this.rssItem = rssItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        i = getIntent();
        rssItem = (RssItem)i.getSerializableExtra("rssItem");

        title = (TextView) findViewById(R.id.titleInfo);
        description = (TextView) findViewById(R.id.descriptionInfo);
        link = (TextView) findViewById(R.id.linkInfo);

        title.setText(rssItem.getTitle());
        description.setText(rssItem.getDescription());
        link.setText(rssItem.getLink());
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(rssItem.getLink()));
                startActivity(i);
            }
        });
    }
}


package com.example.presspollresults;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ImageTextListViewActivity extends Activity implements OnItemClickListener {

    private String LOG_TAG = "ImageTextListViewActivity";

    public static final String[] titles = new String[]{"Strawberry", "Banana", "Orange", "Mixed"};

    public static final String[] descriptions = new String[]{
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant", "Citrus Fruit",
            "Mixed Fruits"};

    ListView listView;
    List<RowItem> rowItems;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        Intent in = getIntent();
        ArrayList<String> electoratesNew = in.getStringArrayListExtra("electorates");
        ArrayList<String> voteCountsNew = in.getStringArrayListExtra("voteCounts");

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < electoratesNew.size(); i++) {
            RowItem item = new RowItem(electoratesNew.get(i), voteCountsNew.get(i));
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}

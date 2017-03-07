package com.streamsurfer.surfers.streamsurfer;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Results extends AppCompatActivity {

    private Entries entries = Entries.getInstance();
    public static final String SELECTED = "selected";
    private Map<String, Entry> entryMap = entries.getEntries();
    private List<Entry> results;
    private SetAdapter setAdapter = new SetAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String search = getIntent().getStringExtra(MainActivity.RESULTS);
        results = getResults(search);

        final ListView resultList = (ListView) findViewById(R.id.result_list);
        final EditText input = (EditText) findViewById(R.id.results_input);
        input.setHint(search);
        Button searchButton = (Button) findViewById(R.id.results_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newSearch = input.getText().toString().toLowerCase();
                results = getResults(newSearch);
                setAdapter.customAdapterSet(resultList, results, Results.this);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        setAdapter.customAdapterSet(resultList, results, this);
    }

    private List<Entry> getResults(String search) {
        List<Entry> results = new ArrayList<>();
        Set<String> entryKeys = entryMap.keySet();
        for (String s : entryKeys) {
            if (s.contains(search)) {
                results.add(entryMap.get(s));
            }
        }
        return results;
    }

    public void clickHandlerLeft(View v) {
        clickHandler(v, R.id.title_left);
    }

    public void clickHandlerRight(View v) {
        clickHandler(v, R.id.title_right);
    }

    private void clickHandler(View v, int id) {
        LinearLayout row = (LinearLayout) v.getParent();
        TextView title = (TextView) row.findViewById(id);
        String selected = title.getText().toString().toLowerCase();
        Intent detail = new Intent(Results.this, Details.class);
        detail.putExtra(SELECTED, selected);
        startActivity(detail);
    }
}

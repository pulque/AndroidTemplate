package com.lizheblogs.android.template.module.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lizheblogs.android.template.R;
import com.lizheblogs.android.template.module.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class MainActivity extends BaseActivity {

    private MainContract.Presenter presenter;
    private TextView title;
    private TextView description;
    private ListView list;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        list = (ListView) findViewById(R.id.list);
        mListAdapter = new ListAdapter(new ArrayList<String>());
        list.setAdapter(mListAdapter);

        setPresenter(new MainPresenter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void notifyList(List<String> images) {
        mListAdapter.replaceData(images);
    }

    @Override
    public void hideTitle() {
        this.title.setVisibility(View.GONE);
    }

    @Override
    public void showTitle(String title) {
        this.title.setText(title);
        this.title.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDescription() {
        this.description.setVisibility(View.GONE);
    }

    @Override
    public void showDescription(String description) {
        this.description.setText(description);
        this.description.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }
}

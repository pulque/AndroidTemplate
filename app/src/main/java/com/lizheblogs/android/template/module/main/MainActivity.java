/*
 * Copyright 2016 Li Zhe <pulqueli@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    private MainListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        list = (ListView) findViewById(R.id.list);
        mListAdapter = new MainListAdapter(new ArrayList<String>());
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

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}

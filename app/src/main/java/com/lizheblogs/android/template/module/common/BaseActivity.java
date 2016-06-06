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

package com.lizheblogs.android.template.module.common;

import android.app.Activity;
import android.widget.Toast;

import com.lizheblogs.android.template.module.main.MainContract;

import java.util.List;

/**
 * Base Activity
 * Created by Norman.Li on 6/6/2016.
 */
public class BaseActivity extends Activity implements MainContract.View {

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showToast(int msgRes) {
        Toast.makeText(this, msgRes, Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyList(List<String> images) {

    }

    @Override
    public void hideTitle() {

    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void hideDescription() {

    }

    @Override
    public void showDescription(String description) {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }
}

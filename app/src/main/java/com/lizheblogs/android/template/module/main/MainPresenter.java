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

import android.support.annotation.NonNull;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Presenter: control data
 * Created by Norman.Li on 6/2/2016.
 */
public class MainPresenter implements MainContract.Presenter {

    private final MainActivity mActivity;

    public MainPresenter(@NonNull MainActivity mActivity) {
        this.mActivity = checkNotNull(mActivity);
        this.mActivity.setPresenter(this);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void start() {

    }
}

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


import com.lizheblogs.android.template.module.common.BasePresenter;
import com.lizheblogs.android.template.module.common.BaseView;

import java.util.List;

/**
 * Contract: a interface of main activity
 * Created by Norman.Li on 6/2/2016.
 */
public interface MainContract {

    interface View extends BaseView<Presenter> {

        void notifyList(List<String> images);

        void hideTitle();

        void showTitle(String title);

        void hideDescription();

        void showDescription(String description);

    }

    interface Presenter extends BasePresenter {

        void updateData();

        void complete();

        void clearData();

    }
}

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

package com.lizheblogs.android.template.data.common;

/**
 * Main entry point for accessing data.
 * Created by Norman.Li on 6/13/2016.
 */
public interface DataSource {

    interface LoadCallback {

        void onTasksLoaded(String entity);

        void onDataNotAvailable();
    }

    void getEntity(LoadCallback callback);

    void saveEntity(String entity);

    void clearEntity();

    void refreshTasks();

    void deleteAllEntity();

    void deleteEntity(String EntityId);
}

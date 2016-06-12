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

package com.lizheblogs.android.template.util;

import android.content.Context;

import com.lizheblogs.android.template.VersionControl;
import com.squareup.picasso.Picasso;

/**
 * PicassoUtils
 * Created by Norman.Li on 6/3/2016.
 */
public class PicassoUtils {

    private volatile static Picasso singleton;

    public static Picasso with(Context context) {
        if (singleton == null) {
            synchronized (Picasso.class) {
                if (singleton == null) {
                    singleton = new Picasso.Builder(context).build();
                    singleton.setLoggingEnabled(VersionControl.IS_SHOW_LOG);
                }
            }
        }
        return singleton;
    }

    public static void clearCache() {
        if (singleton != null)
            singleton.shutdown();
        singleton = null;
    }
}

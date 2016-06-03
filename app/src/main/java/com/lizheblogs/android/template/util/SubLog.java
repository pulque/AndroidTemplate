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

import android.text.TextUtils;
import android.util.Log;

import com.lizheblogs.android.template.VersionControl;

/**
 * SubLog
 * Created by Norman.Li on 6/3/2016.
 */
public class SubLog {

    private static final String TAG_LOG = "SubLog";
    private static final String THROWABLE = "Throwable";
    private static final String LINE = "_";

    private static final int maxLength = 4000;

    private static final int TAG_LOG_V = 1;
    private static final int TAG_LOG_I = 2;
    private static final int TAG_LOG_D = 3;
    private static final int TAG_LOG_W = 4;
    private static final int TAG_LOG_E = 5;

    /**
     * v log
     *
     * @param msg message
     */
    public static void v(String msg) {
        v(null, msg);
    }

    public static void v(String tag, String msg) {
        longShow(TAG_LOG_V, tag, msg);
    }

    /**
     * i log
     *
     * @param msg message
     */
    public static void i(String msg) {
        i(null, msg);
    }

    public static void i(String tag, String msg) {
        longShow(TAG_LOG_I, tag, msg);
    }

    /**
     * d log
     *
     * @param msg message
     */
    public static void d(String msg) {
        d(null, msg);
    }

    public static void d(String tag, String msg) {
        longShow(TAG_LOG_D, tag, msg);
    }

    /**
     * w log
     *
     * @param msg message
     */
    public static void w(String msg) {
        w(null, msg);
    }

    public static void w(String tag, String msg) {
        longShow(TAG_LOG_W, tag, msg);
    }

    /**
     * e log
     *
     * @param msg message
     */
    public static void e(String msg) {
        e(null, msg);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        longShow(TAG_LOG_E, tag, msg, tr);
    }

    /**
     * long log show
     *
     * @param logTag log level tag
     * @param tag    log tag
     * @param msg    message
     */
    private static void longShow(int logTag, String tag, String msg) {
        longShow(logTag, tag, msg, null);
    }

    private static void longShow(int logTag, String tag, String msg, Throwable tr) {
        if (VersionControl.IS_SHOW_LOG) {
            if (TextUtils.isEmpty(msg)) {
                return;
            }
            msg = msg.trim();
            int index = 0;
            String sub;
            int length = msg.length();
            while (index < length) {
                if (length <= index + maxLength) {
                    sub = msg.substring(index);
                } else {
                    sub = msg.substring(index, index + maxLength);
                }
                index += maxLength;
                showLog(logTag, tag, sub.trim());
            }
            if (tr != null) {
                Log.e(TAG_LOG + LINE + tag, THROWABLE, tr);
            }
        }
    }

    private static void showLog(int logTag, String tag, String msg) {
        switch (logTag) {
            case TAG_LOG_V:
                Log.v(TAG_LOG + LINE + tag, msg);
                break;
            case TAG_LOG_I:
                Log.i(TAG_LOG + LINE + tag, msg);
                break;
            case TAG_LOG_D:
                Log.d(TAG_LOG + LINE + tag, msg);
                break;
            case TAG_LOG_W:
                Log.w(TAG_LOG + LINE + tag, msg);
                break;
            case TAG_LOG_E:
                Log.e(TAG_LOG + LINE + tag, msg);
                break;
        }
    }

}
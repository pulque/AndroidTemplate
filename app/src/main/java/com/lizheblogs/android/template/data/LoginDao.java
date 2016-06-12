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

package com.lizheblogs.android.template.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.lizheblogs.android.template.util.DesUtils;

public final class LoginDao {
    private static final String PREFERENCES_NAME = "com_lizheblogs_android";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    /**
     * @param context  Context
     * @param username String
     * @param password String
     */
    public static void save(Context context, String username, String password) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putString(USERNAME, DesUtils.Encryption(username));
        editor.putString(PASSWORD, DesUtils.Encryption(password));
        editor.apply();
    }

    public static void save(Context context, String password) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putString(PASSWORD, DesUtils.Encryption(password));
        editor.apply();
    }

    /**
     * @param context Context
     */
    public static void clear(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.remove(USERNAME);
        editor.remove(PASSWORD);
        editor.apply();
    }

    /**
     * @param context Context
     * @return String
     */
    public static String readUsername(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        String name = pref.getString(USERNAME, "");
        if (TextUtils.isEmpty(name))
            return "";
        else
            return DesUtils.Decryption(name);
    }

    /**
     * @param context Context
     * @return String
     */
    public static String readPassword(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        String pwd = pref.getString(PASSWORD, "");
        if (TextUtils.isEmpty(pwd))
            return "";
        else
            return DesUtils.Decryption(pwd);
    }
}
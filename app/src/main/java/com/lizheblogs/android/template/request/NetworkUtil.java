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

package com.lizheblogs.android.template.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class NetworkUtil {

    private NetworkUtil() {
    }

    /**
     * Judge whether the device is network online
     *
     * @return true:OnLine,false：OffLine
     */
    public static boolean isOnline(Context context) {
        if (context != null) {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } else {
            return false;
        }
    }

    /**
     * Judge whether the device is network online
     *
     * @return true:OnLine,false：OffLine
     */
    public static boolean is3GOnline(Context context) {
        if (context != null) {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected() && networkInfo
                    .getType() == ConnectivityManager.TYPE_MOBILE);
        } else {
            return false;
        }
    }

    public static String URLEncode(String content) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String ImageEncode(String filePath) {
        Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
        if (selectedImage == null)
            return "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String strBase64 = Base64.encodeToString(byteArray, 0);
        if (selectedImage != null && !selectedImage.isRecycled()) {
            selectedImage.recycle();
        }
        return strBase64;
    }
}

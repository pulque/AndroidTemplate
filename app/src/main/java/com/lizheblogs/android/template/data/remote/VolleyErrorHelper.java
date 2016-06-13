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

package com.lizheblogs.android.template.data.remote;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.lizheblogs.android.template.R;

import org.json.JSONObject;

/**
 * VolleyErrorHelper
 * Created by Norman.Li on 6/3/2016.
 */
public class VolleyErrorHelper {

    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error
     * @param context
     * @return
     */
    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.generic_server_down);
        } else if (isServerProblem(error)) {
            return handleServerError(error, context);
        } else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.no_server);
        }
        return context.getResources().getString(R.string.generic_error_not_know);
    }

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    /**
     * Determines whether the error is related to server
     *
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock message or to
     * show a message retrieved from the server.
     *
     * @param err
     * @param context
     * @return
     */
    private static String handleServerError(Object err, Context context) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        if (response != null) {
            try {
                // server might return error like this { "error": "Some error occured" }
                // Use "Gson" to parse the result
                JSONObject result = new JSONObject(new String(response.data));
                if (result != null && !result.isNull("error")) {
                    return result.optString("error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (response.statusCode) {
                case 404:
                    return context.getResources().getString(R.string.internet_404);
                case 401:
                    return context.getResources().getString(R.string.internet_401);
                case 500:
                    return context.getResources().getString(R.string.internet_500);
                case 503:
                    return context.getResources().getString(R.string.internet_503);
                default:
                    // invalid request
                    return TextUtils.isEmpty(error.getMessage()) ? context.getResources().getString(R.string.generic_error_not_know) : error.getMessage();
            }
        }
        return context.getResources().getString(R.string.generic_error_not_know);
    }
}

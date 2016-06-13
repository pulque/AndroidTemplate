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
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lizheblogs.android.template.R;
import com.lizheblogs.android.template.module.common.Constants;
import com.lizheblogs.android.template.module.common.SubApplication;
import com.lizheblogs.android.template.data.remote.common.AbstractReq;
import com.lizheblogs.android.template.data.remote.common.HttpRsp;
import com.lizheblogs.android.template.util.GsonUtil;
import com.lizheblogs.android.template.util.SubLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * https://developer.android.com/training/volley/index.html
 * Base Request Class
 * Created by Norman.Li on 6/2/2016.
 */
public class RequestApi {
    private static final String TAG = "Request";
    private static final int socketTimeout = 60000;

    public static void RequestJson(final Context context, int method, AbstractReq msg, final RequestInterface.CallBack mCallBack) {
        checkNotNull(context);
        final HttpRsp httpRes = new HttpRsp();
        if (!NetworkUtil.isOnline(context)) {
            httpRes.setIsException(true);
            httpRes.setStatus(Constants.NO_NETWORK_498);
            httpRes.setMessage(context.getString(R.string.no_internet));
            if (mCallBack != null)
                mCallBack.onFailure(httpRes);
            return;
        }

        String url = msg.getUri();
        String msgContent = GsonUtil.objectToJson(msg);
        JSONObject mJsonObject = null;
        if (Request.Method.GET == method) {
            SubLog.i(TAG, "Norman-Content: Request.Method.GET-" + msgContent);
            url = url + "?json=" + NetworkUtil.URLEncode(msgContent);
        } else if (Request.Method.POST == method) {
            SubLog.i(TAG, "Norman-Content: Request.Method.POST-" + msgContent);
            if (!TextUtils.isEmpty(msgContent)) {
                try {
                    mJsonObject = new JSONObject(msgContent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        // pass second argument as "null" for GET requests
        JsonObjectRequest req = new JsonObjectRequest(method, url, mJsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        SubLog.i(TAG, "Norman-Response: " + response.toString());
                        if (mCallBack != null)
                            mCallBack.onSuccess(response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpRes.setMessage(VolleyErrorHelper.getMessage(error, context));
                if (error.networkResponse != null)
                    httpRes.setStatus(error.networkResponse.statusCode);
                httpRes.setIsException(true);
                SubLog.e("Norman-Response-Error: ", httpRes.getStatus() + "-" + httpRes.getMessage());
                if (mCallBack != null)
                    mCallBack.onFailure(httpRes);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };


        DefaultRetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        req.setRetryPolicy(policy);
        SubLog.i(TAG, "Norman-Request: " + url);
        // add the request object to the queue to be executed
        SubApplication.getInstance().addToRequestQueue(req);
    }

    public static void cancelAll() {
        SubLog.i(TAG, "Norman-Request: cancelAll");
        SubApplication.getInstance().cancelAllPendingRequests();
    }

}

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

import com.android.volley.Request;
import com.lizheblogs.android.template.module.common.Constants;
import com.lizheblogs.android.template.request.common.BaseReq;

/**
 * RequestHelper
 * Created by Norman.Li on 6/6/2016.
 */
public class RequestHelper {

    private static RequestHelper mRequestHelper;

    public RequestHelper() {

    }

    //Norman: Incorrect lazy initialization of static field
    public static synchronized RequestHelper getInstance() {
        if (mRequestHelper == null)
            mRequestHelper = new RequestHelper();
        return mRequestHelper;
    }

    public void cancelAll() {
        RequestApi.cancelAll();
    }

    public void UserList(Context mContext, RequestInterface.CallBack mCallBack) {
        BaseReq mReq = new BaseReq();
        mReq.setUri(Constants.SERVER_URL + "user_list.json");
        RequestApi.RequestJson(mContext, Request.Method.GET, mReq, mCallBack);
    }
}

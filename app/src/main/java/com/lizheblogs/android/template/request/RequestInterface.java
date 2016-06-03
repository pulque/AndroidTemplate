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


import com.lizheblogs.android.template.request.common.HttpRsp;

/**
 * Created by Norman.Li on 6/3/2016.
 * CallBack
 */
public interface RequestInterface {

    interface CallBack {

        void onSuccess(String mEntity);

        void onFailure(HttpRsp httpRsp);

    }

    interface Success {

        void onSuccess(String mEntity);

    }

    interface Failure {

        void onFailure(HttpRsp httpRsp);

    }

}
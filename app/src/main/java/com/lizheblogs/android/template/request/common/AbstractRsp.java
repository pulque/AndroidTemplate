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

package com.lizheblogs.android.template.request.common;

import android.content.Context;

import com.lizheblogs.android.template.R;

import java.io.Serializable;
import java.util.Calendar;

public class AbstractRsp implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpRsp summary = new HttpRsp();

    public AbstractRsp() {
    }

    public AbstractRsp(int status, String message, String date) {
        summary = new HttpRsp(status, message, date);
    }

    public boolean isError() {
        if (summary != null) {
            return summary.getStatus() != 200;
        }
        return true;
    }

    public int getStatus() {
        if (summary != null) {
            return summary.getStatus();
        }
        return 0;
    }

    public String getMessage(Context mContext) {
        if (summary != null) {
            return summary.getMessage();
        }
        return mContext.getString(R.string.unknown_error);
    }

    public String getDate() {
        if (summary != null) {
            return summary.getDate();
        }
        return Calendar.getInstance().toString();
    }

    public HttpRsp getSummary() {
        return summary;
    }

    public void setSummary(HttpRsp summary) {
        this.summary = summary;
    }


}

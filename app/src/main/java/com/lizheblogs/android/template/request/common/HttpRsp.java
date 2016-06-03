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

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class HttpRsp implements Serializable {
    /**
     * status code
     */
    private int status;

    /**
     * return content
     */
    @Expose
    private String message;
    /**
     * return date
     */
    private String date;

    /**
     * is exception
     */
    private boolean isException = false;

    public HttpRsp() {

    }

    public HttpRsp(int status, String message, String date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isException() {
        return isException;
    }

    public void setIsException(boolean isException) {
        this.isException = isException;
    }
}

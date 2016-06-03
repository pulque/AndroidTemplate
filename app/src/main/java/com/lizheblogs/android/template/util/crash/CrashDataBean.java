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

package com.lizheblogs.android.template.util.crash;

import java.io.Serializable;

/**
 * ErrorInfo
 *
 * @author Norman.Li
 * @version 2016.6.3
 */
public class CrashDataBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String file_name;
    private String path;
    private String message;

    public CrashDataBean(String file_name, String path, String message) {
        super();
        this.file_name = file_name;
        this.path = path;
        this.message = message;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

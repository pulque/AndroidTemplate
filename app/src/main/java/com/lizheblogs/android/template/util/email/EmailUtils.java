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

package com.lizheblogs.android.template.util.email;

import com.lizheblogs.android.template.VersionControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {

    private static final String EMAIL_ADDRESS = "crash@lizheblogs.com";
    private static final String PWD = "1qaz2WSX3edc";
    private static final String RECEIVE_CRASH = "receivecrash@lizheblogs.com";

    public static void sendEmail(String path, String title, String content) {
        if (VersionControl.IS_EMAIL_CRASH) {
            BackgroundMail bm = new BackgroundMail();
            bm.setEmailUserName(EMAIL_ADDRESS);
            bm.setEmailPassword(PWD);
            bm.setMailTo(RECEIVE_CRASH);
            bm.setFormSubject(title);
            bm.setFormBody(content);
            bm.setAttachment(path);
            bm.send();
        }
    }

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}

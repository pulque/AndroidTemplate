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

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * @author badbaixiao@gmail.com
 */
public class BackgroundMail {
    private String username, password, mailTo, subject, body;
    private ArrayList<String> attachments;

    public BackgroundMail() {
        attachments = new ArrayList<String>();
    }

    public void setEmailUserName(String string) {
        this.username = string;
    }

    public void setEmailPassword(String string) {
        this.password = string;
    }

    public void setMailTo(String string) {
        this.mailTo = string;
    }

    public void setFormSubject(String string) {
        this.subject = string;
    }

    public void setFormBody(String string) {
        this.body = string;
    }

    public void setAttachment(String attachments) {
        this.attachments.add(attachments);

    }

    public void send() {
        new startSendingEmail().execute();
    }

    public class startSendingEmail extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                EmailSender sender = new EmailSender(username, password);
                if (!attachments.isEmpty()) {
                    for (int i = 0; i < attachments.size(); i++) {
                        if (!attachments.get(i).isEmpty()) {
                            sender.addAttachment(attachments.get(i));
                        }
                    }
                }
                sender.sendMail(subject, body, username, mailTo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}

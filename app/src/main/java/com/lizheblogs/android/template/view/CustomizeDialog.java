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

package com.lizheblogs.android.template.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

/**
 * Customize Dialog
 * Created by Norman.Li on 6/13/2016.
 */
public class CustomizeDialog {

    private AlertDialog dialog;

    public CustomizeDialog(View mView) {
        checkNull(mView.getContext());
        dialog.setView(mView);
        dialog.show();
    }

    private void checkNull(Context mContext) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new AlertDialog.Builder(mContext).create();
    }

    public AlertDialog getDialog() {
        return dialog;
    }
}

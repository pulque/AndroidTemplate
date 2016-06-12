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

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.lizheblogs.android.template.VersionControl;
import com.lizheblogs.android.template.module.common.Constants;
import com.lizheblogs.android.template.util.SubLog;
import com.lizheblogs.android.template.util.email.EmailUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * UncaughtException, save exception file.
 *
 * @author Norman.Li
 */
public class AppCrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";
    //CrashHandler
    private static AppCrashHandler INSTANCE = new AppCrashHandler();
    //Handle UncaughtException class
    private UncaughtExceptionHandler mDefaultHandler;
    //Context
    private Context mContext;
    //store info
    private Map<String, String> InformationMap = new HashMap<>();

    //Format Date
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private AppCrashHandler() {
    }

    /**
     * CrashHandler Instance
     */
    public static AppCrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Initialization
     *
     * @param context Context
     */
    public void init(Context context) {
        mContext = context;
        //get UncaughtException
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //set CrashHandler
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * @param thread Thread
     * @param ex     Throwable
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //if we not catch Exception
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    /**
     * handleException
     *
     * @param ex
     * @return true:Abnormality information has been processed.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //Show Toast
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "Sorry,App Crash, save file to /sdcard/crash/. Please send to developer.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //collect device info
        collectDeviceInfo(mContext);
        //save info file
        CrashDataBean mBean = saveCrashInfo2File(ex);
        if (mBean != null) {
            SubLog.e("AppCrashHandler", mBean.getMessage());
            if (VersionControl.IS_DEBUG)
                EmailUtils.sendEmail(mBean.getPath() + mBean.getFile_name(), mBean.getFile_name(), mBean.getMessage());
        }
        return true;
    }

    /**
     * collect device info
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            InformationMap.put("build_version", VersionControl.version);
            InformationMap.put("build_type_isDebug", String.valueOf(VersionControl.IS_DEBUG));
            InformationMap.put("build_type_isSendCrashEmail", String.valueOf(VersionControl.IS_EMAIL_CRASH));
            InformationMap.put("Android_version", Build.VERSION.SDK_INT + "");
            InformationMap.put("Android_system", "Product Model: " + Build.MODEL + ","
                    + Build.VERSION.SDK + ","
                    + Build.VERSION.RELEASE);

            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                InformationMap.put("versionName", versionName);
                InformationMap.put("versionCode", versionCode);
            }

        } catch (NameNotFoundException e) {
            SubLog.e(TAG, "an error occurred when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                InformationMap.put(field.getName(), field.get(null).toString());
                SubLog.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                SubLog.e(TAG, "an error occurred when collect crash info", e);
            }
        }
    }

    /**
     * Save Crash Info To File
     *
     * @param ex
     * @return file bean
     */
    private CrashDataBean saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : InformationMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".txt";
            String path = Constants.CRASH_SAVE_PATH;
            if (Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState())) {
                path = Environment.getExternalStorageDirectory() + Constants.CRASH_SAVE_PATH;
                File dir = new File(path);
                if (!dir.exists()) {
                    if (!dir.mkdirs()) {
                        SubLog.e(TAG, "an error occurred while writing file...");
                        return null;
                    }
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            CrashDataBean mDataBean = new CrashDataBean(fileName, path, sb.toString());
            return mDataBean;
        } catch (Exception e) {
            SubLog.e(TAG, "an error occurred while writing file...", e);
        }
        return null;
    }
}

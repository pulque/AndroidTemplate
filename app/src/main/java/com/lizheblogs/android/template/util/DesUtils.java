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

package com.lizheblogs.android.template.util;

import android.text.TextUtils;

import java.security.Key;

import javax.crypto.Cipher;

/**
 * security
 * http://www.cnblogs.com/vwpolo/archive/2012/07/18/2597232.html
 * Created by Norman.Li on 3/30/2016.
 */
public class DesUtils {
    private static DesUtils mDesUtils;
    private static String strDefaultKey = "com.lizheblogs.android";

    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    public static String Encryption(String data) {
        DesUtils mDesUtils = DesUtils.getInstance();
        if (mDesUtils != null) {
            try {
                String enData = mDesUtils.encrypt(data);
                if (!TextUtils.isEmpty(enData)) {
                    return enData;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static String Decryption(String data) {
        DesUtils mDesUtils = DesUtils.getInstance();
        if (mDesUtils != null) {
            try {
                String deData = mDesUtils.decrypt(data);
                if (!TextUtils.isEmpty(deData)) {
                    return deData;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static synchronized DesUtils getInstance() {
        if (mDesUtils == null) {
            try {
                mDesUtils = new DesUtils();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mDesUtils;
    }

    public DesUtils() throws Exception {
        this(strDefaultKey);
    }

    public DesUtils(String strKey) throws Exception {
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(strKey.getBytes());

        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    public String byteArr2HexStr(byte[] arrB){
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    private Key getKey(byte[] arrBTmp){
        byte[] arrB = new byte[8];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }

}

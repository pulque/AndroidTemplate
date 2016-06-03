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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonUtil {

    /**
     * create once, reuse
     */
    private static Gson gson;

    private static void initGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .enableComplexMapKeySerialization()
                    .serializeNulls()
                    .setPrettyPrinting()
                    .setVersion(1.0)
                    .create();
        }
    }

    /**
     * Convert Object to Json
     *
     * @param object class
     * @return json
     */
    public static String objectToJson(Object object) {
        initGson();
        return gson.toJson(object);
    }

    /**
     * Convert json to object
     *
     * @param jsonString json
     * @param clazz      class
     * @return class
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        initGson();
        return gson.fromJson(jsonString, clazz);
    }

}

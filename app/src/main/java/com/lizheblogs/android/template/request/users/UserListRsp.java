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

package com.lizheblogs.android.template.request.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lizheblogs.android.template.request.common.AbstractRsp;

import java.util.List;

/**
 * UserList
 * Created by Norman.Li on 6/6/2016.
 */
public class UserListRsp extends AbstractRsp {

    /**
     * user_id : 1
     * profile_picture : http://lizheblogs.com/demo/AndroidTemplate/photo-1452019761881-737a0073d2f7.jpg
     * name : a
     */

    @Expose
    @SerializedName("users")
    private List<UsersEntity> users;

    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }

    public static class UsersEntity {
        @Expose
        @SerializedName("user_id")
        private int userId;
        @Expose
        @SerializedName("profile_picture")
        private String profilePicture;
        @Expose
        @SerializedName("name")
        private String name;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

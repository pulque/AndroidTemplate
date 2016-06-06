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

package com.lizheblogs.android.template.module.main;

import android.support.annotation.NonNull;

import com.lizheblogs.android.template.R;
import com.lizheblogs.android.template.request.RequestHelper;
import com.lizheblogs.android.template.request.RequestInterface;
import com.lizheblogs.android.template.request.common.HttpRsp;
import com.lizheblogs.android.template.request.users.UserListRsp;
import com.lizheblogs.android.template.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Presenter: control data
 * Created by Norman.Li on 6/2/2016.
 */
public class MainPresenter implements MainContract.Presenter {

    private final MainActivity mActivity;

    public MainPresenter(@NonNull MainActivity mActivity) {
        this.mActivity = checkNotNull(mActivity);
        this.mActivity.setPresenter(this);
    }

    @Override
    public void updateData() {
        initData();
    }

    @Override
    public void complete() {

    }

    @Override
    public void start() {
        initData();
    }

    private void initData() {
        mActivity.showTitle("Test");
        mActivity.showDescription("Test Description");
        RequestHelper.getInstance().UserList(mActivity, new RequestInterface.CallBack() {
            @Override
            public void onSuccess(String mEntity) {
                if (mActivity.isFinishing()) {
                    return;
                }
                UserListRsp rsp = GsonUtil.jsonToObject(mEntity, UserListRsp.class);
                if (rsp == null) {
                    mActivity.showToast(R.string.generic_error);
                } else if (rsp.isError()) {
                    mActivity.showToast(rsp.getMessage(mActivity));
                } else {
                    List<String> mStrings = new ArrayList<String>();
                    List<UserListRsp.UsersEntity> mUsersEntities = rsp.getUsers();
                    for (UserListRsp.UsersEntity mUsersEntity : mUsersEntities) {
                        mStrings.add(mUsersEntity.getProfilePicture());
                    }
                    mActivity.notifyList(mStrings);
                }
            }

            @Override
            public void onFailure(HttpRsp httpRsp) {
                mActivity.showToast(httpRsp.getMessage());
            }
        });
    }
}

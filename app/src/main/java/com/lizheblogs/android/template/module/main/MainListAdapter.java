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

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lizheblogs.android.template.R;
import com.lizheblogs.android.template.module.common.Constants;
import com.lizheblogs.android.template.util.PicassoUtils;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * ListAdapter
 * Created by Norman.Li on 6/2/2016.
 */
public class MainListAdapter extends BaseAdapter {

    private List<String> images;

    public MainListAdapter(List<String> images) {

        setList(images);
    }

    public void replaceData(List<String> images) {
        setList(images);
        notifyDataSetChanged();
    }

    private void setList(List<String> images) {
        this.images = checkNotNull(images);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.activity_main_list_item, parent, false);
            holder = new Holder();
            holder.imageIV = (ImageView) convertView.findViewById(R.id.imageIV);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        PicassoUtils.with(parent.getContext())
                .load(images.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(Constants.SCREEN_WIDTH, 0)
                .config(Bitmap.Config.RGB_565)
                .into(holder.imageIV);
        return convertView;
    }

    class Holder {
        ImageView imageIV;
    }
}

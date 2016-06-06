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

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lizheblogs.android.template.R;
import com.lizheblogs.android.template.util.PicassoUtils;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * ListAdapter
 * Created by Norman.Li on 6/2/2016.
 */
public class ListAdapter extends BaseAdapter {

    private List<String> images;

    public ListAdapter(List<String> images) {
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
            holder = new Holder();
            holder.imageIV = new ImageView(parent.getContext());
            convertView = holder.imageIV;
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        PicassoUtils.with(parent.getContext())
                .load(images.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageIV);
        return convertView;
    }

    class Holder {
        ImageView imageIV;
    }
}

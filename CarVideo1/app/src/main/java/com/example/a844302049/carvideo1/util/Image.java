package com.example.a844302049.carvideo1.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 844302049 on 2018/7/4.
 */

public class Image {
        /**
         * 网络加载图片
         *
         * @param mContext
         * @param url
         * @param view
         */
        public static void LoaderNet(Context mContext, String url, ImageView view) {
            Glide.with(mContext).load(url).into(view);
        }
}

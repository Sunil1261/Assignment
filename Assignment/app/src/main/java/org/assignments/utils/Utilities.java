package org.assignments.utils;

import android.content.Context;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.assignments.R;


/**
 * Created by sunil on 23/12/17.
 */
public class Utilities {

    public static void loadImage(String url, NetworkImageView imgCategory, Context context) {

        if (url != null) {
            ImageLoader imageLoader = CustomVolleyRequestQueue.getInstance(context)
                    .getImageLoader();
            imageLoader.get(url, ImageLoader.getImageListener(imgCategory,
                    R.drawable.loading, R.drawable.placeholder));
            imgCategory.setImageUrl(url, imageLoader);
        }
    }
}
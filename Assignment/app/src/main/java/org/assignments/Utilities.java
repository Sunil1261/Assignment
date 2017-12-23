package org.assignments;

import android.content.Context;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


/**
 * Created by mahiti on 31/8/16.
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
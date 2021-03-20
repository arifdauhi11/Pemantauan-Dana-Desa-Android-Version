package com.arifdauhi.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;

import com.arifdauhi.pdd.R;

public class Tools {
        public static void setSystemBarColor(Activity activity) {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = activity.getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.clearFlags(67108864);
                window.setStatusBarColor(activity.getResources().getColor(R.color.grey_60));
            }
        }

    }

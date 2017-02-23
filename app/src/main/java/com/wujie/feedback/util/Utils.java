package com.wujie.feedback.util;

import android.content.Context;

/**
 * Created by wujie on 2017/2/23.
 */
public class Utils {

    /**
     * dip 转化成px
     * @param context
     * @param dipValue
     * @return
     */

    public static int dip2px (Context context, float dipValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density*dipValue + 0.5f);
    }

    /**
     * px 转化成dp
     * @param context
     * @param pixValue
     * @return
     */
    public static  int px2dip(Context context, float pixValue) {
        final float density = context.getResources().getDisplayMetrics().density;
        return  (int) (pixValue / density + 0.5f);
    }
}

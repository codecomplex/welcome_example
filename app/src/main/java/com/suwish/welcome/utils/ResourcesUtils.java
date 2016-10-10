package com.suwish.welcome.utils;

import android.view.View;
import android.widget.TextView;

import com.suwish.welcome.BaseActivity;

/**
 * 和资源相关的工具类,主要是隐藏比较繁琐的<code>findViewById(int)</code>
 * 方法
 *
 * @author min.su on 2016/9/26.
 */
public final class ResourcesUtils {

    private ResourcesUtils(){}

    public static void setClick(BaseActivity activity, View.OnClickListener l, int ... ids){
        for (int id : ids){
            View view = activity.findViewById(id);
            if (view == null) continue;
            view.setOnClickListener(l);
        }
    }

    public static void setText(BaseActivity activity, int id, int resId){
        TextView textView = (TextView)activity.findViewById(id);
        if (textView == null) return;
        textView.setText(resId);
    }

    public static void toggleVisibility(BaseActivity activity, boolean visible, int ... ids){
        View view;
        for (int id : ids){
            view = activity.findViewById(id);
            if (view == null) continue;
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public static void toggleVisibility(boolean visible, View ... views){
        for (View view : views){
            view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }
}

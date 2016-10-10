package com.suwish.welcome.mvp;

import android.content.Context;

/**
 *  TODO 增加一个ErrorMessage回调选型，用于向UI层发送
 *   错误信息，并使用标题和内容进行提示，便于UI层使用
 *
 * @author min.su on 2016/9/27.
 */

public interface BaseView {

    Context getContext();
}

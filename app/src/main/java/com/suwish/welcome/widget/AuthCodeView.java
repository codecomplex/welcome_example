package com.suwish.welcome.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.suwish.welcome.R;
import com.suwish.welcome.utils.WeakHandler;

/**
 *
 * @author min.su on 2016/9/29.
 */
public class AuthCodeView extends LinearLayout{

    private EditText editText;

    private Button reloadView;
    private ProgressBar progressBar;

    private TextView textCountDown;

    private CountDownHandler handler;

    public AuthCodeView(Context context) {
        super(context);
        setup(context);
    }

    public AuthCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public AuthCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    private void setup(Context context){
        inflate(context, R.layout.view_auth_code, this);
        editText = (EditText)findViewById(R.id.edit_auth);
        reloadView = (Button)findViewById(R.id.button_get_code);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar_auth);
        textCountDown = (TextView)findViewById(R.id.text_time);

        handler = new CountDownHandler(this);
    }

    public String getAuthCode(){
        return editText.getText().toString();
    }

    public void onLoading(){
        reloadView.setEnabled(false);
        progressBar.setVisibility(VISIBLE);
        textCountDown.setText("");
    }

    public void overLoading(){
        reloadView.setEnabled(true);
        progressBar.setVisibility(GONE);
        handler.removeMessages(CountDownHandler.FLAG_COUNT_DOWN);
        textCountDown.setText("");
    }

    /**
     * 开始倒计时，禁用按钮，隐藏进度条
     *
     */
    public void startCountDown(){
        reloadView.setEnabled(false);
        progressBar.setVisibility(GONE);
        handler.startCountDown();
    }

    private static class CountDownHandler extends WeakHandler<AuthCodeView> {

        final static int FLAG_COUNT_DOWN = 1;
        final static String KEY_TIME_REMAIN = "time";

        CountDownHandler(AuthCodeView owner) {
            super(owner);
        }

        @Override
        public void handleMessage(Message msg) {
            AuthCodeView view = getOwner();
            if (view == null) return;
            Bundle bundle = msg.getData();
            int time = bundle.getInt(KEY_TIME_REMAIN);
            if (time == 0){
                view.overLoading();
                return;
            }
            time = time - 1;
            view.textCountDown.setText(String.valueOf(time));
            Message message = Message.obtain();
            message.what = FLAG_COUNT_DOWN;
            bundle = new Bundle();
            message.setData(bundle);
            bundle.putInt(KEY_TIME_REMAIN, time);
            sendMessageDelayed(message, 1000);
        }

        void startCountDown(){
            Message message = Message.obtain();
            message.what = FLAG_COUNT_DOWN;
            Bundle bundle = new Bundle();
            message.setData(bundle);
            bundle.putInt(KEY_TIME_REMAIN, 30);
            removeMessages(FLAG_COUNT_DOWN);
            sendMessage(message);
        }
    }
}

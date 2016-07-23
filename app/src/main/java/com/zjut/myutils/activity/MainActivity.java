package com.zjut.myutils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zjut.myutils.R;
import com.zjut.myutils.imagethreelevelcache.MyBitmapUtils;

public class MainActivity extends AppCompatActivity {

    private long exitTime = 0;
    private ImageView mImageViewCache;
    private Button mButtonPic;
    private static String PIC_URL = "http://ww3.sinaimg.cn/large/7a8aed7bjw1ezplg7s8mdj20xc0m8jwf.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewCache = (ImageView) findViewById(R.id.iv_cache);
        mButtonPic = (Button) findViewById(R.id.btn_pic);

        mButtonPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBitmapUtils myBitmapUtils = new MyBitmapUtils();
                myBitmapUtils.display(mImageViewCache,PIC_URL);
            }
        });
    }

    /**
     * Android中的“再按一次返回键退出程序“的代码
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.zjut.myutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.zjut.myutils.DateUtil;
import com.zjut.myutils.JsonHelper;
import com.zjut.myutils.R;
import com.zjut.myutils.bean.Bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gsonUse();


        dateFormatUse();
    }

    private void dateFormatUse() {
        String dataStr = DateUtil.getInstance().getDataString_1(null);
        String toStringData = DateUtil.getInstance().stringDateToStringData("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", dataStr);
        String date = DateUtil.getInstance().getDayByDate(
                Calendar.getInstance(), Calendar.DATE, 1);
        String week = DateUtil.getInstance().getDayByDate(
                Calendar.getInstance(), Calendar.WEEK_OF_YEAR, 1);
        String month = DateUtil.getInstance().getDayByDate(
                Calendar.getInstance(), Calendar.MONTH, 1);
        String year = DateUtil.getInstance().getDayByDate(
                Calendar.getInstance(), Calendar.YEAR, 1);
        int lastDay = DateUtil.getInstance().getMonthLastDay(2015, 2);
        System.out.println(dataStr);
        System.out.println(toStringData);
        System.out.println(date);
        System.out.println(week);
        System.out.println(month);
        System.out.println(year);
        System.out.println("2月有"+lastDay+"天");
    }

    private void gsonUse() {
        Bean bean = new Bean();
        bean.age="30";
        bean.name="name";
        String result = JsonHelper.getInstance().createJsonString(bean);
        System.out.println(result);
        Bean bean2 = JsonHelper.getInstance().getObject(result, Bean.class);
        System.out.println(bean2.toString());


        ArrayList<Bean> list = new ArrayList<Bean>();
        list.add(bean);
        list.add(bean2);
        result = JsonHelper.getInstance().createJsonString(list);
        System.out.println(result);
        List<ArrayList<Bean>> list2 = JsonHelper.getInstance().getList(result, new TypeToken<ArrayList<Bean>>(){});
        System.out.println(list2.toString());
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

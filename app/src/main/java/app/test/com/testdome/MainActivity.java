package app.test.com.testdome;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import app.test.com.testdome.view.UIListItemViewGroup;
import app.test.com.testdome.view.model.UIListItemModel;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver myBroadcastReceiver;
    private UIListItemViewGroup mUIListItemViewGroup, mUIListItemViewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIListItem();

    }

    private void initUIListItem() {
        mUIListItemViewGroup = (UIListItemViewGroup) findViewById(R.id.id_uilistItem);
        mUIListItemViewGroup2 = (UIListItemViewGroup) findViewById(R.id.id_uilistItem_2);
        UIListItemModel clear = new UIListItemModel(R.drawable.ic_clear_data, "清理数据");
        clear.setType(UIListItemModel.Type.RED_DOT);
        mUIListItemViewGroup.addItem(clear);
        mUIListItemViewGroup.addItem(new UIListItemModel(R.drawable.ic_clear_data, "个人中心"));

        mUIListItemViewGroup2.addItem(new UIListItemModel(R.drawable.ic_clear_data,"重置密码"));
        initEvents();

    }

    private void initEvents() {
        mUIListItemViewGroup.setItemOnClickListener(new UIListItemViewGroup.ItemOnClickListener() {
            @Override
            public void ItemOnClick(int index) {
                switch (index){
                    case 0:
                        Toast.makeText(getApplicationContext(),"你单击的是清除数据",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),"你单击的是个人中心",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    /**
     * 心跳 广播
     */
    private void initAlarm() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("test");
        registerReceiver(myBroadcastReceiver, intentFilter);


        Intent intent = new Intent();
        intent.setAction("test");
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);

        //获得闹钟管理器
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        //设置任务执行计划
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pi);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myBroadcastReceiver != null)
            unregisterReceiver(myBroadcastReceiver);
    }
}

class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("测试", "成功！！！！");
    }
}
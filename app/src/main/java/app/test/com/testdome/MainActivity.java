package app.test.com.testdome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import app.test.com.testdome.view.ListItemViewGroup;
import app.test.com.testdome.view.adapter.ItemAdapter;
import app.test.com.testdome.view.model.ListItemModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListItemViewGroup mUIListItemViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIListItem();

    }

    private void initUIListItem() {
        mUIListItemViewGroup = findViewById(R.id.id_uilistItem);
        ListItemModel clear = new ListItemModel(R.drawable.ic_clear_data, "清理数据");
        mUIListItemViewGroup.addItem(clear);
        mUIListItemViewGroup.addItem(new ListItemModel(R.drawable.ic_clear_data, "个人中心"));
        mUIListItemViewGroup.addItem(new ListItemModel(R.drawable.ic_clear_data, "个人中心", "1213"));

        initEvents();

    }

    private void initEvents() {
        mUIListItemViewGroup.setItemOnClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "onItemClick: " + position);
            }
        });
    }

}
package app.test.com.testdome.view;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import app.test.com.testdome.view.adapter.ItemAdapter;
import app.test.com.testdome.view.model.ListItemModel;

/**
 * @Title UIListItem
 * @Description: 自定义 操作列表控件（一个图片一文字）
 * @Author: alvin
 * @Date: 2016/4/9.16:03
 * @E-mail: 49467306@qq.com
 */
public class ListItemViewGroup extends RecyclerView {
    private Context mContext;
    private static final String TAG = ListItemViewGroup.class.getSimpleName();
    private List<ListItemModel> mList;

    private ItemAdapter.OnItemClickListener mItemOnClickListener;
    private ItemAdapter mAdapter;

    public ListItemViewGroup(Context context) {
        this(context, null);
    }

    public ListItemViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItemViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }


    private void init() {
        mList = new ArrayList<>();
        mList.clear();
        setLayoutManager(new LinearLayoutManager(getContext()));
        addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new ItemAdapter(mList);
        setAdapter(mAdapter);

    }

    public void addItem(ListItemModel item) {
        mList.add(item);
        if (null != mAdapter) {
            mAdapter.notifyDataSetChanged();
        }

    }

    public void setItemOnClickListener(ItemAdapter.OnItemClickListener itemOnClickListener) {
        mItemOnClickListener = itemOnClickListener;
    }
}

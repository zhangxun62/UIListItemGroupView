package app.test.com.testdome.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.test.com.testdome.R;
import app.test.com.testdome.view.model.UIListItemModel;

/**
 * @Title UIListItem
 * @Description: 自定义 操作列表控件（一个图片一文字）
 * @Author: alvin
 * @Date: 2016/4/9.16:03
 * @E-mail: 49467306@qq.com
 */
public class UIListItemViewGroup extends RelativeLayout {
    private Context mContext;
    private static final String TAG = UIListItemViewGroup.class.getSimpleName();
    private List<UIListItemModel> mList;
    private MyUIListItemAdapter mAdapter;
    private float mDensity;
    private ItemOnClickListener mItemOnClickListener;

    public UIListItemViewGroup(Context context) {
        this(context, null);
    }

    public UIListItemViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIListItemViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UIListItemViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mDensity = mContext.getResources().getDisplayMetrics().density;
        mList = new ArrayList<UIListItemModel>();
        mList.clear();
        ListView mListView = new ListView(mContext);
        mListView.setDividerHeight(1);

        addView(mListView);
        mAdapter = new MyUIListItemAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != mItemOnClickListener)
                    mItemOnClickListener.ItemOnClick(position);
            }
        });

    }

    public void addItem(UIListItemModel item) {
        mList.add(item);
        if (null != mAdapter)
            mAdapter.notifyDataSetChanged();

    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.mItemOnClickListener = itemOnClickListener;
    }

    class MyUIListItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public UIListItemModel getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (getItem(position).getType()== UIListItemModel.Type.DEFAULT)
                return 0;
            else if (getItem(position).getType()== UIListItemModel.Type.RED_DOT)
                return 1;
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = new UIItemView(mContext);
                //默认样式
                if(getItemViewType(position)==0){
                    ImageView view = new ImageView(mContext);
                    view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_left));
                    RelativeLayout.LayoutParams viewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    viewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                    viewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                    viewLayoutParams.rightMargin = (int)(10*mContext.getResources().getDisplayMetrics().density);
                    ((UIItemView)convertView).addView(view, viewLayoutParams);
                }
                viewHolder = new ViewHolder();
                viewHolder.mImageView = (ImageView) convertView.findViewWithTag("imageview");
                viewHolder.mTextView = (TextView) convertView.findViewWithTag("title");
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mImageView.setImageResource(getItem(position).getDrawable());
            viewHolder.mTextView.setText(getItem(position).getTitle());

            return convertView;
        }
    }

    class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }

    public interface ItemOnClickListener {
        void ItemOnClick(int index);
    }
}

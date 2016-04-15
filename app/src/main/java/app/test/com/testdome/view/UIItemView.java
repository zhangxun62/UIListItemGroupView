package app.test.com.testdome.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @Title ItemView
 * @Description:
 * @Author: alvin
 * @Date: 2016/4/9.16:24
 * @E-mail: 49467306@qq.com
 */
public class UIItemView extends RelativeLayout {
    private static final String TAG = UIItemView.class.getSimpleName();
    private Context mContext;
    private float mDensity;
    private int mMargin,mPadding;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UIItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public UIItemView(Context context) {
        this(context, null);
    }

    public UIItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mDensity = mContext.getResources().getDisplayMetrics().density;
        mMargin = (int)(10*mDensity);
        mPadding = (int)(10*mDensity);
        initViews();
    }

    @SuppressWarnings("ResourceType")
    private void initViews() {
        // 初始化 图片
        ImageView imageView = new ImageView(mContext);
        imageView.setTag("imageview");
        imageView.setId(1);
//        imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clear_data));
        RelativeLayout.LayoutParams imageLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

        imageView.setPadding(mPadding,mPadding,mPadding,mPadding);
        addView(imageView, imageLayoutParams);
        // 初始化 文字
        TextView textView = new TextView(mContext);
        textView.setTag("title");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); //设置文字大小 单位SP
        RelativeLayout.LayoutParams textViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Log.e(TAG,"id------>>"+ imageView.getId());
        textViewLayoutParams.addRule(RelativeLayout.RIGHT_OF,imageView.getId()); // 设置 在iamgeview的右面
        textViewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        textViewLayoutParams.leftMargin = mMargin;
        addView(textView, textViewLayoutParams);
//        // 初始化 imageview
//        ImageView view = new ImageView(mContext);
//        view.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_left));
//        RelativeLayout.LayoutParams viewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        viewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//        viewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
//        viewLayoutParams.rightMargin = mMargin;
//        addView(view, viewLayoutParams);


    }
}

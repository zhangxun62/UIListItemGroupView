package app.test.com.testdome.view.model;

/**
 * @Title ListItemModel
 * @Description:
 * @Author: alvin
 * @Date: 2016/4/9.16:17
 * @E-mail: 494673006@qq.com
 */
public class ListItemModel {
    private static final String TAG = ListItemModel.class.getSimpleName();
    public static final int DEFAULT = 140;
    public static final int RED_DOT = 153;
    public static final int RIGHT_TEXT = 275;
    private int mLeftDrawableId;
    private String mTitle;
    private String mRightText;
    private int mType = DEFAULT;

    public ListItemModel(int leftDrawableId, String title) {
        mLeftDrawableId = leftDrawableId;
        mTitle = title;
    }

    public ListItemModel(int leftDrawableId, String title, String rightText) {
        mLeftDrawableId = leftDrawableId;
        mTitle = title;
        mType = RIGHT_TEXT;
        mRightText = rightText;
    }

    public String getRightText() {
        return mRightText;
    }

    public void setRightText(String rightText) {
        mRightText = rightText;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getLeftDrawableId() {
        return mLeftDrawableId;
    }

    public void setLeftDrawableId(int leftDrawableId) {
        mLeftDrawableId = leftDrawableId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}

package app.test.com.testdome.view.model;

/**
 * @Title UIListItemModel
 * @Description:
 * @Author: alvin
 * @Date: 2016/4/9.16:17
 * @E-mail: 49467306@qq.com
 */
public class UIListItemModel {
    private static final String TAG = UIListItemModel.class.getSimpleName();
    private int mDrawable;
    private String mTitle;
    private Type mType = Type.DEFAULT;
    public UIListItemModel(int drawable, String title) {
        mDrawable = drawable;
        mTitle = title;
    }

  public enum Type{
        DEFAULT,RED_DOT
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public void setDrawable(int drawable) {
        mDrawable = drawable;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}

package app.test.com.testdome.view.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.test.com.testdome.R;
import app.test.com.testdome.view.model.ListItemModel;

/**
 * @Title ItemAdapter
 * @Description:
 * @author: alvin
 * @Date: 2018/5/9.09:24
 * @E-mail: 494673006@qq.com
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ListItemModel> mList;
    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        /**
         * item 点击事件
         *
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);
    }

    public ItemAdapter(List<ListItemModel> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayoutCompat view = (LinearLayoutCompat) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_ui_list_item, parent, false);
        if (viewType == ListItemModel.RIGHT_TEXT) {
            AppCompatTextView textView = view.findViewWithTag("rightText");
            if (textView == null) {
                textView = new AppCompatTextView(parent.getContext());
                LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                params.rightMargin = 16;
                textView.setTag("rightText");
                textView.setLayoutParams(params);
                // 将控件添加到最右边的
                view.addView(textView, view.getChildCount() - 1);
            }


        }
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        ListItemModel item = mList.get(position);
        holder.setText(R.id.id_tv_title, item.getTitle());
        if (!item.isArrow()) {
            holder.findId(R.id.id_iv_right_image).setVisibility(View.INVISIBLE);
        } else {
            holder.setImage(R.id.id_iv_right_image, item.getRightDrawableId());
        }
        switch (item.getType()) {
            case ListItemModel.DEFAULT:
                holder.setImage(R.id.id_iv_left_image, item.getLeftDrawableId());
                break;
            case ListItemModel.RED_DOT:
                break;
            case ListItemModel.RIGHT_TEXT:
                holder.setText("rightText", item.getRightText());
                break;
            default:
                holder.setImage(R.id.id_iv_left_image, item.getLeftDrawableId());
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener == null) {
                    return;
                }
                mOnItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);

        }

        public <T extends View> T findId(int id) {
            return this.itemView.findViewById(id);
        }

        public <T extends View> T findId(Object tag) {
            return this.itemView.findViewWithTag(tag);
        }

        public ItemViewHolder setText(int id, CharSequence text) {
            ((AppCompatTextView) findId(id)).setText(text);
            return this;
        }

        public ItemViewHolder setText(Object tag, CharSequence text) {
            ((AppCompatTextView) findId(tag)).setText(text);
            return this;
        }

        public ItemViewHolder setImage(int id, int imageId) {
            ((AppCompatImageView) findId(id)).setImageResource(imageId);
            return this;
        }

        public ItemViewHolder setImage(int id, Bitmap bitmap) {
            ((AppCompatImageView) findId(id)).setImageBitmap(bitmap);
            return this;
        }

        public ItemViewHolder setImage(int id, Drawable drawable) {
            ((AppCompatImageView) findId(id)).setImageDrawable(drawable);
            return this;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }
}

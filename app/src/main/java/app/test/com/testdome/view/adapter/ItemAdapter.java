package app.test.com.testdome.view.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_ui_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        ListItemModel item = mList.get(position);
        holder.setText(R.id.id_tv_title, item.getTitle());
        switch (item.getType()) {
            case ListItemModel.DEFAULT:
                holder.setImage(R.id.id_iv_left_image, item.getLeftDrawableId());
                break;
            case ListItemModel.RED_DOT:
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

        public ItemViewHolder setText(int id, CharSequence text) {
            ((AppCompatTextView) findId(id)).setText(text);
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

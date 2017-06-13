package com.pan.retrofitdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Author : Pan
 * Date : 1/9/17
 */

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder>{
    private Context mContext;
    private List<DistrictBean.ResultBean> mResultBeanList;

    private OnItemClickListener mListener;

    public DistrictAdapter(Context context) {
        this.mContext = context;
    }

    public void refresh (List<DistrictBean.ResultBean> resultBeanList) {
        this.mResultBeanList = resultBeanList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_district, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mResultBeanList.get(position).getFullname());
        holder.mCheckBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mResultBeanList == null ? 0 : mResultBeanList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        CheckBox mCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.tv_item);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);

            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (null != mListener) {
                            mListener.onItemClick(getPosition());
                        }
                    }
                }
            });
        }
    }
}

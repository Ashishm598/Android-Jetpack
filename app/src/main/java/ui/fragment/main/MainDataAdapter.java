package ui.fragment.main;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.MainListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import pojo.response.Response;

public class MainDataAdapter extends RecyclerView.Adapter<MainDataAdapter.MainDataViewHolder> {

    private Context mContext;
    private List<Response> mData = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private final String TAG = this.getClass().getSimpleName();

    public MainDataAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MainDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MainListLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_list_layout, parent, false);

        return new MainDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainDataViewHolder holder, int position) {
        try {
            holder.binding.setData(mData.get(position));
            holder.binding.tvTag.setTextColor(setTextColor(mData.get(position).getColor()));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public void setData(List<Response> value) {
        this.mData.addAll(value);
        notifyDataSetChanged();
    }

    private int setTextColor(String colorName) {
        return (colorName != null) ? (colorName.equalsIgnoreCase("red")) ? Color.RED : Color.GREEN : Color.WHITE;
    }

    class MainDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MainListLayoutBinding binding;

        MainDataViewHolder(MainListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

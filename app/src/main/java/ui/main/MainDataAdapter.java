package ui.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
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

    MainDataAdapter(Context mContext) {
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
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    void setData(List<Response> value) {
        this.mData.addAll(value);
        notifyDataSetChanged();
    }

    class MainDataViewHolder extends RecyclerView.ViewHolder {

        MainListLayoutBinding binding;

        MainDataViewHolder(MainListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

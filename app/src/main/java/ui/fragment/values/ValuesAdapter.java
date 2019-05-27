package ui.fragment.values;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.ValuesListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.ValuesViewHolder> {

    private Context mContext;
    private List<Float> values = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private final String TAG = this.getClass().getSimpleName();


    public ValuesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ValuesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ValuesListLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.values_list_layout, parent, false);

        return new ValuesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ValuesViewHolder holder, int position) {
        try {
            holder.binding.setValues(values.get(position));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return (values != null) ? values.size() : 0;
    }

    public void setData(List<Float> values) {
        this.values.addAll(values);
        notifyDataSetChanged();
    }

    class ValuesViewHolder extends RecyclerView.ViewHolder {

        ValuesListLayoutBinding binding;

        ValuesViewHolder(ValuesListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.tvText.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }
}

package ui.fragment.criteria;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.CriteriaListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import pojo.response.Criteria;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.CriteriaViewHolder> {

    private Context mContext;
    private List<Criteria> criteria = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private final String TAG = this.getClass().getSimpleName();


    public CriteriaAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CriteriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CriteriaListLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.criteria_list_layout, parent, false);

        return new CriteriaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CriteriaViewHolder holder, int position) {
        try {
            holder.binding.tvText.setText(criteria.get(position).getSpannables().get(position), TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return (criteria != null) ? criteria.size() : 0;
    }

    public void setData(List<Criteria> criteria) {
        this.criteria.addAll(criteria);
        notifyDataSetChanged();
    }


    class CriteriaViewHolder extends RecyclerView.ViewHolder {

        CriteriaListLayoutBinding binding;

        CriteriaViewHolder(CriteriaListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.tvText.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }
}

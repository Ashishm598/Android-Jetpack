package ui.fragment.criteria;

import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.CriteriaListLayoutBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pojo.response.Criteria;
import pojo.response.Variable;
import util.Constants;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.CriteriaViewHolder> {

    private Context mContext;
    private List<Criteria> criteria = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private final String TAG = this.getClass().getSimpleName();
    Spannable spannable;


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
            holder.binding.tvText.setText(getCurrentSpans(criteria.get(position)), TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    private Spannable getCurrentSpans(Criteria criteria) {
        String source = criteria.getText();
        spannable = new SpannableStringBuilder(source);

        if (criteria.getVariable() != null) {

            for (Map.Entry<String, Variable> item : criteria.getVariable().entrySet()) {
                String currentVarKey = item.getKey();
                Variable variable = item.getValue();

                int startIndex = source.indexOf(currentVarKey);

                Log.e("startIndex", String.valueOf(startIndex));

                String string1 = source.substring(0, startIndex);
                String string2 = source.substring(startIndex, startIndex + currentVarKey.length());

                Log.e("string1", string1);
                Log.e("string2", string2);

                String finalString = string2.replace(currentVarKey, getVarValue(variable));

                int endIndex = finalString.length();

                String newSource = string1.concat(finalString);

                Log.e("FinalString", finalString);

                Log.e("endIndex", String.valueOf(endIndex));

                Log.e("newSource", newSource);

                spannable = new SpannableStringBuilder(newSource);

                spannable.setSpan(
                        valuesClick(variable),
                        startIndex,
                        startIndex + endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            }
        }
        return spannable;
    }

    private String getVarValue(Variable variable) {
        String value;
        if (variable.getValues() != null) {
            value = String.valueOf(variable.getValues().get(0));
        } else {
            value = String.valueOf(variable.getDefault_value());
        }
        return "(" + value + ")";
    }


    private ClickableSpan valuesClick(final Variable variable) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.VARIABLE, variable);
        return new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (variable.getValues() != null) {
                    Navigation.findNavController(widget).navigate(R.id.destinationValues, bundle);
                } else {
                    Navigation.findNavController(widget).navigate(R.id.destinationIndicator, bundle);
                }
            }
        };
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

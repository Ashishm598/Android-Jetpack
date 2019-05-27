package ui.fragment.criteria;

import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
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

import java.util.List;
import java.util.Map;

import pojo.response.Criteria;
import pojo.response.Variable;
import util.Constants;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.CriteriaViewHolder> {

    private Context mContext;
    private List<Criteria> criteria;
    private LayoutInflater layoutInflater;
    private final String TAG = this.getClass().getSimpleName();


    public CriteriaAdapter(Context mContext, List<Criteria> criteriaList) {
        this.mContext = mContext;
        this.criteria = criteriaList;
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
            holder.binding.tvText.setText(replaceVarWithVal(criteria.get(position)), TextView.BufferType.SPANNABLE);
            setHighLightedText(holder.binding.tvText, criteria.get(position));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void setHighLightedText(TextView tvText, Criteria criteria) {
        String source = tvText.getText().toString();
        Spannable spannable = (Spannable) tvText.getText();
        spannable = new SpannableString(source);

        if (criteria.getVariable() != null) {
            for (Variable variable : criteria.getVariable().values()) {
                String value = getVarValue(variable);
                int startIndex = source.indexOf(value);
                int endIndex = startIndex + value.length();

                spannable.setSpan(
                        getClickableSpans(variable),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );

                tvText.setText(spannable, TextView.BufferType.SPANNABLE);
            }
        }
    }

    private String replaceVarWithVal(Criteria criteria) {
        String source = criteria.getText();
        if (criteria.getVariable() != null) {
            for (Map.Entry<String, Variable> item : criteria.getVariable().entrySet()) {
                String currentVarKey = item.getKey();
                Variable variable = item.getValue();
                source = source.replace(currentVarKey, getVarValue(variable));
            }
        }
        return source;
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

    private ClickableSpan getClickableSpans(final Variable variable) {
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
            binding.tvText.setSpannableFactory(mSpannableFactory);
            binding.tvText.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }

    private Spannable.Factory mSpannableFactory = new Spannable.Factory() {
        @Override
        public Spannable newSpannable(CharSequence source) {
            return super.newSpannable(source);
        }
    };


}

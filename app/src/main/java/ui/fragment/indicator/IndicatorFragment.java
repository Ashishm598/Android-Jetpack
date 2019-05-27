package ui.fragment.indicator;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.FragmentIndicatorBinding;

import pojo.response.Variable;
import util.Constants;


public class IndicatorFragment extends Fragment {

    private FragmentIndicatorBinding binding;
    private View rootView;
    private Variable variable;

    public IndicatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            variable = getArguments().getParcelable(Constants.VARIABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_indicator, container, false);
        rootView = binding.getRoot();

        binding.setVariable(variable);

        return rootView;
    }

}

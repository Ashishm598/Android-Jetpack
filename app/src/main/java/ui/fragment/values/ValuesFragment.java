package ui.fragment.values;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.FragmentValuesBinding;

import pojo.response.Variable;
import util.Constants;

public class ValuesFragment extends Fragment implements ValuesFragmentContract.View {

    private FragmentValuesBinding binding;
    private View rootView;
    private Context mContext;
    private ValuesAdapter valuesAdapter;
    private Variable variable;

    public ValuesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        if (getArguments() != null) {
            variable = getArguments().getParcelable(Constants.VARIABLE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_values, container, false);
        rootView = binding.getRoot();

        initView();
        initRecyclerView();

        return rootView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initRecyclerView() {
        RecyclerView recyclerView = binding.rvValues;
        valuesAdapter = new ValuesAdapter(mContext);
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(valuesAdapter);
        valuesAdapter.setData(variable.getValues());
    }
}

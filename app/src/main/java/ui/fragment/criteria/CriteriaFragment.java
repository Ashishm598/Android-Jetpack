package ui.fragment.criteria;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.FragmentCriteriaBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pojo.response.Criteria;
import pojo.response.Response;
import util.Constants;
import util.MySpanTask;


public class CriteriaFragment extends Fragment implements CriteriaFragmentContract.View {

    private View rootView;
    private FragmentCriteriaBinding binding;
    private Response data;
    private List<Criteria> criteriaList = new ArrayList<>();
    private CriteriaAdapter criteriaAdapter;
    private Context mContext;

    public CriteriaFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_criteria, container, false);
        rootView = binding.getRoot();

        initRecyclerView(); //initRecyclerView

        if (getArguments() != null) {
            data = getArguments().getParcelable(Constants.CURRENT_DATA);
            criteriaList = getArguments().getParcelableArrayList(Constants.CRITERIA);
            criteriaAdapter.setData(criteriaList);
            binding.setData(data);
            binding.setColor(new Color());
            // setSpans(criteriaList);
        }

        return rootView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initRecyclerView() {
        RecyclerView recyclerView = binding.rvCriteria;
        criteriaAdapter = new CriteriaAdapter(mContext);
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(criteriaAdapter);
    }

    @Override
    public void setSpans(List<Criteria> criteria) {
        MySpanTask mySpanTask = new MySpanTask(getContext());
        try {
            criteriaList = mySpanTask.execute(criteria).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

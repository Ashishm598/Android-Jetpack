package ui.fragment.main;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.FragmentMainBinding;

import viewmodels.MainFragmentViewModel;


public class MainFragment extends Fragment implements MainFragmentContract.View{

    private View rootView;
    private FragmentMainBinding binding;
    private MainFragmentViewModel viewModel;
    private MainDataAdapter mainDataAdapter;
    private Context mContext;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        rootView = binding.getRoot();

        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);

        initRecyclerView(); // initRecyclerView

        viewModel.init(); // init

        viewModel.getResponse(); //getData

        loadData(); // load

        return rootView;
    }


    @Override
    public void initRecyclerView() {
        RecyclerView recyclerView = binding.rvData;
        mainDataAdapter = new MainDataAdapter(mContext);
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(mainDataAdapter);
    }

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadData() {
        viewModel.getProgressUpdate().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isOnProgress) {
                if (isOnProgress) {
                    showProgressBar();
                } else {
                    hideProgressBar();
                    mainDataAdapter.setData(viewModel.getResponseLiveData().getValue());
                }
            }
        });
    }
}

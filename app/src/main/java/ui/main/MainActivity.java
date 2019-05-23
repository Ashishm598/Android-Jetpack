package ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.ActivityMainBinding;

import viewmodels.MainActivityViewModel;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    ActivityMainBinding binding;
    MainActivityViewModel viewModel;
    MainDataAdapter mainDataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        initRecyclerView(); // initRecyclerView

        viewModel.init(); // init

        viewModel.getResponse(); //getData

        loadData(); // load
    }


    @Override
    public void initRecyclerView() {
        RecyclerView recyclerView = binding.rvData;
        mainDataAdapter = new MainDataAdapter(this);
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);
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

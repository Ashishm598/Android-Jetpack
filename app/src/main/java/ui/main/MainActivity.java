package ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ashish.marketpluseassignment.R;
import com.ashish.marketpluseassignment.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    ActivityMainBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    @Override
    public void initView() {
        navController = Navigation.findNavController(this, R.id.nav_main_host_fragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}

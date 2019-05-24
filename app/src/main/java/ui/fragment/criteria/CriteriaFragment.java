package ui.fragment.criteria;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ashish.marketpluseassignment.R;


public class CriteriaFragment extends Fragment {

    private View rootView;

    public CriteriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_criteria, container, false);
        return rootView;
    }

}

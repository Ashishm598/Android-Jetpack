package util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.ashish.marketpluseassignment.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pojo.response.Criteria;
import pojo.response.Variable;

public class MySpanTask extends AsyncTask<List<Criteria>, String, List<Criteria>> {

    private Context context;
    private String currentVarKey;
    private Variable variable;
    private String source;


    public MySpanTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<Criteria> doInBackground(List<Criteria>... lists) {
        List<Spannable> spannableList = new ArrayList<>();

        for (int i = 0; i < lists[0].size(); i++) {
            source = lists[0].get(i).getText();
            Spannable spannable = new SpannableStringBuilder(source);

            if (lists[0].get(i).getVariable() != null) {

                int startIndex, endIndex;

                for (Map.Entry<String, Variable> item : lists[0].get(i).getVariable().entrySet()) {
                    currentVarKey = item.getKey();
                    variable = item.getValue();

                    startIndex = source.indexOf(currentVarKey.charAt(0));
                    endIndex = startIndex + 2;

                    spannable.setSpan(
                            valuesClick(variable),
                            startIndex,
                            endIndex,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                }
            }
            spannableList.add(spannable);
            lists[0].get(i).setSpannables(spannableList);
        }

        return lists[0];
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
}




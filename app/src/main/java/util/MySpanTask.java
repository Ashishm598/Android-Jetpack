package util;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pojo.response.Criteria;
import pojo.response.Variable;

public class MySpanTask extends AsyncTask<List<Criteria>, String, List<Criteria>> {

    private Context context;
    String currentVarKey;
    Variable variable;


    public MySpanTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<Criteria> doInBackground(List<Criteria>... lists) {
        List<Spannable> spannableList = new ArrayList<>();

        for (int i = 0; i < lists[0].size(); i++) {
            String source = lists[0].get(i).getText();
            Spannable spannable = new SpannableStringBuilder(source);

            if (lists[0].get(i).getVariable() != null) {

                int startIndex;

                for (Map.Entry<String, Variable> item : lists[0].get(i).getVariable().entrySet()) {
                    currentVarKey = item.getKey();
                    variable = item.getValue();

                    startIndex = source.indexOf(currentVarKey.charAt(0));

                    if (variable.getValues() != null) {
                        variable.getValues().get(0);
                    } else {
                        variable.getDefault_value();
                    }

                    spannable.setSpan(
                            valuesClick(variable),
                            startIndex,
                            startIndex + 2,
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
        return new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (variable.getValues() != null) {
                    Toast.makeText(context, "VALUES!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "INDICATOR!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}




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

                int startIndex, endIndex, finalStartIndex, finalEndIndex;

                for (Map.Entry<String, Variable> item : lists[0].get(i).getVariable().entrySet()) {
                    currentVarKey = item.getKey();
                    variable = item.getValue();

                    startIndex = source.indexOf(currentVarKey.charAt(0));
                    endIndex = startIndex + 1;

                    String newString = setVariableVal(source, startIndex, endIndex, variable);

                    finalStartIndex = newString.indexOf("(");
                    finalEndIndex = newString.indexOf(")");

                    spannable.setSpan(
                            valuesClick(variable),
                            finalStartIndex,
                            finalEndIndex,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                }
            }
            spannableList.add(spannable);
            lists[0].get(i).setSpannables(spannableList);
        }

        return lists[0];
    }

    private String setVariableVal(String source, int startIndex, int endIndex, Variable variable) {
        String string = source;
        String value;
        string = Util.replace(string, startIndex, '(');
        string = Util.replace(string, endIndex, ')');
        if (variable != null) {
            value = String.valueOf(variable.getValues().get(0));
        } else {
            value = String.valueOf(variable.getDefault_value());
        }
        return string;
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




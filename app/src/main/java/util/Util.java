package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.HashMap;

import pojo.response.Criteria;


public class Util {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static HashMap<String, Spannable> setSpannableVar(final Criteria criteria) {
        String text = criteria.getText();
        HashMap<String, Spannable> spanHashMap = new HashMap<>();
        Spannable spannable;

        if (criteria.getVariable() != null) {
            spannable = Spannable.Factory.getInstance().newSpannable(text);
            for (String currentKey : criteria.getVariable().keySet()) {
                int i1 = text.indexOf(currentKey.charAt(0));
                int i2 = text.indexOf(currentKey.charAt(currentKey.length() - 1));
                spannable.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Log.e("CLICKED :", "YEASS!!");
                    }
                }, i1, i2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spanHashMap.put(currentKey, spannable);
            }
            return spanHashMap;
        }
        return null;
    }

    public static Spannable getSpan(HashMap<String, Spannable> data) {
        if (data != null) {
            for (String key : data.keySet()) {
                return data.get(key);
            }
        }
        return null;
    }


}

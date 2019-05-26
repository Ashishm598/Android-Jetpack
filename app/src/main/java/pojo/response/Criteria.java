package pojo.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Spannable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Criteria implements Parcelable {

    @Expose
    @SerializedName("text")
    private String text;
    @Expose
    @SerializedName("type")
    private String type;
    @SerializedName("variable")
    @Expose
    private HashMap<String, Variable> variable;

    private List<Spannable> spannables;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Variable> getVariable() {
        return variable;
    }

    public void setVariable(HashMap<String, Variable> variable) {
        this.variable = variable;
    }

    public List<Spannable> getSpannables() {
        return spannables;
    }

    public void setSpannables(List<Spannable> spannables) {
        this.spannables = spannables;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", variable=" + variable +
                ", spannables=" + spannables +
                '}';
    }

    public Criteria() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.type);
        dest.writeSerializable(this.variable);
        dest.writeList(this.spannables);
    }

    protected Criteria(Parcel in) {
        this.text = in.readString();
        this.type = in.readString();
        this.variable = (HashMap<String, Variable>) in.readSerializable();
        this.spannables = new ArrayList<Spannable>();
        in.readList(this.spannables, Spannable.class.getClassLoader());
    }

    public static final Creator<Criteria> CREATOR = new Creator<Criteria>() {
        @Override
        public Criteria createFromParcel(Parcel source) {
            return new Criteria(source);
        }

        @Override
        public Criteria[] newArray(int size) {
            return new Criteria[size];
        }
    };
}

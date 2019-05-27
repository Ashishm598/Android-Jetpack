package pojo.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

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


    @Override
    public String toString() {
        return "Criteria{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", variable=" + variable +
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
    }

    protected Criteria(Parcel in) {
        this.text = in.readString();
        this.type = in.readString();
        this.variable = (HashMap<String, Variable>) in.readSerializable();
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

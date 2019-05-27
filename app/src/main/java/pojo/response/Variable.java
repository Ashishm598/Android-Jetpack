package pojo.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Variable implements Parcelable {

    @Expose
    @SerializedName("values")
    private ArrayList<Float> values;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("default_value")
    private int default_value;
    @Expose
    @SerializedName("max_value")
    private int max_value;
    @Expose
    @SerializedName("min_value")
    private int min_value;
    @Expose
    @SerializedName("parameter_name")
    private String parameter_name;
    @Expose
    @SerializedName("study_type")
    private String study_type;

    public ArrayList<Float> getValues() {
        return values;
    }

    public void setValues(ArrayList<Float> values) {
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDefault_value() {
        return default_value;
    }

    public void setDefault_value(int default_value) {
        this.default_value = default_value;
    }

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public int getMin_value() {
        return min_value;
    }

    public void setMin_value(int min_value) {
        this.min_value = min_value;
    }

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }

    public String getStudy_type() {
        return study_type;
    }

    public void setStudy_type(String study_type) {
        this.study_type = study_type;
    }

    public Variable() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.values);
        dest.writeString(this.type);
        dest.writeInt(this.default_value);
        dest.writeInt(this.max_value);
        dest.writeInt(this.min_value);
        dest.writeString(this.parameter_name);
        dest.writeString(this.study_type);
    }

    protected Variable(Parcel in) {
        this.values = new ArrayList<Float>();
        in.readList(this.values, Float.class.getClassLoader());
        this.type = in.readString();
        this.default_value = in.readInt();
        this.max_value = in.readInt();
        this.min_value = in.readInt();
        this.parameter_name = in.readString();
        this.study_type = in.readString();
    }

    public static final Creator<Variable> CREATOR = new Creator<Variable>() {
        @Override
        public Variable createFromParcel(Parcel source) {
            return new Variable(source);
        }

        @Override
        public Variable[] newArray(int size) {
            return new Variable[size];
        }
    };
}

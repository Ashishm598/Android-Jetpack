package pojo.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response implements Parcelable {

    @Expose
    @SerializedName("criteria")
    private ArrayList<Criteria> criteria;
    @Expose
    @SerializedName("color")
    private String color;
    @Expose
    @SerializedName("tag")
    private String tag;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public ArrayList<? extends Parcelable> getCriteria() {
        return criteria;
    }

    public void setCriteria(ArrayList<Criteria> criteria) {
        this.criteria = criteria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Response{" +
                "criteria=" + criteria +
                ", color='" + color + '\'' +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Response() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.criteria);
        dest.writeString(this.color);
        dest.writeString(this.tag);
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }

    protected Response(Parcel in) {
        this.criteria = in.createTypedArrayList(Criteria.CREATOR);
        this.color = in.readString();
        this.tag = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel source) {
            return new Response(source);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };
}

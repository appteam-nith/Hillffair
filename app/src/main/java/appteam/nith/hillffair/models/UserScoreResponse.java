package appteam.nith.hillffair.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 10/6/2016.
 */
public class UserScoreResponse implements Parcelable{


    @SerializedName("score")
    private int score;

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    public UserScoreResponse(int score, boolean success, String message) {
        this.score = score;
        this.success = success;
        this.message = message;
    }

    protected UserScoreResponse(Parcel in) {
        score = in.readInt();
        success = in.readByte() != 0;
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(score);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserScoreResponse> CREATOR = new Creator<UserScoreResponse>() {
        @Override
        public UserScoreResponse createFromParcel(Parcel in) {
            return new UserScoreResponse(in);
        }

        @Override
        public UserScoreResponse[] newArray(int size) {
            return new UserScoreResponse[size];
        }
    };

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

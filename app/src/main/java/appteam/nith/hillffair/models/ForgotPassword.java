package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jatin on 10/14/2016.
 */

public class ForgotPassword {

    @SerializedName("user")
    public ForgotPassword.Forgot user;

    @SerializedName("success")
    public boolean success;

    @SerializedName("msg")
    public  String msg;

    public ForgotPassword(ForgotPassword.Forgot user, Boolean success, String msg){
        this.user = user;
        this.success = success;
        this.msg = msg;
    }

    public class Forgot{

        @SerializedName("id")
        private String id;

        @SerializedName("code")
        private int code;

        public Forgot(String id,int code)
        {
            this.id=id;
            this.code = code;
        }


        public String get_id1() {
            return id;
        }

        public void set_id1(String _id) {
            this.id = id;
        }

        public int getCode() {
            return code;
        }

        public void setode(int code) {
            this.code = code;
        }

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}

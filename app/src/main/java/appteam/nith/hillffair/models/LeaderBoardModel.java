package appteam.nith.hillffair.models;

import appteam.nith.hillffair.activities.LeaderBoardActivity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Akatsuki on 10/12/2016.
 */
public class LeaderBoardModel {

    @SerializedName("users")
    private ArrayList<LeaderBoardActivity.LeaderBoardUserModel> users;

    public LeaderBoardModel(ArrayList<LeaderBoardActivity.LeaderBoardUserModel> users) {
        this.users = users;
    }

    public ArrayList<LeaderBoardActivity.LeaderBoardUserModel> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<LeaderBoardActivity.LeaderBoardUserModel> users) {
        this.users = users;
    }
}
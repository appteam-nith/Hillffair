package appteam.nith.hillffair.models;

/**
 * Created by parvesh_dhull on 30/9/16.
 */

public class CardsData {


    private String user_name ;
    private String user_msg;
    private String post_date ;
    private boolean lyk_status ;
    private int no_of_likes;
    private String post_img;

    public CardsData() {
    }

    public CardsData(String user_name, String post_img, String user_msg,
                     boolean lyk_status, int no_of_likes, String post_date) {
        this.user_name = user_name;
        this.post_img = post_img;
        this.user_msg = user_msg;
        this.lyk_status = lyk_status;
        this.no_of_likes = no_of_likes;
        this.post_date = post_date;

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPost_img() {
        return post_img;
    }

    public void setPost_img(String post_img) {
        this.post_img = post_img;
    }

    public String getUser_msg() {
        return user_msg;
    }

    public void setUser_msg(String user_msg) {
        this.user_msg = user_msg;
    }
    public boolean getLyk_status() {
        return lyk_status;
    }

    public void setLyk_status(boolean lyk_status) {
        this.lyk_status = lyk_status;
    }
    public int getNo_of_likes() {
        return no_of_likes;
    }

    public void setNo_of_likes(int no_of_likes) {
        this.no_of_likes = no_of_likes;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

}

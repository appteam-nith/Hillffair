package com.appteamnith.hillffair.models;
/**
 * Created by Akatsuki on 3/23/2016.
 */
public class ClubEvent {
    private int image_id;
    private String name;


    public ClubEvent(int image_id, String name) {
        this.image_id = image_id;
        this.name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public String getName() {
        return name;
    }
}

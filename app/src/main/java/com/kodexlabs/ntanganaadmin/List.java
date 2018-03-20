package com.kodexlabs.ntanganaadmin;

/**
 * Created by Aritra on 10-12-2017.
 */

public class List  {
    private String  date,name,image;
public List(){

}
    public List(String date, String name, String image) {
        this.date = date;
        this.name = name;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.karla00058615.gamenews.classes;

import java.util.ArrayList;

/**
 * Created by Karla on 11/6/2018.
 */

public class NewFav {
    private String succes;
    private New add;

    public NewFav(String succes, New add) {
        this.succes = succes;
        this.add = add;
    }

    public String getSucces() {
        return succes;
    }

    public void setSucces(String succes) {
        this.succes = succes;
    }

    public New getAdd() {
        return add;
    }

    public void setAdd(New add) {
        this.add = add;
    }
}

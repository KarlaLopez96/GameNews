package com.karla00058615.gamenews.classes;

import java.util.ArrayList;

/**
 * Created by Karla on 11/6/2018.
 */

public class NewFav {
    private String success;
    private New add;

    public NewFav(String success, New add) {
        this.success = success;
        this.add = add;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public New getAdd() {
        return add;
    }

    public void setAdd(New add) {
        this.add = add;
    }
}

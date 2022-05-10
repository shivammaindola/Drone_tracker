package com.microdrones.technical_test.model.missions;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Mission {
    @SerializedName("name")
    public String name;
    @SerializedName("horizontalSpeed")
    public double horizontalSpeed;
    @SerializedName("altitude")
    public double altitude;
    @SerializedName("points")
    public ArrayList<Points> points;


    static public class Points {
        @SerializedName("latitude")
        public double latitude;
        @SerializedName("longitude")
        public double longitude;
    }


}

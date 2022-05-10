package com.microdrones.technical_test.model.configurations;

import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("verticalSpeeds")
    public VerticalSpeeds verticalSpeeds;
    @SerializedName("energy")
    public Energy energy;
    @SerializedName("payload")
    public payload payload;

    static public class VerticalSpeeds {
        @SerializedName("ascension")
        public double ascension;
        @SerializedName("descent")
        public double descent;
    }

    static public class Energy {
        @SerializedName("numberOfBatteries")
        public double numberOfBatteries;
        @SerializedName("capacity")
        public double capacity;
    }

    static public class payload {
        @SerializedName("additionalLoad")
        public double additionalLoad;
    }

}

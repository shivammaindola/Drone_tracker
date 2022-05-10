package com.microdrones.technical_test.model.drones;

import com.google.gson.annotations.SerializedName;

public class Drone {
    @SerializedName("currentLoadInFlight")
    public CurrentLoadInFlight currentLoadInFlight;
    @SerializedName("forcedLandingCharge")
    public double forcedLandingCharge;


    static public class CurrentLoadInFlight {
        @SerializedName("ascension")
        public double ascension;
        @SerializedName("descent")
        public double descent;
        @SerializedName("translation")
        public double translation;
    }

}

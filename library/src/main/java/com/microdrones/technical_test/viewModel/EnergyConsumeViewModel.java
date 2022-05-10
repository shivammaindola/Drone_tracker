package com.microdrones.technical_test.viewModel;

import androidx.lifecycle.ViewModel;

import com.microdrones.technical_test.model.configurations.Config;
import com.microdrones.technical_test.model.drones.Drone;
import com.microdrones.technical_test.model.missions.Mission;


public class EnergyConsumeViewModel extends ViewModel {

    static double totalEnergy=0;

    public static Boolean getFlight(Config config, Drone drone, Mission mission){

        boolean flown=false;
        int size=mission.points.size();
        double horizontalDistance=0;

        for(int i=0;i<size-1;i++){
            double latitude1=mission.points.get(i).latitude;
            double longitude1 = mission.points.get(i).longitude;
            double latitude2=mission.points.get(i+1).latitude;
            double longitude2 = mission.points.get(i+1).longitude;

            double distance=calculateDistance(latitude1,longitude1,latitude2,longitude2);

            horizontalDistance+=distance;
        }

        //calculate energy
        double additionalPayload=0;

        if(config.payload!=null){
            additionalPayload=config.payload.additionalLoad;
        }

        double energyUp=mission.altitude*config.verticalSpeeds.ascension*(drone.currentLoadInFlight.ascension+additionalPayload);
        double energyHorizontal= horizontalDistance*mission.horizontalSpeed*(drone.currentLoadInFlight.translation+additionalPayload);
        double energyDown= mission.altitude*config.verticalSpeeds.descent*(drone.currentLoadInFlight.descent+additionalPayload);

        totalEnergy= energyUp+energyHorizontal+energyDown;

        double capacity=config.energy.capacity*config.energy.numberOfBatteries;
        double actualCapacity= capacity-drone.forcedLandingCharge;

        if(actualCapacity>totalEnergy){
            //reach
            flown=true;
        }
        else{
            //fall
            flown=false;
        }

        return flown;
    }

    public double getTotalEnergy(){
        return totalEnergy;
    }

    public static double calculateDistance(double lat1, double lon1,
                                           double lat2, double lon2){
        final int R = 6371000; // Radius of the earth

        double latDistance = Math.toRadians(lat2-lat1);
        double lonDistance =  Math.toRadians(lon2-lon1);

        double a = Math.sin(latDistance / 2.0) * Math.sin(latDistance / 2.0) +
                Math.cos( Math.toRadians(lat1)) * Math.cos( Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2.0) * Math.sin(lonDistance / 2.0);

        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = R * c;
        return distance;
    }
}

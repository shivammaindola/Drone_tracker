package com.microdrones.technical_test.viewModel;


import static org.junit.Assert.*;

import android.util.Log;

import com.microdrones.technical_test.model.configurations.Config;
import com.microdrones.technical_test.model.drones.Drone;
import com.microdrones.technical_test.model.missions.Mission;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EnergyConsumeViewModelTest {
    double precision = 10; //meters
    @Test
    public void calculateDistance(){

        double latitude1 = 37.28323, longitude1 = 45.321212, latitude2 = 52.34212, longitud2 = 12.12712;
        double expectedDistance =3067125.6340807835; //in meters

        double distance= EnergyConsumeViewModel.calculateDistance(latitude1,longitude1,latitude2,longitud2);
        assertEquals(expectedDistance,distance,precision);
    }

    @Test
    public void kolkataToMumbaiDistance(){

        double latitude1 = 22.5726, longitude1 = 88.3639, latitude2 = 19.0760, longitud2 = 72.8777;
        double expectedDistance =1654839.116799172; //in meters

        double distance= EnergyConsumeViewModel.calculateDistance(latitude1,longitude1,latitude2,longitud2);
        assertEquals(expectedDistance,distance,precision);
    }

    @Test
    public void delhiToBangaloreDistance(){

        double latitude1 = 28.7041, longitude1 = 77.1025, latitude2 = 12.9716, longitud2 = 77.5946;
        double expectedDistance =1750114.76241321; //in meters

        double distance= EnergyConsumeViewModel.calculateDistance(latitude1,longitude1,latitude2,longitud2);
        assertEquals(expectedDistance,distance,precision);
    }

    @Test
    public void delhiToKolkataDistance(){

        double latitude1 = 28.7041, longitude1 = 77.1025, latitude2 = 22.5726, longitud2 = 88.3639;
        double expectedDistance =1317753.60303967245; //in meters

        double distance= EnergyConsumeViewModel.calculateDistance(latitude1,longitude1,latitude2,longitud2);
        assertEquals(expectedDistance,distance,precision);
    }
    @Test
    public void delhiToMumbaiDistance(){

        double latitude1 = 28.7041, longitude1 = 77.1025, latitude2 = 19.0760, longitud2 = 72.8777;
        double expectedDistance =1153241.2912502035; //in meters

        double distance= EnergyConsumeViewModel.calculateDistance(latitude1,longitude1,latitude2,longitud2);
        assertEquals(expectedDistance,distance,precision);
    }


}
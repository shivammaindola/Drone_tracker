package com.microdrones.technical_test.viewModel;
import static org.junit.Assert.*;

import android.util.Log;

import com.google.gson.Gson;
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
    public void isMissionSuccessful(){

        String config= "{\"verticalSpeeds\":{\"ascension\":5.0,\"descent\":1.0},\"energy\":{\"numberOfBatteries\":2,\"capacity\":2000},\"payload\":{\"additionalLoad\":0.1}}";
        String drone="{\"currentLoadInFlight\":{\"ascension\":0.020,\"descent\":0.010,\"translation\":0.015},\"forcedLandingCharge\":200}";
        String mission="{\"name\":\"mission-1\",\"horizontalSpeed\":6.0,\"altitude\":50,\"points\":[{\"latitude\":45.5367421983067,\"longitude\":-73.6282321314434},{\"latitude\":45.534616428413734,\"longitude\":-73.6282321314434},{\"latitude\":45.53462980489953,\"longitude\":-73.62849520233081},{\"latitude\":45.536754529851336,\"longitude\":-73.62849520233081},{\"latitude\":45.536732607277386,\"longitude\":-73.62875827321822},{\"latitude\":45.534643181385334,\"longitude\":-73.62875827321822},{\"latitude\":45.534656735625646,\"longitude\":-73.62902134410562},{\"latitude\":45.536710684703436,\"longitude\":-73.62902134410562},{\"latitude\":45.53668876212949,\"longitude\":-73.62928441499304},{\"latitude\":45.53474170262034,\"longitude\":-73.62928441499304},{\"latitude\":45.53482666961503,\"longitude\":-73.62954748588045},{\"latitude\":45.53666663118064,\"longitude\":-73.62954748588045},{\"latitude\":45.53648732141936,\"longitude\":-73.62981055676786},{\"latitude\":45.53491163660972,\"longitude\":-73.62981055676786},{\"latitude\":45.53499660360441,\"longitude\":-73.63007362765528},{\"latitude\":45.53613746425981,\"longitude\":-73.63007362765528},{\"latitude\":45.53578760710022,\"longitude\":-73.63033669854272},{\"latitude\":45.535193994790106,\"longitude\":-73.63033669854272},{\"latitude\":45.534644926763626,\"longitude\":-73.627969060556},{\"latitude\":45.536671513372,\"longitude\":-73.627969060556},{\"latitude\":45.5366008284373,\"longitude\":-73.62770598966858},{\"latitude\":45.53470432986724,\"longitude\":-73.62770598966858},{\"latitude\":45.53476373297084,\"longitude\":-73.6274429187812},{\"latitude\":45.5365301435026,\"longitude\":-73.6274429187812},{\"latitude\":45.53645945856791,\"longitude\":-73.62717984789381},{\"latitude\":45.53482313607444,\"longitude\":-73.62717984789381},{\"latitude\":45.53495188355321,\"longitude\":-73.62691677700641},{\"latitude\":45.53638877363321,\"longitude\":-73.62691677700641},{\"latitude\":45.536254425516695,\"longitude\":-73.62665370611901},{\"latitude\":45.53508210364247,\"longitude\":-73.62665370611901},{\"latitude\":45.535294415080614,\"longitude\":-73.62639063523162},{\"latitude\":45.535917510169675,\"longitude\":-73.62639063523162}]}";

        Config configModel = new Gson().fromJson(config, Config.class);
        Drone dronesModel = new Gson().fromJson(drone, Drone.class);
        Mission missionsModel = new Gson().fromJson(mission, Mission.class);

        assertEquals(true,EnergyConsumeViewModel.getFlight(configModel,dronesModel,missionsModel));
    }

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
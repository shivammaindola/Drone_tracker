package com.microdrones.technical_test;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.microdrones.technical_test.adapter.CardAdapter;
import com.microdrones.technical_test.model.CardModel;
import com.microdrones.technical_test.model.configurations.Config;
import com.microdrones.technical_test.model.drones.Drone;
import com.microdrones.technical_test.model.missions.Mission;
import com.microdrones.technical_test.viewModel.EnergyConsumeViewModel;
import com.microdrones.test.example.view.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private Context context;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    private RecyclerView missionRV;

    // Arraylist for storing data
    private ArrayList<CardModel> CardModelArrayList;
    EnergyConsumeViewModel energyConsumeViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mirrar, container, false);
        energyConsumeViewModel= new ViewModelProvider(this).get(EnergyConsumeViewModel.class);
        missionRV = view.findViewById(R.id.missionsRecycler);

        Toast.makeText(context, "Tap on the card to see the drone path", Toast.LENGTH_LONG).show();


        // here we have created new array list and added data to it.
        CardModelArrayList = new ArrayList<>();
        for(int i=1;i<=6;i++)
        runMissions(i);

        CardAdapter courseAdapter = new CardAdapter(context, CardModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false);

        missionRV.setLayoutManager(linearLayoutManager);
        missionRV.setAdapter(courseAdapter);
        return view;

    }
    private void runMissions(int i) {
        String s=String.valueOf(i);

        String config= null;
        String drones= null;
        String missions= null;

        try {
            config = inputStreamToString(context.getAssets().open("data/configurations/config-"+s+".json"));
            drones = inputStreamToString(context.getAssets().open("data/drones/drone-"+s+".json"));
            missions = inputStreamToString(context.getAssets().open("data/missions/mission-"+s+".json"));

        } catch (IOException e) {
            Log.d("checkModel", "onCreate: Error"+e.toString());
            e.printStackTrace();
        }

        Config configModel = new Gson().fromJson(config, Config.class);
        Drone dronesModel = new Gson().fromJson(drones, Drone.class);
        Mission missionsModel = new Gson().fromJson(missions, Mission.class);

        boolean droneFlown= energyConsumeViewModel.getFlight(configModel,dronesModel,missionsModel);
        String result="";

        if(droneFlown)
            result="Successful";
        else
            result="Fail";

        int size=missionsModel.points.size();

        double capacity=configModel.energy.capacity*configModel.energy.numberOfBatteries;

        CardModelArrayList.add(
                new CardModel(
                        missionsModel,
                        result,
                        energyConsumeViewModel.getTotalEnergy(),
                        capacity
                ));

        //print result
        System.out.println("checkMissions"+missionsModel.name+": ");
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }


}


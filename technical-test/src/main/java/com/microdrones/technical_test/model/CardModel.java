package com.microdrones.technical_test.model;

import com.microdrones.technical_test.model.missions.Mission;

public class CardModel {

    private Mission mission;
    private String missionSucess;
    private double progress;
    private double capacity;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public CardModel(Mission mission, String missionSucess, double progress, double capacity) {
        this.mission = mission;
        this.missionSucess = missionSucess;
        this.progress = progress;
        this.capacity = capacity;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMissionTitle(Mission mission) {
        this.mission = mission;
    }

    public String getMissionSucess() {
        return missionSucess;
    }

    public void setMissionSucess(String missionSucess) {
        this.missionSucess = missionSucess;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}

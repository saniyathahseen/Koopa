package com.android.koopa;

public class State {
    String stateName;
    int stateId;

    public State(String state, State choose_a_country, int id) {
        this.stateName = state;
        this.stateId = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStateId() {
        return stateId;
    }

    public int setStateId(int stateId) {
        this.stateId = stateId;
        return stateId;
    }
}

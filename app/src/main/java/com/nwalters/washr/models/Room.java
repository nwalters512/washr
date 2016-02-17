package com.nwalters.washr.models;

import java.util.List;

public class Room {
    int id;
    String name;
    List<Machine> machines;

    @Override
    public String toString() {
        String string = "";
        string += name + "\n" + "[" + "\n";
        for(Machine machine : machines) {
            string += machine.toString();
            string += "\n";
        }
        string += "]";
        return string;
    }
}

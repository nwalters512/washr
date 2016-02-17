package com.nwalters.washr.models;

import java.util.List;

public class Location {
    String name;
    String code;
    boolean networked;
    String company;
    List<Room> rooms;

    @Override
    public String toString() {
        String string = "";
        string += name + "\n" + "[" + "\n";
        for(Room room : rooms) {
            string += room.toString();
            string += "\n";
        }
        string += "]";
        return string;
    }
}

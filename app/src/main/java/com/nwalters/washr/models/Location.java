package com.nwalters.washr.models;

import java.util.List;

public class Location {
    public String name;
    String code;
    boolean networked;
    String company;
    public List<Room> rooms;

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

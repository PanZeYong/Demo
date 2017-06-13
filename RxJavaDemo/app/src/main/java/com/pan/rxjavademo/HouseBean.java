package com.pan.rxjavademo;

/**
 * Author : Pan
 * Date : 12/14/16
 */

public class HouseBean {
    private int id;
    private String name;
    private String area;
    private int floor;
    private String communityName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    @Override
    public String toString() {
        return "[" +
                "id : " + id + " " +
                "name : " + name + " " +
                "area : " + area + " " +
                "floor : " + floor +
                "]";
    }
}

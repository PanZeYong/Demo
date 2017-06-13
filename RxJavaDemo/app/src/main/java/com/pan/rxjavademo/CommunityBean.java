package com.pan.rxjavademo;

import java.util.List;

/**
 * Author : Pan
 * Date : 12/14/16
 */

public class CommunityBean {
    private int id;
    private String name;
    private int floors;
    private List<HouseBean> houseBeanList;

    private String text = null;

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

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public List<HouseBean> getHouseBeanList() {
        return houseBeanList;
    }

    public void setHouseBeanList(List<HouseBean> houseBeanList) {
        this.houseBeanList = houseBeanList;
    }

    private void getText() {
        for (HouseBean bean : houseBeanList) {
            text += bean.toString() + "\n";
        }
    }

    @Override
    public String toString() {
        return "[" +
                "id : " + id + " " +
                "name : " + name + " " +
                "floors : " + floors +
                "]";
    }
}

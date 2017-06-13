package com.pan.rxjavademo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Pan
 * Date : 12/14/16
 */

public class Data {
    private static List<HouseBean> mHouseBeanListOne = new ArrayList<>();
    private static List<HouseBean> mHouseBeanListTwo = new ArrayList<>();

    private static List<CommunityBean> mCommunityBeanList = new ArrayList<>();

    public static List<CommunityBean> getData() {
        HouseBean one = new HouseBean();
        one.setId(1);
        one.setName("House One");
        one.setArea("40平方米");
        one.setFloor(3);
        one.setCommunityName("Community One");

        HouseBean two = new HouseBean();
        two.setId(2);
        two.setName("House Two");
        two.setArea("50平方米");
        two.setFloor(2);
        two.setCommunityName("Community Two");

        HouseBean three = new HouseBean();
        three.setId(3);
        three.setName("House Three");
        three.setArea("60平方米");
        three.setFloor(4);
        three.setCommunityName("Community Two");

        HouseBean four = new HouseBean();
        four.setId(4);
        four.setName("House Four");
        four.setArea("70平方米");
        four.setFloor(5);
        four.setCommunityName("Community One");

        HouseBean five = new HouseBean();
        five.setId(5);
        five.setName("House Five");
        five.setArea("80平方米");
        five.setFloor(6);
        five.setCommunityName("Community One");

        mHouseBeanListOne.add(one);
        mHouseBeanListOne.add(two);
        mHouseBeanListOne.add(three);
        mHouseBeanListOne.add(four);
        mHouseBeanListOne.add(five);

        CommunityBean communityOne = new CommunityBean();
        communityOne.setName("Community One");
        communityOne.setId(1);
        communityOne.setFloors(6);
        communityOne.setHouseBeanList(mHouseBeanListOne);
        mCommunityBeanList.add(communityOne);

        HouseBean six = new HouseBean();
        six.setId(6);
        six.setName("House Six");
        six.setArea("50平方米");
        six.setFloor(3);

        HouseBean seven = new HouseBean();
        seven.setId(7);
        seven.setName("House Seven");
        seven.setArea("40平方米");
        seven.setFloor(2);

        HouseBean eight = new HouseBean();
        eight.setId(8);
        eight.setName("House Eight");
        eight.setArea("60平方米");
        eight.setFloor(4);

        HouseBean nine = new HouseBean();
        nine.setId(9);
        nine.setName("House Nine");
        nine.setArea("70平方米");
        nine.setFloor(5);

        HouseBean ten = new HouseBean();
        ten.setId(10);
        ten.setName("House Ten");
        ten.setArea("80平方米");
        ten.setFloor(6);

        HouseBean eleven = new HouseBean();
        eleven.setId(11);
        eleven.setName("House Eleven");
        eleven.setArea("50平方米");
        eleven.setFloor(6);

        mHouseBeanListTwo.add(six);
        mHouseBeanListTwo.add(seven);
        mHouseBeanListTwo.add(eight);
        mHouseBeanListTwo.add(nine);
        mHouseBeanListTwo.add(ten);
        mHouseBeanListTwo.add(eleven);

        CommunityBean communityTwo = new CommunityBean();
        communityTwo.setName("Community Two");
        communityTwo.setId(2);
        communityTwo.setFloors(9);
        communityTwo.setHouseBeanList(mHouseBeanListTwo);
        mCommunityBeanList.add(communityTwo);

        return mCommunityBeanList;
    }

    public static List<HouseBean> getHouseList() {
        return mHouseBeanListOne;
    }
}

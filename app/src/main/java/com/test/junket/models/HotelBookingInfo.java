package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelBookingInfo {
    @SerializedName("dest_id")
    @Expose
    String destId;

    @SerializedName("dateFrom")
    @Expose
    long dateFrom;

    @SerializedName("dateTo")
    @Expose
    long dateTo;

    @SerializedName("days")
    @Expose
    int days;

    @SerializedName("beds")
    @Expose
    int beds;

    @SerializedName("adults")
    @Expose
    int adults;

    @SerializedName("children")
    @Expose
    int children = 0;

    @SerializedName("city")
    @Expose
    String city;

    @SerializedName("hotel_info")
    @Expose
    HotelResultVo hotelInfo;

    @SerializedName("room_info")
    @Expose
    HotelRoomVo roomInfo;


    public HotelBookingInfo(String destId, long dateFrom, long dateTo, int beds, int adults, int children, String city) {
        this.destId = destId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.beds = beds;
        this.adults = adults;
        this.children = children;
        this.city = city;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public long getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateTo) {
        this.dateTo = dateTo;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public HotelResultVo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelResultVo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public HotelRoomVo getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(HotelRoomVo roomInfo) {
        this.roomInfo = roomInfo;
    }

    public int getDays() {
        long diff = dateTo - dateFrom;
        int numOfDays = (int) (diff /  (1000 * 60 * 60 * 24));
        return numOfDays;
    }
}

package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HotelBookingInfo {
    @SerializedName("dest_id")
    @Expose
    String destId;

    @SerializedName("dateFrom")
    @Expose
    Date dateFrom;

    @SerializedName("dateTo")
    @Expose
    Date dateTo;

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


    public HotelBookingInfo(String destId, Date dateFrom, Date dateTo, int beds, int adults, int children, String city) {
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public Integer getAdults() {
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
        long diff = dateTo.getTime() - dateFrom.getTime();
        int numOfDays = (int) (diff /  (1000 * 60 * 60 * 24));
        return numOfDays;
    }
}

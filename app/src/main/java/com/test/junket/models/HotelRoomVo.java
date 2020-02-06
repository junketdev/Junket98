package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelRoomVo {

    @SerializedName("hotel_id")
    @Expose
    private String hotelId;

    @SerializedName("room_id")
    @Expose
    private String roomId;

    @SerializedName("room_type")
    @Expose
    private String roomType;

    @SerializedName("room_price")
    @Expose
    private String roomPrice;

    @SerializedName("room_images")
    @Expose
    private String roomImages;

    @SerializedName("room_description")
    @Expose
    private String roomDescription;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(String roomImages) {
        this.roomImages = roomImages;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

}
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

   /* @SerializedName("room_images")
    @Expose
    private String roomImages;*/

    @SerializedName("img1")
    @Expose
    private String img1;

    @SerializedName("img2")
    @Expose
    private String img2;

    @SerializedName("img3")
    @Expose
    private String img3;

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

    /*public String getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(String roomImages) {
        this.roomImages = roomImages;
    }
*/
    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }
}
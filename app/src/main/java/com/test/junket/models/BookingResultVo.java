package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingResultVo {
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;
    @SerializedName("room_id")
    @Expose
    private String roomId;

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("contact_no")
    @Expose
    private String contactNo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("room_price")
    @Expose
    private String roomPrice;
    @SerializedName("addcharge_price")
    @Expose
    private String addchargePrice;
    @SerializedName("payamount_price")
    @Expose
    private String payamountPrice;
    @SerializedName("checkin_date")
    @Expose
    private String checkinDate;
    @SerializedName("checkout_date")
    @Expose
    private String checkoutDate;
    @SerializedName("adults")
    @Expose
    private String adults;
    @SerializedName("kids")
    @Expose
    private String kids;
    @SerializedName("totalnight")
    @Expose
    private String totalnight;

    @SerializedName("totalrooms")
    @Expose
    private String totalrooms;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getAddchargePrice() {
        return addchargePrice;
    }

    public void setAddchargePrice(String addchargePrice) {
        this.addchargePrice = addchargePrice;
    }

    public String getPayamountPrice() {
        return payamountPrice;
    }

    public void setPayamountPrice(String payamountPrice) {
        this.payamountPrice = payamountPrice;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getKids() {
        return kids;
    }

    public void setKids(String kids) {
        this.kids = kids;
    }

    public String getTotalnight() {
        return totalnight;
    }

    public void setTotalnight(String totalnight) {
        this.totalnight = totalnight;
    }

    public String getTotalrooms() {
        return totalrooms;
    }

    public void setTotalrooms(String totalrooms) {
        this.totalrooms = totalrooms;
    }

}

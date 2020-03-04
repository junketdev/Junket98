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
    @SerializedName("user_name")
    @Expose
    private String userName;
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
    @SerializedName("hotelier_name")
    @Expose
    private String hotelierName;
    @SerializedName("hotelier_password")
    @Expose
    private String hotelierPassword;
    @SerializedName("hotelier_type")
    @Expose
    private String hotelierType;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("hotelier_address")
    @Expose
    private String hotelierAddress;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("hotelier_email")
    @Expose
    private String hotelierEmail;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("amenities")
    @Expose
    private String amenities;
    @SerializedName("offers")
    @Expose
    private Object offers;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("min_price")
    @Expose
    private String minPrice;
    @SerializedName("max_price")
    @Expose
    private String maxPrice;
    @SerializedName("hotelier_tags")
    @Expose
    private String hotelierTags;
    @SerializedName("room_type")
    @Expose
    private String roomType;
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
    @SerializedName("srno")
    @Expose
    private String srno;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getHotelierName() {
        return hotelierName;
    }

    public void setHotelierName(String hotelierName) {
        this.hotelierName = hotelierName;
    }

    public String getHotelierPassword() {
        return hotelierPassword;
    }

    public void setHotelierPassword(String hotelierPassword) {
        this.hotelierPassword = hotelierPassword;
    }

    public String getHotelierType() {
        return hotelierType;
    }

    public void setHotelierType(String hotelierType) {
        this.hotelierType = hotelierType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelierAddress() {
        return hotelierAddress;
    }

    public void setHotelierAddress(String hotelierAddress) {
        this.hotelierAddress = hotelierAddress;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHotelierEmail() {
        return hotelierEmail;
    }

    public void setHotelierEmail(String hotelierEmail) {
        this.hotelierEmail = hotelierEmail;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public Object getOffers() {
        return offers;
    }

    public void setOffers(Object offers) {
        this.offers = offers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getHotelierTags() {
        return hotelierTags;
    }

    public void setHotelierTags(String hotelierTags) {
        this.hotelierTags = hotelierTags;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

}
package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelResultVo {
    @SerializedName("hotelier_id")
    @Expose
    private String hotelierId;
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
    private String offers;
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

    @SerializedName("rooms")
    @Expose
    private List<HotelRoomVo> rooms = null;

    public String getHotelierId() {
        return hotelierId;
    }

    public void setHotelierId(String hotelierId) {
        this.hotelierId = hotelierId;
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

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
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

    public List<HotelRoomVo> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoomVo> rooms) {
        this.rooms = rooms;
    }


}

package com.example.vidal.dipl.entities;

public class ResultFindObject {

    private Double tourRating;
    private Integer tourID;
    private String tourDuration;
    private String tourImages;
    private String tourDate;
    private String tourPrice;
    private String tourCity;
    private String tourHot;
    private String tourResort;


    public ResultFindObject(Integer tourID, String tourDuration, Double tourRating, String tourImages, String tourDate, String tourPrice, String tourCity, String tourHot, String tourResort) {
        this.tourID = tourID;
        this.tourDuration = tourDuration;
        this.tourRating = tourRating;
        this.tourImages = tourImages;
        this.tourDate = tourDate;
        this.tourPrice = tourPrice;
        this.tourCity = tourCity;
        this.tourHot = tourHot;
        this.tourResort = tourResort;

    }

    public Double getTourRating() {
        return tourRating;
    }
    public String getTourDuration() {
        return tourDuration;
    }
    public String getTourResort() {
        return tourResort;
    }
    public String getTourImages() {
        return tourImages;
    }
    public String getTourDate() {
        return tourDate;
    }
    public String getTourPrice() {
        return tourPrice;
    }
    public String getTourCity() {
        return tourCity;
    }
    public String getTourHot() {
        return tourHot;
    }
    public int getTourID() {
        return tourID;
    }
}

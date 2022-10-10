package com.model;

public class AddticketsModel {

    double FlightNo,DepartureDate,ArrivalDate;
    String DepartureAirport,ArrivalAirport;
    int UploadTicket,UserID;

    public AddticketsModel(int UserID,double flightNo, double departureDate, double arrivalDate, String departureAirport, String arrivalAirport, int uploadTicket) {
        FlightNo = flightNo;
        DepartureDate = departureDate;
        ArrivalDate = arrivalDate;
        DepartureAirport = departureAirport;
        ArrivalAirport = arrivalAirport;
        UploadTicket = uploadTicket;
        UserID = UserID;
    }

    public double getFlightNo() {
        return FlightNo;
    }

    public void setFlightNo(double flightNo) {
        FlightNo = flightNo;
    }

    public double getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(double departureDate) {
        DepartureDate = departureDate;
    }

    public double getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(double arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getDepartureAirport() {
        return DepartureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        DepartureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return ArrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        ArrivalAirport = arrivalAirport;
    }

    public int getUploadTicket() {
        return UploadTicket;
    }

    public void setUploadTicket(int uploadTicket) {
        UploadTicket = uploadTicket;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        UserID = UserID;
    }
}

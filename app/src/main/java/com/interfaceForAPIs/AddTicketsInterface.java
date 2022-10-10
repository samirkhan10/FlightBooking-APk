package com.interfaceForAPIs;

import com.model.AddticketsModel;
import com.model.registerModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddTicketsInterface {

    @FormUrlEncoded
    @POST("SaveFlightDetail")
    Call<AddticketsModel> addTickets(@Field("UserID") int UserID, @Field("FlightNo")String FlightNo, @Field("DepartureAirport")String DepartureAirport, @Field("DepartureDate")String DepartureDate, @Field("ArrivalAirport")String ArrivalAirport, @Field("ArrivalDate")String ArrivalDate, @Field("UploadTicket")String UploadTicket);


}

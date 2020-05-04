package com.ankita.complaintdemo.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("api/GetComplaints?")
    Call<CreateResponse> doGetListResour(@Query("customerID") int customerID,
                                         @Query("userType") int userType,
                                         @Query("pageNo") int pageNo,
                                         @Query("pageSize") int pageSize,
                                         @Query("sortColumn") String sortColumn,
                                         @Query("sortOrder") String  sortOrder);
}

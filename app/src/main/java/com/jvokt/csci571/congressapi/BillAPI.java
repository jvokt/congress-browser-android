package com.jvokt.csci571.congressapi;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jvokt on 11/27/16.
 */
public interface BillAPI {
    @GET("/api?operation=bills50")
    public void getBills(Callback<BillResults> response);
}

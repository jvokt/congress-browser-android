package com.jvokt.csci571.congressapi;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jvokt on 11/23/16.
 */
public interface LegislatorAPI {
    @GET("/api?operation=legislatorsAll")
    public void getLegislators(Callback<LegislatorResults> response);
}

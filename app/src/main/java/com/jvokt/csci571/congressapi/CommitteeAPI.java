package com.jvokt.csci571.congressapi;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jvokt on 11/27/16.
 */
public interface CommitteeAPI {
    @GET("/api?operation=committeesAll")
    public void getCommittees(Callback<CommitteeResults> response);
}

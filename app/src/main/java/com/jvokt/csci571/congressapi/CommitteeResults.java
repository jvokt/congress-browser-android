package com.jvokt.csci571.congressapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvokt on 11/27/16.
 */
public class CommitteeResults {
    private List<Committee> results = new ArrayList<>();
    private int count;
    private Page page;
    public List<Committee> getResults() {
        return results;
    }
}

package com.jvokt.csci571.congressapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvokt on 11/23/16.
 */
public class LegislatorResults {
    private List<Legislator> results = new ArrayList<>();
    private int count;
    private Page page;
    /**
     *
     * @return
     *     The results
     */
    public List<Legislator> getResults() {
        return results;
    }
}

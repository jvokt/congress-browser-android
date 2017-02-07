package com.jvokt.csci571.congressapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jvokt on 11/23/16.
 */
public class LegislatorDataProvider {
    public static List<Legislator> stateLegislatorList;
    public static List<String> stateAlphabet;
    public static HashMap<String,Integer> stateSections;
    public static List<Legislator> houseLegislatorList;
    public static List<String> houseAlphabet;
    public static HashMap<String,Integer> houseSections;
    public static List<Legislator> senateLegislatorList;
    public static List<String> senateAlphabet;
    public static HashMap<String,Integer> senateSections;
    static {
        stateLegislatorList = new ArrayList<>();
        stateAlphabet = new ArrayList<>();
        stateSections = new HashMap<>();
        houseLegislatorList = new ArrayList<>();
        houseAlphabet = new ArrayList<>();
        houseSections = new HashMap<>();
        senateLegislatorList = new ArrayList<>();
        senateAlphabet = new ArrayList<>();
        senateSections = new HashMap<>();
        // HTTP get request to retrieve LegislatorResults
        // Extract List<Legislator>
        // Sort and filter into different lists for State, House, and Senate tabs
    }
}

package com.jvokt.csci571.congressapi;

/**
 * Created by jvokt on 11/27/16.
 */
public class Committee {
    public String chamber;
    public String committee_id;
    public String name;
    public String parent_committee_id;
    public String phone;
    public String office;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Committee committee = (Committee) o;

        return committee_id.equals(committee.committee_id);

    }

    @Override
    public int hashCode() {
        return committee_id.hashCode();
    }
}

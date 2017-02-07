package com.jvokt.csci571.congressapi;

/**
 * Created by jvokt on 11/27/16.
 */
public class Bill {
    public String bill_id;
    public String bill_type;
    public String chamber;
    public History history;
    public String introduced_on;
    public LastVersion last_version;
    public String official_title;
    public Sponsor sponsor;
    public Urls_ urls;
    public String short_title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        return bill_id.equals(bill.bill_id);

    }

    @Override
    public int hashCode() {
        return bill_id.hashCode();
    }
}

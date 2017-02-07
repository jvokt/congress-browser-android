package com.jvokt.csci571.congressapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jvokt on 11/23/16.
 */

public class Legislator implements Parcelable {

    private String bioguide_id;
    private String title;
    private String first_name;
    private String last_name;
    private String oc_email;
    private String chamber;
    private String phone;
    private String party;
    private String term_start;
    private String term_end;
    private String office;
    private String state_name;
    private String fax;
    private String birthday;
    private String contact_form;
    private String crpId;
    private int district;
    private List<String> fecIds = new ArrayList<String>();
    private String gender;
    private String govtrackId;
    private boolean inOffice;
    private String leadershipRole;
    private String middle_name;
    private String name_suffix;
    private String nickname;
    private String ocdId;
    private String state;
    private String thomasId;
    private int votesmartId;
    private String website;

    private String facebook_id;
    private String twitter_id;

    private Map<String, String> additionalProperties = new HashMap<String, String>();

    /**
     *
     * @return
     *     The bioguide_id
     */
    public String getBioguideId() {
        return bioguide_id;
    }

    /**
     *
     * @param bioguideId
     *     The bioguide_id
     */
    public void setBioguideId(String bioguideId) {
        this.bioguide_id = bioguideId;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The first_name
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     *
     * @param firstName
     *     The first_name
     */
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    /**
     *
     * @return
     *     The last_name
     */
    public String getLastName() {
        return last_name;
    }

    /**
     *
     * @param lastName
     *     The last_name
     */
    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    /**
     *
     * @return
     *     The oc_email
     */
    public String getOcEmail() {
        return oc_email;
    }

    /**
     *
     * @param ocEmail
     *     The oc_email
     */
    public void setOcEmail(String ocEmail) {
        this.oc_email = ocEmail;
    }

    /**
     *
     * @return
     *     The chamber
     */
    public String getChamber() {
        return chamber;
    }

    /**
     *
     * @param chamber
     *     The chamber
     */
    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    /**
     *
     * @return
     *     The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     *     The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     *     The party
     */
    public String getParty() {
        return party;
    }

    /**
     *
     * @param party
     *     The party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     *
     * @return
     *     The term_start
     */
    public String getTermStart() {
        return term_start;
    }

    /**
     *
     * @param termStart
     *     The term_start
     */
    public void setTermStart(String termStart) {
        this.term_start = termStart;
    }

    /**
     *
     * @return
     *     The term_end
     */
    public String getTermEnd() {
        return term_end;
    }

    /**
     *
     * @param termEnd
     *     The term_end
     */
    public void setTermEnd(String termEnd) {
        this.term_end = termEnd;
    }

    /**
     *
     * @return
     *     The office
     */
    public String getOffice() {
        return office;
    }

    /**
     *
     * @param office
     *     The office
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     *
     * @return
     *     The state_name
     */
    public String getStateName() {
        return state_name;
    }

    /**
     *
     * @param stateName
     *     The state_name
     */
    public void setStateName(String stateName) {
        this.state_name = stateName;
    }

    /**
     *
     * @return
     *     The fax
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     *     The fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     *
     * @return
     *     The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     *     The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     *     The contact_form
     */
    public String getContactForm() {
        return contact_form;
    }

    /**
     *
     * @param contactForm
     *     The contact_form
     */
    public void setContactForm(String contactForm) {
        this.contact_form = contactForm;
    }

    /**
     *
     * @return
     *     The crpId
     */
    public String getCrpId() {
        return crpId;
    }

    /**
     *
     * @param crpId
     *     The crp_id
     */
    public void setCrpId(String crpId) {
        this.crpId = crpId;
    }

    /**
     *
     * @return
     *     The district
     */
    public int getDistrict() {
        return district;
    }

    /**
     *
     * @param district
     *     The district
     */
    public void setDistrict(int district) {
        this.district = district;
    }

    /**
     *
     * @return
     *     The fecIds
     */
    public List<String> getFecIds() {
        return fecIds;
    }

    /**
     *
     * @param fecIds
     *     The fec_ids
     */
    public void setFecIds(List<String> fecIds) {
        this.fecIds = fecIds;
    }

    /**
     *
     * @return
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     *     The govtrackId
     */
    public String getGovtrackId() {
        return govtrackId;
    }

    /**
     *
     * @param govtrackId
     *     The govtrack_id
     */
    public void setGovtrackId(String govtrackId) {
        this.govtrackId = govtrackId;
    }

    /**
     *
     * @return
     *     The inOffice
     */
    public boolean isInOffice() {
        return inOffice;
    }

    /**
     *
     * @param inOffice
     *     The in_office
     */
    public void setInOffice(boolean inOffice) {
        this.inOffice = inOffice;
    }

    /**
     *
     * @return
     *     The leadershipRole
     */
    public String getLeadershipRole() {
        return leadershipRole;
    }

    /**
     *
     * @param leadershipRole
     *     The leadership_role
     */
    public void setLeadershipRole(String leadershipRole) {
        this.leadershipRole = leadershipRole;
    }

    /**
     *
     * @return
     *     The middle_name
     */
    public String getMiddleName() {
        return middle_name;
    }

    /**
     *
     * @param middleName
     *     The middle_name
     */
    public void setMiddleName(String middleName) {
        this.middle_name = middleName;
    }

    /**
     *
     * @return
     *     The name_suffix
     */
    public String getNameSuffix() {
        return name_suffix;
    }

    /**
     *
     * @param nameSuffix
     *     The name_suffix
     */
    public void setNameSuffix(String nameSuffix) {
        this.name_suffix = nameSuffix;
    }

    /**
     *
     * @return
     *     The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     *     The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     *     The ocdId
     */
    public String getOcdId() {
        return ocdId;
    }

    /**
     *
     * @param ocdId
     *     The ocd_id
     */
    public void setOcdId(String ocdId) {
        this.ocdId = ocdId;
    }

    /**
     *
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     *     The thomasId
     */
    public String getThomasId() {
        return thomasId;
    }

    /**
     *
     * @param thomasId
     *     The thomas_id
     */
    public void setThomasId(String thomasId) {
        this.thomasId = thomasId;
    }

    /**
     *
     * @return
     *     The votesmartId
     */
    public int getVotesmartId() {
        return votesmartId;
    }

    /**
     *
     * @param votesmartId
     *     The votesmart_id
     */
    public void setVotesmartId(int votesmartId) {
        this.votesmartId = votesmartId;
    }

    /**
     *
     * @return
     *     The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     *
     * @param website
     *     The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bioguide_id);
        dest.writeString(this.title);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.oc_email);
        dest.writeString(this.chamber);
        dest.writeString(this.phone);
        dest.writeString(this.party);
        dest.writeString(this.term_start);
        dest.writeString(this.term_end);
        dest.writeString(this.office);
        dest.writeString(this.state_name);
        dest.writeString(this.fax);
        dest.writeString(this.birthday);
        dest.writeString(this.contact_form);
        dest.writeString(this.crpId);
        dest.writeInt(this.district);
        dest.writeStringList(this.fecIds);
        dest.writeString(this.gender);
        dest.writeString(this.govtrackId);
        dest.writeByte(this.inOffice ? (byte) 1 : (byte) 0);
        dest.writeString(this.leadershipRole);
        dest.writeString(this.middle_name);
        dest.writeString(this.name_suffix);
        dest.writeString(this.nickname);
        dest.writeString(this.ocdId);
        dest.writeString(this.state);
        dest.writeString(this.thomasId);
        dest.writeInt(this.votesmartId);
        dest.writeString(this.website);
        dest.writeString(this.facebook_id);
        dest.writeString(this.twitter_id);
        dest.writeInt(this.additionalProperties.size());
        for (Map.Entry<String, String> entry : this.additionalProperties.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public Legislator() {
    }

    protected Legislator(Parcel in) {
        this.bioguide_id = in.readString();
        this.title = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.oc_email = in.readString();
        this.chamber = in.readString();
        this.phone = in.readString();
        this.party = in.readString();
        this.term_start = in.readString();
        this.term_end = in.readString();
        this.office = in.readString();
        this.state_name = in.readString();
        this.fax = in.readString();
        this.birthday = in.readString();
        this.contact_form = in.readString();
        this.crpId = in.readString();
        this.district = in.readInt();
        this.fecIds = in.createStringArrayList();
        this.gender = in.readString();
        this.govtrackId = in.readString();
        this.inOffice = in.readByte() != 0;
        this.leadershipRole = in.readString();
        this.middle_name = in.readString();
        this.name_suffix = in.readString();
        this.nickname = in.readString();
        this.ocdId = in.readString();
        this.state = in.readString();
        this.thomasId = in.readString();
        this.votesmartId = in.readInt();
        this.website = in.readString();
        this.facebook_id = in.readString();
        this.twitter_id = in.readString();
        int additionalPropertiesSize = in.readInt();
        this.additionalProperties = new HashMap<String, String>(additionalPropertiesSize);
        for (int i = 0; i < additionalPropertiesSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.additionalProperties.put(key, value);
        }
    }

    public static final Creator<Legislator> CREATOR = new Creator<Legislator>() {
        @Override
        public Legislator createFromParcel(Parcel source) {
            return new Legislator(source);
        }

        @Override
        public Legislator[] newArray(int size) {
            return new Legislator[size];
        }
    };

    @Override
    public String toString() {
        return "Legislator{" +
                "bioguide_id='" + bioguide_id + '\'' +
                ", title='" + title + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", oc_email='" + oc_email + '\'' +
                ", chamber='" + chamber + '\'' +
                ", phone='" + phone + '\'' +
                ", party='" + party + '\'' +
                ", term_start='" + term_start + '\'' +
                ", term_end='" + term_end + '\'' +
                ", office='" + office + '\'' +
                ", state_name='" + state_name + '\'' +
                ", fax='" + fax + '\'' +
                ", birthday='" + birthday + '\'' +
                ", contact_form='" + contact_form + '\'' +
                ", crpId='" + crpId + '\'' +
                ", district=" + district +
                ", fecIds=" + fecIds +
                ", gender='" + gender + '\'' +
                ", govtrackId='" + govtrackId + '\'' +
                ", inOffice=" + inOffice +
                ", leadershipRole='" + leadershipRole + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", name_suffix='" + name_suffix + '\'' +
                ", nickname='" + nickname + '\'' +
                ", ocdId='" + ocdId + '\'' +
                ", state='" + state + '\'' +
                ", thomasId='" + thomasId + '\'' +
                ", votesmartId=" + votesmartId +
                ", website='" + website + '\'' +
                ", facebook_id='" + facebook_id + '\'' +
                ", twitter_id='" + twitter_id + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Legislator that = (Legislator) o;

        return bioguide_id.equals(that.bioguide_id);

    }

    @Override
    public int hashCode() {
        return bioguide_id.hashCode();
    }
}

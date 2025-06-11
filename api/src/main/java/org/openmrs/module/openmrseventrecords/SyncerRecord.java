// api/src/main/java/org/openmrs/module/openmrseventrecords/SyncerRecord.java

package org.openmrs.module.openmrseventrecords;

import javax.persistence.*;
import java.util.Date; // For birthdate

@Entity
@Table(name = "syncer") // Map to the 'syncer' database table
public class SyncerRecord {

    @Id
    // Assuming person_id is your primary key and is auto-generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "given_name", nullable = false)
    private String givenName;

    @Column(name = "middle_name") // Can be null
    private String middleName;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(name = "gender") // Assuming M/F/O, etc.
    private String gender;

    @Column(name = "birthdate") // Can be null
    private Date birthdate;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city_village")
    private String cityVillage;

    @Column(name = "state_province")
    private String stateProvince;

    @Column(name = "country")
    private String country;

    @Column(name = "patient_identifier") // This looks like a unique identifier
    private String patientIdentifier;

    @Column(name = "phone_number")
    private String phoneNumber;

    // --- Getters and Setters ---
    // Generate all getters and setters for the above fields.

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCityVillage() {
        return cityVillage;
    }

    public void setCityVillage(String cityVillage) {
        this.cityVillage = cityVillage;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
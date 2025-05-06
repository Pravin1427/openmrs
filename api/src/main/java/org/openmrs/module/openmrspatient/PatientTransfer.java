package org.openmrs.module.openmrspatient;

import org.openmrs.BaseOpenmrsData;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient_transfer")
public class PatientTransfer extends BaseOpenmrsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Integer transferId;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "encounter_id", nullable = false)
    private Integer encounterId;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "transfer_reason")
    private String transferReason;

    @Column(name = "destination_hospital")
    private String destinationHospital;

    @Column(name = "uuid", unique = true, nullable = false, length = 38)
    private String uuid;

    @Override
    public Integer getId() {
        return transferId;
    }

    @Override
    public void setId(Integer id) {
        this.transferId = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public String getDestinationHospital() {
        return destinationHospital;
    }

    public void setDestinationHospital(String destinationHospital) {
        this.destinationHospital = destinationHospital;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

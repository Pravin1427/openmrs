// api/src/main/java/org/openmrs/module/openmrseventrecords/api/impl/SyncerServiceImpl.java

package org.openmrs.module.openmrseventrecords.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.openmrseventrecords.SyncerRecord; // Import the new domain object
import org.openmrs.module.openmrseventrecords.api.SyncerService; // Import new service interface
import org.openmrs.module.openmrseventrecords.db.SyncerDAO; // Import new DAO interface

import java.util.Date;
import java.util.List;

public class SyncerServiceImpl extends BaseOpenmrsService implements SyncerService {

    private SyncerDAO dao; // Changed type to SyncerDAO

    // Use correct setter name as per Spring bean definition
    public void setSyncerDAO(SyncerDAO dao) { // Changed setter name
        this.dao = dao;
    }

    @Override
    public SyncerRecord saveSyncerRecord(SyncerRecord syncerRecord) {
        return dao.saveSyncerRecord(syncerRecord);
    }

    @Override
    public SyncerRecord getSyncerRecordById(Integer id) {
        return dao.getSyncerRecordById(id);
    }

    @Override
    public List<SyncerRecord> getAllSyncerRecords() {
        return dao.getAllSyncerRecords();
    }

    @Override
    public void deleteSyncerRecord(SyncerRecord syncerRecord) {
        dao.deleteSyncerRecord(syncerRecord);
    }

    @Override
    public SyncerRecord getSyncerRecordByPatientIdentifier(String patientIdentifier) {
        return dao.getSyncerRecordByPatientIdentifier(patientIdentifier);
    }

    @Override
    public List<SyncerRecord> getSyncerRecordsByGivenName(String givenName) {
        return dao.getSyncerRecordsByGivenName(givenName);
    }

    @Override
    public List<SyncerRecord> getSyncerRecordsByFamilyName(String familyName) {
        return dao.getSyncerRecordsByFamilyName(familyName);
    }

    @Override
    public List<SyncerRecord> getSyncerRecordsByGender(String gender) {
        return dao.getSyncerRecordsByGender(gender);
    }

    @Override
    public List<SyncerRecord> getSyncerRecordsByBirthdateRange(Date startDate, Date endDate) {
        return dao.getSyncerRecordsByBirthdateRange(startDate, endDate);
    }

    @Override
    public List<SyncerRecord> getSyncerRecordsByCityVillage(String cityVillage) {
        return dao.getSyncerRecordsByCityVillage(cityVillage);
    }

    @Override
    public List<SyncerRecord> getSyncerRecordsByStateProvince(String stateProvince) {
        return dao.getSyncerRecordsByStateProvince(stateProvince);
    }
}
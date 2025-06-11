// api/src/main/java/org/openmrs/module/openmrseventrecords/db/SyncerDAO.java

package org.openmrs.module.openmrseventrecords.db;

import org.openmrs.module.openmrseventrecords.SyncerRecord; // Import new domain object

import java.util.Date;
import java.util.List;

public interface SyncerDAO {

    SyncerRecord saveSyncerRecord(SyncerRecord syncerRecord);

    SyncerRecord getSyncerRecordById(Integer id);

    List<SyncerRecord> getAllSyncerRecords();

    void deleteSyncerRecord(SyncerRecord syncerRecord);

    SyncerRecord getSyncerRecordByPatientIdentifier(String patientIdentifier);

    List<SyncerRecord> getSyncerRecordsByGivenName(String givenName);
    List<SyncerRecord> getSyncerRecordsByFamilyName(String familyName);
    List<SyncerRecord> getSyncerRecordsByGender(String gender);
    List<SyncerRecord> getSyncerRecordsByBirthdateRange(Date startDate, Date endDate);
    List<SyncerRecord> getSyncerRecordsByCityVillage(String cityVillage);
    List<SyncerRecord> getSyncerRecordsByStateProvince(String stateProvince);
}
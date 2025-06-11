// api/src/main/java/org/openmrs/module/openmrseventrecords/api/SyncerService.java

package org.openmrs.module.openmrseventrecords.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.openmrseventrecords.SyncerRecord; // Import the new domain object
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface SyncerService extends OpenmrsService {

    // For the 'syncer' table, you are likely only reading data.
    // Consider if 'save' and 'delete' operations are truly needed for this module.
    // If not, remove them to enforce read-only behavior.
    // For now, let's keep them as a skeleton for completeness, but note the intention.

    SyncerRecord saveSyncerRecord(SyncerRecord syncerRecord);

    SyncerRecord getSyncerRecordById(Integer id); // Renamed from getEventRecordById

    List<SyncerRecord> getAllSyncerRecords(); // Renamed from getAllEventRecords

    void deleteSyncerRecord(SyncerRecord syncerRecord); // Renamed from deleteEventRecord

    SyncerRecord getSyncerRecordByPatientIdentifier(String patientIdentifier); // New method for a common identifier

    // Add more specific query methods relevant to the 'syncer' table
    List<SyncerRecord> getSyncerRecordsByGivenName(String givenName);
    List<SyncerRecord> getSyncerRecordsByFamilyName(String familyName);
    List<SyncerRecord> getSyncerRecordsByGender(String gender);
    List<SyncerRecord> getSyncerRecordsByBirthdateRange(Date startDate, Date endDate);
    List<SyncerRecord> getSyncerRecordsByCityVillage(String cityVillage);
    List<SyncerRecord> getSyncerRecordsByStateProvince(String stateProvince);
}
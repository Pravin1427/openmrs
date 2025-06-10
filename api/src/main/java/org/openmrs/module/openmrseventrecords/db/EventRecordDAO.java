package org.openmrs.module.openmrseventrecords.db;

import org.openmrs.module.openmrseventrecords.EventRecord; // Import new domain object
import java.util.Date;
import java.util.List;

public interface EventRecordDAO {

    EventRecord saveEventRecord(EventRecord eventRecord);

    EventRecord getEventRecordById(Integer id);

    List<EventRecord> getAllEventRecords();

    void deleteEventRecord(EventRecord eventRecord);

    EventRecord getEventRecordByUuid(String uuid);

    List<EventRecord> getEventRecordsByCategory(String category);
    List<EventRecord> getEventRecordsByTitle(String title);
    List<EventRecord> getEventRecordsByDateRange(Date startDate, Date endDate);
}
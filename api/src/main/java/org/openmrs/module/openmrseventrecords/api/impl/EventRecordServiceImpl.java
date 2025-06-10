package org.openmrs.module.openmrseventrecords.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.openmrseventrecords.EventRecord; // Import the new domain object
import org.openmrs.module.openmrseventrecords.api.EventRecordService; // Import new service interface
import org.openmrs.module.openmrseventrecords.db.EventRecordDAO; // Import new DAO interface

import java.util.Date;
import java.util.List;

public class EventRecordServiceImpl extends BaseOpenmrsService implements EventRecordService {

    private EventRecordDAO dao;

    // Use correct setter name as per Spring bean definition
    public void setEventRecordDAO(EventRecordDAO dao) {
        this.dao = dao;
    }

    @Override
    public EventRecord saveEventRecord(EventRecord eventRecord) {
        return dao.saveEventRecord(eventRecord);
    }

    @Override
    public EventRecord getEventRecordById(Integer id) {
        return dao.getEventRecordById(id);
    }

    @Override
    public List<EventRecord> getAllEventRecords() {
        return dao.getAllEventRecords();
    }

    @Override
    public void deleteEventRecord(EventRecord eventRecord) {
        dao.deleteEventRecord(eventRecord);
    }

    @Override
    public EventRecord getEventRecordByUuid(String uuid) {
        return dao.getEventRecordByUuid(uuid);
    }

    @Override
    public List<EventRecord> getEventRecordsByCategory(String category) {
        return dao.getEventRecordsByCategory(category);
    }

    @Override
    public List<EventRecord> getEventRecordsByTitle(String title) {
        return dao.getEventRecordsByTitle(title);
    }

    @Override
    public List<EventRecord> getEventRecordsByDateRange(Date startDate, Date endDate) {
        return dao.getEventRecordsByDateRange(startDate, endDate);
    }
}
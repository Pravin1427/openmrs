package org.openmrs.module.openmrseventrecords.db.hibernate;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.openmrseventrecords.SyncerRecord;
import org.openmrs.module.openmrseventrecords.db.SyncerDAO;

import java.util.Date;
import java.util.List;

public class HibernateSyncerDAOImpl implements SyncerDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SyncerRecord saveSyncerRecord(SyncerRecord syncerRecord) {
        sessionFactory.getCurrentSession().saveOrUpdate(syncerRecord);
        return syncerRecord;
    }

    @Override
    public SyncerRecord getSyncerRecordById(Integer id) {
        return (SyncerRecord) sessionFactory.getCurrentSession().get(SyncerRecord.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SyncerRecord> getAllSyncerRecords() {
        return sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class).list();
    }

    @Override
    public void deleteSyncerRecord(SyncerRecord syncerRecord) {
        sessionFactory.getCurrentSession().delete(syncerRecord);
    }

    @Override
    public SyncerRecord getSyncerRecordByPatientIdentifier(String patientIdentifier) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class);
        // Trim the input parameter for exact match
        criteria.add(Restrictions.eq("patientIdentifier", patientIdentifier != null ? patientIdentifier.trim() : null));
        return (SyncerRecord) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SyncerRecord> getSyncerRecordsByGivenName(String givenName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class);
        // Trim the input parameter
        criteria.add(Restrictions.eq("givenName", givenName != null ? givenName.trim() : null));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SyncerRecord> getSyncerRecordsByFamilyName(String familyName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class);
        // Trim the input parameter
        criteria.add(Restrictions.eq("familyName", familyName != null ? familyName.trim() : null));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SyncerRecord> getSyncerRecordsByGender(String gender) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class);
        // Trim the input parameter
        criteria.add(Restrictions.eq("gender", gender != null ? gender.trim() : null));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SyncerRecord> getSyncerRecordsByBirthdateRange(Date startDate, Date endDate) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class);
        if (startDate != null) {
            criteria.add(Restrictions.ge("birthdate", startDate));
        }
        if (endDate != null) {
            criteria.add(Restrictions.le("birthdate", endDate));
        }
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SyncerRecord> getSyncerRecordsByCityVillage(String cityVillage) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class);
        // Trim the input parameter
        criteria.add(Restrictions.eq("cityVillage", cityVillage != null ? cityVillage.trim() : null));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SyncerRecord> getSyncerRecordsByStateProvince(String stateProvince) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SyncerRecord.class);
        // Trim the input parameter
        criteria.add(Restrictions.eq("stateProvince", stateProvince != null ? stateProvince.trim() : null));
        return criteria.list();
    }
}
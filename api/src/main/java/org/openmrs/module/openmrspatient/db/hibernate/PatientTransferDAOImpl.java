package org.openmrs.module.openmrspatient.db.hibernate;
import org.hibernate.SessionFactory;
import java.util.List;
import org.openmrs.module.openmrspatient.PatientTransfer;
import org.openmrs.module.openmrspatient.db.PatientTransferDAO;

public class PatientTransferDAOImpl implements PatientTransferDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PatientTransfer savePatientTransfer(PatientTransfer transfer) {
        sessionFactory.getCurrentSession().saveOrUpdate(transfer);
        return transfer;
    }

    @Override
    public PatientTransfer getPatientTransferById(Integer id) {
        return sessionFactory.getCurrentSession().get(PatientTransfer.class, id);
    }

    @Override
    public List<PatientTransfer> getAllPatientTransfers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from PatientTransfer", PatientTransfer.class)
                .list();
    }

    @Override
    public void deletePatientTransfer(PatientTransfer transfer) {
        sessionFactory.getCurrentSession().delete(transfer);
    }

    @Override
    public PatientTransfer getPatientTransferByUuid(String uuid) {
        return (PatientTransfer) sessionFactory.getCurrentSession()
                .createQuery("FROM PatientTransfer WHERE uuid = :uuid")
                .setParameter("uuid", uuid)
                .uniqueResult();
    }
}

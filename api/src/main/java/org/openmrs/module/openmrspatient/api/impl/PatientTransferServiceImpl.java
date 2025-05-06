package org.openmrs.module.openmrspatient.api.impl;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.openmrspatient.PatientTransfer;
import org.openmrs.module.openmrspatient.api.PatientTransferService;
import org.openmrs.module.openmrspatient.db.PatientTransferDAO;

import java.util.List;

public class PatientTransferServiceImpl extends BaseOpenmrsService implements PatientTransferService {

    private PatientTransferDAO dao;

    public void setPatientTransferDAO(PatientTransferDAO dao) {
        this.dao = dao;
    }

    @Override
    public PatientTransfer savePatientTransfer(PatientTransfer transfer) {
        return dao.savePatientTransfer(transfer);
    }

    @Override
    public PatientTransfer getPatientTransferById(Integer id) {
        return dao.getPatientTransferById(id);
    }

    @Override
    public List<PatientTransfer> getAllPatientTransfers() {
        return dao.getAllPatientTransfers();
    }

    @Override
    public void deletePatientTransfer(PatientTransfer transfer) {
        dao.deletePatientTransfer(transfer);
    }

    @Override
    public PatientTransfer getPatientTransferByUuid(String uuid) {
        return dao.getPatientTransferByUuid(uuid);
    }

}

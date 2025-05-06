package org.openmrs.module.openmrspatient.db;



import org.openmrs.module.openmrspatient.PatientTransfer;

import java.util.List;

public interface PatientTransferDAO {

    PatientTransfer savePatientTransfer(PatientTransfer transfer);

    PatientTransfer getPatientTransferById(Integer id);

    List<PatientTransfer> getAllPatientTransfers();

    void deletePatientTransfer(PatientTransfer transfer);

    PatientTransfer getPatientTransferByUuid(String uuid);
}
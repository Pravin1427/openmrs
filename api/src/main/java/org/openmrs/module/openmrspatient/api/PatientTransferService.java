package org.openmrs.module.openmrspatient.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.openmrspatient.PatientTransfer;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface PatientTransferService extends OpenmrsService {

    PatientTransfer savePatientTransfer(PatientTransfer transfer);

    PatientTransfer getPatientTransferById(Integer id);

    List<PatientTransfer> getAllPatientTransfers();

    void deletePatientTransfer(PatientTransfer transfer);

    PatientTransfer getPatientTransferByUuid(String uuid);

}

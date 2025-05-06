package org.openmrs.module.openmrspatient.web.resource;

import org.openmrs.module.openmrspatient.PatientTransfer;
import org.openmrs.module.openmrspatient.api.PatientTransferService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;

import java.util.List;
import java.util.UUID;

@Resource(name = RestConstants.VERSION_1 + "/patienttransfer", supportedClass = PatientTransfer.class, supportedOpenmrsVersions = {"2.7.*"})
public class PatientTransferResource extends DelegatingCrudResource<PatientTransfer> {

    @Override
    public PatientTransfer getByUniqueId(String uuid) {
        return getService().getPatientTransferByUuid(uuid);
    }

    @Override
    public PatientTransfer save(PatientTransfer transfer) {
        // Set required fields for new PatientTransfer
        if (transfer.getUuid() == null) {
            transfer.setUuid(UUID.randomUUID().toString());  // Ensure UUID is set if it's a new transfer
            // You can set other fields like 'creator' and 'dateCreated' if needed, similar to how it's done in HelloWorldMessageResource
        }
        return getService().savePatientTransfer(transfer);
    }

    @Override
    public PatientTransfer newDelegate() {
        return new PatientTransfer();
    }

    @Override
    public void purge(PatientTransfer transfer, RequestContext context) {
        getService().deletePatientTransfer(transfer);
    }

    @Override
    public void delete(PatientTransfer transfer, String reason, RequestContext context) {
        if (transfer == null) {
            throw new IllegalArgumentException("No PatientTransfer found with the provided ID");
        }
        // Implement soft delete or hard delete
        getService().deletePatientTransfer(transfer);
    }

    @Override
    public String getUniqueId(PatientTransfer transfer) {
        return transfer.getUuid();
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription description = new DelegatingResourceDescription();

        if (rep instanceof DefaultRepresentation) {
            description.addProperty("uuid");
            description.addProperty("patientId");
            description.addProperty("firstName");
            description.addProperty("lastName");
            description.addProperty("encounterId");
            description.addProperty("location");
            description.addProperty("doctorName");
            description.addProperty("transferReason");
            description.addProperty("destinationHospital");
        } else if (rep instanceof RefRepresentation) {
            description.addProperty("uuid");
            description.addProperty("transferReason");
        }

        description.addSelfLink();
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("transferReason");
        description.addProperty("destinationHospital");
        return description;
    }

    @Override
    public NeedsPaging<PatientTransfer> doGetAll(RequestContext context) {
        return new NeedsPaging<>(getService().getAllPatientTransfers(), context);
    }

    private PatientTransferService getService() {
        return Context.getService(PatientTransferService.class);
    }

    @Override
    public DelegatingResourceDescription getUpdatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addProperty("transferReason");
        description.addProperty("destinationHospital");
        description.addProperty("doctorName");
        return description;
    }
}

// omod/src/main/java/org/openmrs/module/openmrseventrecords/web/resource/SyncerRecordResource.java

package org.openmrs.module.openmrseventrecords.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.openmrseventrecords.SyncerRecord; // Import the new domain object
import org.openmrs.module.openmrseventrecords.api.SyncerService; // Import new service interface
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Renamed resource name and supportedClass
@Resource(name = RestConstants.VERSION_1 + "/syncerrecord", supportedClass = SyncerRecord.class, supportedOpenmrsVersions = {"2.7.*"})
public class SyncerRecordResource extends DelegatingCrudResource<SyncerRecord> {

    protected static final Log log = LogFactory.getLog(SyncerRecordResource.class); // Changed class name for logger

    @Override
    public SyncerRecord getByUniqueId(String uniqueId) { // uniqueId will likely be patientIdentifier
        // For SyncerRecord, the 'unique ID' that makes most sense for retrieval is 'patient_identifier'
        return getService().getSyncerRecordByPatientIdentifier(uniqueId);
    }

    @Override
    public SyncerRecord save(SyncerRecord syncerRecord) {
        // Typically, 'syncer' records are populated by a synchronization process, not created via REST.
        // It is strongly recommended to make this endpoint read-only for this resource.
        // Throwing UnsupportedOperationException prevents accidental writes.
        throw new UnsupportedOperationException("Syncer records are read-only via this API.");
        // return getService().saveSyncerRecord(syncerRecord); // REMOVE THIS LINE
    }

    @Override
    public SyncerRecord newDelegate() {
        return new SyncerRecord();
    }

    @Override
    public void purge(SyncerRecord syncerRecord, RequestContext context) {
        // Purging 'syncer' records is highly discouraged as it impacts synchronization.
        throw new UnsupportedOperationException("Purging syncer records is not allowed via this API.");
        // getService().deleteSyncerRecord(syncerRecord); // REMOVE THIS LINE
    }

    @Override
    public void delete(SyncerRecord syncerRecord, String reason, RequestContext context) {
        // Deletion of 'syncer' records is highly discouraged as it impacts synchronization.
        throw new UnsupportedOperationException("Deleting syncer records is not allowed via this API.");
        // if (syncerRecord == null) {
        //     throw new IllegalArgumentException("No SyncerRecord found with the provided ID");
        // }
        // getService().deleteSyncerRecord(syncerRecord); // REMOVE THIS LINE
    }

    @Override
    public String getUniqueId(SyncerRecord syncerRecord) {
        // The patient_identifier seems like a good candidate for a unique ID for REST purposes
        return syncerRecord.getPatientIdentifier();
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription description = new DelegatingResourceDescription();

        if (rep instanceof DefaultRepresentation) {
            description.addProperty("personId");
            description.addProperty("givenName");
            description.addProperty("middleName");
            description.addProperty("familyName");
            description.addProperty("gender");
            description.addProperty("birthdate");
            description.addProperty("address1");
            description.addProperty("address2");
            description.addProperty("cityVillage");
            description.addProperty("stateProvince");
            description.addProperty("country");
            description.addProperty("patientIdentifier");
            description.addProperty("phoneNumber");
        } else if (rep instanceof RefRepresentation) {
            description.addProperty("patientIdentifier"); // Use patientIdentifier for ref
            description.addProperty("givenName");
            description.addProperty("familyName");
            description.addProperty("gender");
            description.addProperty("birthdate");
        }

        description.addSelfLink();
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        // As discussed, syncer records should generally not be creatable via REST.
        throw new UnsupportedOperationException("Creating syncer records is not allowed via this API.");
        // return new DelegatingResourceDescription(); // Or return an empty one if you absolutely must.
    }

    @Override
    public NeedsPaging<SyncerRecord> doGetAll(RequestContext context) {
        // Implement filtering based on syncer table columns
        String givenName = context.getParameter("givenName");
        String familyName = context.getParameter("familyName");
        String gender = context.getParameter("gender");
        String cityVillage = context.getParameter("cityVillage");
        String stateProvince = context.getParameter("stateProvince");
        String startDateStr = context.getParameter("birthdateStartDate"); // Custom parameter name for clarity
        String endDateStr = context.getParameter("birthdateEndDate");     // Custom parameter name for clarity

        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);

        List<SyncerRecord> records;
        if (givenName != null) {
            records = getService().getSyncerRecordsByGivenName(givenName);
        } else if (familyName != null) {
            records = getService().getSyncerRecordsByFamilyName(familyName);
        } else if (gender != null) {
            records = getService().getSyncerRecordsByGender(gender);
        } else if (cityVillage != null) {
            records = getService().getSyncerRecordsByCityVillage(cityVillage);
        } else if (stateProvince != null) {
            records = getService().getSyncerRecordsByStateProvince(stateProvince);
        } else if (startDate != null || endDate != null) {
            records = getService().getSyncerRecordsByBirthdateRange(startDate, endDate);
        } else {
            records = getService().getAllSyncerRecords();
        }
        return new NeedsPaging<>(records, context);
    }

    /**
     * Helper method to parse date strings from request parameters.
     * Assumes a "yyyy-MM-dd" format for simplicity.
     *
     * @param dateString The date string from the request parameter.
     * @return A Date object, or null if the string is null/empty or cannot be parsed.
     */
    private Date parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            log.warn("Failed to parse date string '" + dateString + "'. Expected format: " + dateFormat.toPattern(), e);
            return null;
        }
    }

    private SyncerService getService() { // Changed return type and method name
        return Context.getService(SyncerService.class); // Changed service class
    }

    @Override
    public DelegatingResourceDescription getUpdatableProperties() {
        // As discussed, syncer records should generally not be updatable via REST.
        throw new UnsupportedOperationException("Updating syncer records is not allowed via this API.");
        // return new DelegatingResourceDescription(); // Or return an empty one
    }
}
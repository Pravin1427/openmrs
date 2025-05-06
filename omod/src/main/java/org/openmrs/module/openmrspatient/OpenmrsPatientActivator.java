package org.openmrs.module.openmrspatient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

public class OpenmrsPatientActivator extends BaseModuleActivator {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public void started() {
        log.info("Started OpenMRS Patient Transfer Module");
    }

    @Override
    public void stopped() {
        log.info("Stopped OpenMRS Patient Transfer Module");
    }
}

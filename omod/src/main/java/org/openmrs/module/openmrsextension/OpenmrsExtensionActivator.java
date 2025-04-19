package org.openmrs.module.openmrsextension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

public class OpenmrsExtensionActivator extends BaseModuleActivator {

    private final Log log = LogFactory.getLog(getClass());

    @Override
    public void started() {
        log.info("Started OpenMRS Extension Module");
    }

    @Override
    public void stopped() {
        log.info("Stopped OpenMRS Extension Module");
    }
}

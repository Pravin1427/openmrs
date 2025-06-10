package org.openmrs.module.openmrseventrecords;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator; // <--- Changed import

/**
 * This class is the global _first_ thing that happens when this module is
 * loaded. Any static variables should be loaded in here, not in the Activator
 * as it is not guaranteed that the activator will be called in a consistent
 * way.
 */
public class OpenmrsEventRecordsActivator extends BaseModuleActivator { // <--- Changed to extends

    protected final Log log = LogFactory.getLog(getClass()); // <--- Updated logger declaration

    /**
     * @see BaseModuleActivator#started()
     */
    @Override // <--- Good practice to keep @Override
    public void started() {
        log.info("Started OpenMRS Event Records Module"); // <--- Updated log message
        // Add any startup logic here, e.g., registering global properties or listeners
    }

    /**
     * @see BaseModuleActivator#stopped()
     */
    @Override // <--- Good practice to keep @Override
    public void stopped() { // <--- Changed method name
        log.info("Stopped OpenMRS Event Records Module"); // <--- Updated log message
        // Add any shutdown logic here
    }
}
package cz.lubos.portlet.commons.exportutils;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import cz.sonet.utils.file.api.FileGeneratorOsgi;
import cz.sonet.utils.file.api.FileGeneratorOsgiImpl;

/**
 * Service class for document export (txt, csv, xls) 
 */
public class ExportServiceTracker extends ServiceTracker<FileGeneratorOsgi, FileGeneratorOsgiImpl> {     

	/**
	 * Constructor
	 * @param host
	 */
    public ExportServiceTracker(Object host) {
        super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), FileGeneratorOsgi.class, null);     
    } 
}

package cz.lubos.portlet.commons.exportutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import cz.lubos.portlet.commons.PortletUtils;
import cz.sonet.utils.file.api.FileType;
import cz.sonet.utils.file.api.FileUtilsException;

/**
 * Export methods for all portlets
 *
 */
@Component
public class ExportUtils {
	
	private static final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";

	private static final Log log = LogFactoryUtil.getLog(ExportUtils.class);
	
	private ExportServiceTracker exportServiceTracker;
	
	@PostConstruct
	public void init() {
		exportServiceTracker = new ExportServiceTracker(this);
	    exportServiceTracker.open();
	}
	
	@PreDestroy
	public void destroy() {
		exportServiceTracker.close();
	}
	
	/**
	 * Create excel report
	 * @param fileNameKey
	 * @param messageSource
	 * @param request
	 * @param response
	 * @param headerList
	 * @param dataList
	 */
	public void sendReport(String fileNameKey, MessageSource messageSource, 
			ResourceRequest request, ResourceResponse response,
			List<String> headerList, List<List<Object>> dataList ) {
	
		if (exportServiceTracker.isEmpty()) {
			return;
		}
		
		String fileName = PortletUtils.createFileNameXlsx(null, messageSource.getMessage(fileNameKey, null, request.getLocale()));
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			exportServiceTracker.getService().createReport(FileType.XLSX, headerList, dataList, outputStream);
			
			try (ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray())) {
				ServletResponseUtil.sendFile(httpRequest, httpResponse, fileName, inputStream, EXCEL_CONTENT_TYPE);
			}
		} catch (FileUtilsException | IOException e) {
			log.error("Cannot send file or message in export response.", e);
		} 
	}
	
}
	

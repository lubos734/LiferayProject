package cz.lubos.portlet.commons;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.Formatter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Date convertor for converting string to date.
 * If string is null, empty or cannot be parset, convertor returns null value.
 */
public class DateFormatter implements Formatter<Date> {

	private static final Log log = LogFactoryUtil.getLog(DateFormatter.class);
	
	public final static String[] DEFAULT_INPUT_FORMATS = {
	        "dd.MM.yyyy hh:mm:ss",
	        "dd.MM.yy hh:mm:ss",
	        "dd.MM.yyyy hh:mm",
	        "dd.MM.yy hh:mm",
	        "dd.MM.yyyy",
	        "dd.MM.yy",
	        "MM/yyyy",
	        "MM/yy",
	        "yyyy/MM/dd hh:mm:ss",
	        "yy/MM/dd hh:mm:ss",
	        "yyyy/MM/dd hh:mm",
	        "yy/MM/dd hh:mm",
	        "yyyy/MM/dd",
	        "yy/MM/dd"
	    };
	
	@Override
	public String print(Date object, Locale locale) {
		return object.toString();
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		if (text == null || text.isEmpty()) {
			return null;
		}
		else {
			try {
				return DateUtils.parseDate(text, DEFAULT_INPUT_FORMATS);
			} catch (ParseException e) {
				log.error(e);
				return null;
			}
		}
	}


}

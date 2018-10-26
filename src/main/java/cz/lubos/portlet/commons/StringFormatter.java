package cz.lubos.portlet.commons;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * Date convertor for converting string to date.
 * If string is null, empty or cannot be parset, convertor returns null value.
 */
public class StringFormatter implements Formatter<String> {

	@Override
	public String print(String input, Locale locale) {
		return input;
	}

	@Override
	public String parse(String input, Locale locale) throws ParseException {
		if (input != null) {
			input = input.trim();
			if (input.isEmpty()) {
				return null;
			} else {
				return input;
			}
		} else {
			return null;
		}
	}
	
	


}

package cz.lubos.api.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum for types of possible changes for ChangeRequest portlets
 */
public enum ChangeType {
	UNKNOWN					("unknown", 0), 
	MOBILE_PHONE_PRIVATE	("mobilePhonePrivate", 1),
	EMAIL_PRIVATE			("emailPrivate", 2),
	HEALTH_INSURANCE		("healthInsurance", 3),	
	ACCOUNT_NUMBER			("accountNumber", 4),	
	PERMANENT_ADDRESS		("permanentAddress", 5),
	TEMPORARY_ADDRESS		("temporaryAddress", 6),
	POSTAL_ADDRESS			("postalAddress", 7),
	EDUCATION				("education", 8);
	
	private final String name;
	private final Integer id;
	
	private ChangeType(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public static ChangeType getChangeTypeById(Integer id) {
		for (ChangeType cht : ChangeType.values()) {
			if (cht.id.equals(id)) {
				return cht;
			}
		}
		return UNKNOWN;
	}
	
	public static ChangeType getChangeTypeByName(String name) {
		for (ChangeType cht : ChangeType.values()) {
			if (cht.name.equals(name)) {
				return cht;
			}
		}
		return UNKNOWN;
	}
	
	public static List<ChangeType> getAllValidChangeTypes() {
		List<ChangeType> validChangeTypes = new ArrayList<>();
		for (ChangeType cht : ChangeType.values()) {
			if (!cht.equals(UNKNOWN)) {
				validChangeTypes.add(cht);
			}
		}
		return validChangeTypes;
	}
	
	
}





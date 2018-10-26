package cz.lubos.service.dbo;

import java.io.Serializable;

/**
 * DBO for positions with employees
 *
 */
public class JobPositionDbo implements Serializable {

	private static final long serialVersionUID = 5170756647741192194L;

	private int id;

	private String positionName;

	private String firstName;

	private String surname;
	
	private String personalId;
	
	private String hashId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getHashId() {
		return hashId;
	}

	public void setHashId(String hashId) {
		this.hashId = hashId;
	}
	
	

}
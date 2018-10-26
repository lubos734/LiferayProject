package cz.lubos.service.dbo;

import java.io.Serializable;

/**
 * DBO for table organization_structure.divisions
 *
 */
public class DivisionDbo implements Serializable {

	private static final long serialVersionUID = -2764917275466912555L;

	private int id;

	private String name;

	private Integer idParent;

	private int depth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdParent() {
		return idParent;
	}

	public void setIdParent(Integer idParent) {
		this.idParent = idParent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
package cz.lubos.api;


public class CodeListDto<K> {
	
	private K id;
	
	private String name;


	
	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

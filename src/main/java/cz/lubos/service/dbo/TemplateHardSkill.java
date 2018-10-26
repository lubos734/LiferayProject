package cz.lubos.service.dbo;

/**
 * Dbo for table position_description.template_hard_skill
 */
public class TemplateHardSkill {

	private Integer hardSkillId;
	
	private String name;
	
	private Boolean isNecessary;

	public Integer getHardSkillId() {
		return hardSkillId;
	}

	public void setHardSkillId(Integer hardSkillId) {
		this.hardSkillId = hardSkillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsNecessary() {
		return isNecessary;
	}

	public void setIsNecessary(Boolean isNecessary) {
		this.isNecessary = isNecessary;
	}
	
	

}

package cz.lubos.service.dbo;

/**
 * DBO for table position_description.template_language_level
 */
public class TemplateLanguage {

	private Integer languageId;
	
	private String languageName;
	
	private Integer languageLevelId;
	
	private String languageLevelName;

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public Integer getLanguageLevelId() {
		return languageLevelId;
	}

	public void setLanguageLevelId(Integer languageLevelId) {
		this.languageLevelId = languageLevelId;
	}

	public String getLanguageLevelName() {
		return languageLevelName;
	}

	public void setLanguageLevelName(String languageLevelName) {
		this.languageLevelName = languageLevelName;
	}
	

	
	

}

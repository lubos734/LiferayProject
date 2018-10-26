package cz.lubos.api.common;

import java.util.List;

import cz.lubos.api.CodeListDto;

/**
 * Service for postgre codelist 
 */
public interface CodeListServicePostgre {

	/**
	 * Get hard skills
	 * @return
	 */
	List<CodeListDto<Integer>> getHardSkills();
	
	/**
	 * Get soft skills
	 * @return
	 */
	List<CodeListDto<Integer>> getSoftSkills();
	
	/**
	 * Get driving licence
	 * @return
	 */
	List<CodeListDto<Integer>> getDrivingLicence();
	
	/**
	 * Get education
	 * @return
	 */
	List<CodeListDto<Integer>> getEducation();
	
	/**
	 * Get language
	 * @return
	 */
	List<CodeListDto<Integer>> getLanguage();
	
	/**
	 * Get language level
	 * @return
	 */
	List<CodeListDto<Integer>> getLanguageLevel();
	
	/**
	 * Get practice length
	 * @return
	 */
	List<CodeListDto<Integer>> getPracticeLength();
	
	/**
	 * Get employment types
	 * @return
	 */
	List<CodeListDto<Integer>> getEmploymentType();
	
	
	/**
	 * Get expertise and skills
	 * @return
	 */
	List<CodeListDto<Integer>> getExpertiseAndSkills();
	
	/**
	 * Get social skills
	 * @return
	 */
	List<CodeListDto<Integer>> getSocialSkills();

	/**
	 * Get training type by requirement
	 * @return
	 */
	List<CodeListDto<Integer>> getTrainingTypeByRequirement(boolean isRequired);

	/**
	 * Get medical exam types
	 * @return
	 */
	List<CodeListDto<Integer>> getMedicalExam();
	
	/**
	 * Get default medical exam type
	 * @return
	 */
	Integer getDefaultMedicalExamType();
	
	/**
	 * Get education levels
	 * @return
	 */
	List<CodeListDto<Integer>> getEducationLevel();
}

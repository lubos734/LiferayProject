package cz.lubos.service.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cz.lubos.api.CodeListDto;
import cz.lubos.api.division.dto.Division;

/**
 * Mapper for table organizational_structure.division
 */
public interface DivisionMapper {

	
	/**
	 * Insert division to database
	 * @param division
	 */
	void insertData(Division division);
	
	
	/**
	 * Update division
	 * @param division
	 */
	void updateData(Division division);
	
	
	/**
	 * Update division - deactivate, update valid date to
	 * @param id
	 * @param validDateTo
	 */
	void deactivateDivision(@Param("id") Integer id, @Param("validDateTo") Date validDateTo);

	/**
	 * Select root node
	 * @return
	 */
	List<Division> selectRootNode();

	/**
	 * Select division names
	 * @return
	 */
	List<CodeListDto<Integer>> selectDivisionNames();
	
	/**
	 * Select division name by given id
	 * @param divisionId
	 * @return
	 */
	String selectDivisionName(@Param("divisionId") Integer divisionId);
}

package cz.lubos.service.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cz.lubos.api.CodeListDto;
import cz.lubos.api.division.dto.DivisionOverview;
import cz.lubos.api.division.dto.DivisionOverviewFilter;

/**
 * Mapper for view vw_division_overview 
 */
public interface VwDivisionOverviewMapper {

	/**
	 * Select all divisions for autocomplete
	 * @return
	 */
	List<CodeListDto<Integer>> selectAllDivisions();
	
	/**
	 * Select data for table
	 * @param filter
	 * @return
	 */
	List<DivisionOverview> selectTableData(@Param("filter") DivisionOverviewFilter filter);
	
	/**
	 * Select data count for table
	 * @param filter
	 * @return
	 */
	Integer selectTableDataCount(@Param("filter") DivisionOverviewFilter filter);
	
	/**
	 * Select data for export divisions
	 * @param filter
	 * @return
	 */
	List<DivisionOverview> selectDataForExport(@Param("filter") DivisionOverviewFilter filter);
	
	/**
	 * Select data by ID
	 * @param id
	 * @return
	 */
	DivisionOverview selectDataById(Integer id);
	
	
	/**
	 * Select children division
	 * @param id
	 * @return
	 */
	List<String> selectChildrenDivision(Integer id);
	
	/**
	 * Select valid date from by division ID
	 * @param id
	 * @return
	 */
	Date selectValidFrom(Integer id);
	
}

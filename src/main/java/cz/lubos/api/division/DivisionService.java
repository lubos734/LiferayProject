package cz.lubos.api.division;

import java.util.Date;
import java.util.List;

import cz.lubos.api.CodeListDto;
import cz.lubos.api.division.dto.Division;
import cz.lubos.api.division.dto.DivisionOverview;
import cz.lubos.api.division.dto.DivisionOverviewFilter;
import cz.sonet.utils.jquery.datatable.TableData;

/**
 * Service for divisions portlets
 */
public interface DivisionService {

	/**
	 * Get table data for division table
	 * @param filter
	 * @return
	 */
	TableData<DivisionOverview> getDivsionOverviewData(DivisionOverviewFilter filter);
	
	
	/**
	 * Get division data by filter
	 * @param filter
	 * @return
	 */
	List<DivisionOverview> getDivisionsData(DivisionOverviewFilter filter);
	
	
	/**
	 * Get division by ID
	 * @param divisionId
	 * @return
	 */
	DivisionOverview getDivisionById(Integer divisionId);
	
	
	/**
	 * Get all division for autocomplete
	 * @return
	 */
	List<CodeListDto<Integer>> getDivisionAutocomplete();
	
	
	/**
	 * Insert division into table
	 * @param division
	 */
	void insertDivision(Division division);
	
	/**
	 * Update division
	 * @param division
	 */
	void updateDivision(Division division);
	
	
	/**
	 * Get children division
	 * @param id
	 * @return
	 */
	List<String> getChildrenDivision(Integer id);
	
	
	/**
	 * Get valid date from by division ID
	 * @param id
	 * @return
	 */
	Date getValidFrom(Integer id);
	
	
	/**
	 * Deactivate division
	 * @param id
	 * @param validDateTo
	 */
	void deactivateDivision(Integer id, Date validDateTo);
	
	/**
	 * Get all divisions
	 * @return
	 */
	List<Division> getAllDivisions();	
	
	
	/**
	 * Get root nodes
	 * @return
	 */
	List<Division> getRootNodes();

	/**
	 * Get division name 
	 * @return
	 */
	String getDivisionName(Integer id);
	
}

package cz.lubos.service.mapper;

import java.util.List;

import cz.lubos.api.division.dto.Division;

/**
 * Mapper for view organizational_structure.vw_division_direct_manager
 */
public interface VwDivisionDirectManagerMapper {

	/**
	 * Select all divisions
	 * @return
	 */
	List<Division> selectAllDivisions();
	
}

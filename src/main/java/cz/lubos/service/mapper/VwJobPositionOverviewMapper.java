package cz.lubos.service.mapper;

import java.util.List;

public interface VwJobPositionOverviewMapper {
	
	/**
	 * 
	 * @param divisionId
	 * @return
	 */
	List<String> selectDataByDivisionId(Integer divisionId);

}

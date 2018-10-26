package cz.lubos.api.jobposition;

import java.util.List;

/**
 * Service for job positions
 */
public interface JobPositionService {
	
	
	/**
	 * Get job positions list by division id
	 * @param divisionId
	 * @return
	 */
	List<String> getJobPositionsByDivsionId(Integer divisionId);
	

}

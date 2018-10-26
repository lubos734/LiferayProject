package cz.lubos.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import cz.lubos.api.jobposition.JobPositionService;
import cz.lubos.service.database.SessionFactory;
import cz.lubos.service.mapper.VwJobPositionOverviewMapper;

@Service
public class JobPositionServiceImpl implements JobPositionService{
	
	@Override
	public List<String> getJobPositionsByDivsionId(Integer divisionId) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwJobPositionOverviewMapper vwJobPositionOverviewMapper = session.getMapper(VwJobPositionOverviewMapper.class);
			return vwJobPositionOverviewMapper.selectDataByDivisionId(divisionId);
		}
	}


}

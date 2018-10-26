package cz.lubos.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import cz.lubos.api.CodeListDto;
import cz.lubos.api.division.DivisionService;
import cz.lubos.api.division.dto.Division;
import cz.lubos.api.division.dto.DivisionOverview;
import cz.lubos.api.division.dto.DivisionOverviewFilter;
import cz.lubos.service.database.SessionFactory;
import cz.lubos.service.mapper.DivisionMapper;
import cz.lubos.service.mapper.VwDivisionDirectManagerMapper;
import cz.lubos.service.mapper.VwDivisionOverviewMapper;
import cz.sonet.utils.jquery.datatable.TableData;

@Service
public class DivisionServiceImpl implements DivisionService{
	
//	private static final Log log = LogFactoryUtil.getLog(DivisionServiceImpl.class);

	@Override
	public TableData<DivisionOverview> getDivsionOverviewData(DivisionOverviewFilter filter) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwDivisionOverviewMapper vwDivisionOverviewMapper = session.getMapper(VwDivisionOverviewMapper.class);
			List<DivisionOverview> dataList = vwDivisionOverviewMapper.selectTableData(filter);
			Integer dataCount = vwDivisionOverviewMapper.selectTableDataCount(filter);
			return new TableData<DivisionOverview>(dataList, dataCount);
		}
	}

	@Override
	public List<DivisionOverview> getDivisionsData(DivisionOverviewFilter filter) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwDivisionOverviewMapper vwDivisionOverviewMapper = session.getMapper(VwDivisionOverviewMapper.class);
			return vwDivisionOverviewMapper.selectDataForExport(filter);
		}
	}

	@Override
	public List<CodeListDto<Integer>> getDivisionAutocomplete() {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwDivisionOverviewMapper vwDivisionOverviewMapper = session.getMapper(VwDivisionOverviewMapper.class);
			return vwDivisionOverviewMapper.selectAllDivisions();
		}
	}

	@Override
	public void insertDivision(Division division) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			DivisionMapper divisionMapper = session.getMapper(DivisionMapper.class);
			divisionMapper.insertData(division);
			session.commit();
		}
	}

	@Override
	public DivisionOverview getDivisionById(Integer divisionId) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwDivisionOverviewMapper vwDivisionOverviewMapper = session.getMapper(VwDivisionOverviewMapper.class);
			return vwDivisionOverviewMapper.selectDataById(divisionId);
		}
	}

	@Override
	public List<String> getChildrenDivision(Integer id) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwDivisionOverviewMapper vwDivisionOverviewMapper = session.getMapper(VwDivisionOverviewMapper.class);
			return vwDivisionOverviewMapper.selectChildrenDivision(id);
		}
	}

	@Override
	public Date getValidFrom(Integer id) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwDivisionOverviewMapper vwDivisionOverviewMapper = session.getMapper(VwDivisionOverviewMapper.class);
			return vwDivisionOverviewMapper.selectValidFrom(id);
		}
	}

	@Override
	public void deactivateDivision(Integer id, Date validDateTo) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			DivisionMapper divisionMapper = session.getMapper(DivisionMapper.class);
			divisionMapper.deactivateDivision(id, validDateTo);
			session.commit();
		}
	}

	@Override
	public void updateDivision(Division division) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			DivisionMapper divisionMapper = session.getMapper(DivisionMapper.class);
			divisionMapper.updateData(division);
			session.commit();
		}
	}

	@Override
	public List<Division> getAllDivisions() {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			VwDivisionDirectManagerMapper vwDivisionDirectManagerMapper = session.getMapper(VwDivisionDirectManagerMapper.class);
			return vwDivisionDirectManagerMapper.selectAllDivisions();
		}
	}

	@Override
	public List<Division> getRootNodes() {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			DivisionMapper divisionMapper = session.getMapper(DivisionMapper.class);
			return divisionMapper.selectRootNode();
		}
	}
	
	@Override
	public String getDivisionName(Integer divisionId) {
		try (SqlSession session = SessionFactory.getSessionPostgre()) {
			DivisionMapper divisionMapper = session.getMapper(DivisionMapper.class);
			return divisionMapper.selectDivisionName(divisionId);
		}
	}

}

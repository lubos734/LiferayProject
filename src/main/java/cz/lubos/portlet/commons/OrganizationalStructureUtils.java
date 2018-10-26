package cz.lubos.portlet.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import cz.lubos.api.CodeListDto;
import cz.lubos.api.division.dto.Division;

public class OrganizationalStructureUtils {

	public static String createJsonStringCodeList(List<CodeListDto<Integer>> dataList) {

		JSONArray dataArray = JSONFactoryUtil.createJSONArray();
		for (CodeListDto<Integer> data : dataList) {
			JSONObject o = JSONFactoryUtil.createJSONObject();
			o.put("id", data.getId());
			o.put("name", data.getName());
			dataArray.put(o);
		}

		return dataArray.toString();
	}
	
	/**
	 * Create data for select list from code list
	 * @param codeList
	 * @return
	 */
	public static List<SelectCrate<String>> createSelects(List<CodeListDto<String>> codeList) {
		List<SelectCrate<String>> selectList = new ArrayList<SelectCrate<String>>();
		for (CodeListDto<String> data : codeList) {
			SelectCrate<String> selectCrate = new SelectCrate<String>();
			selectCrate.setKey(data.getId());
			selectCrate.setValue(data.getId() + " - " + data.getName());
			selectList.add(selectCrate);
		}
		return selectList;
		
	}
	
	
	/**
	 * Create data for select list from code list
	 * @param codeList
	 * @return
	 */
	public static List<SelectCrate<Integer>> createSelectsInteger(List<CodeListDto<Integer>> codeList) {
		List<SelectCrate<Integer>> selectList = new ArrayList<SelectCrate<Integer>>();
		for (CodeListDto<Integer> data : codeList) {
			SelectCrate<Integer> selectCrate = new SelectCrate<Integer>();
			selectCrate.setKey(data.getId());
			selectCrate.setValue(data.getId() + " - " + data.getName());
			selectList.add(selectCrate);
		}
		return selectList;
		
	}
	
	
	/**
	 * Create division code list with cascade style
	 * @param rootNodes
	 * @param divisions
	 * @return
	 */
	public static List<CodeListDto<Integer>> createDivisionCascadeCodeList(List<Division> rootNodes, List<Division> divisions) {
		
		Map<Integer, ArrayList<Division>> divisionTree = new HashMap<>();
		
		List<CodeListDto<Integer>> result = new ArrayList<CodeListDto<Integer>>();
		if (rootNodes != null) {
			for (Division r : rootNodes) {			
				divisionTree.put(r.getId(), new ArrayList<>());			
			}			
			if (divisions != null) {
				for (Division d : divisions) {	
					if (divisionTree.get(d.getParentId()) != null) {
						divisionTree.get(d.getParentId()).add(d);
						divisionTree.put(d.getId(), new ArrayList<>());	
					}
				}
			}
			for (Division r : rootNodes) {			
				createDivisionCascadeCodeListStep(result, divisionTree, r);			
			}			
		}
				
		return result;
		
	}
	
	/**
	 * Recursively called method that adds the division into the result and calls itself onto subdivisions of the division
	 * @param result
	 * @param divisionTree
	 * @param division
	 */
	private static void createDivisionCascadeCodeListStep(List<CodeListDto<Integer>> result, Map<Integer, ArrayList<Division>> divisionTree, Division division) {
		
		CodeListDto<Integer> item = new CodeListDto<>();
		item.setId(division.getId());
		item.setName(division.getName());
		if (division.getLevel() != null) {			
			for (int i = 0; i < division.getLevel().intValue(); i++) {				
				item.setName("&#160;&#160;&#160;" + item.getName());				
			}			
		}
		result.add(item);
		if (divisionTree.get(division.getId()) != null) {			
			for (Division d : divisionTree.get(division.getId())) {				
				createDivisionCascadeCodeListStep(result, divisionTree, d);				
			}			
		}
		
	}
	
	/**
 	 * Join subject street, identification number and if exists orientation number
 	 * If not exists street enters the place municipality name 
 	 * @param street
 	 * @param municipalityName
 	 * @param identificationNumber
 	 * @param orientationNumber
 	 * @return
 	 */
 	public static String getAddressFormat(String street, String municipalityName, Integer identificationNumber, String orientationNumber) {
 		String address = "";
 		if (identificationNumber == null) {
 			return address;
 		}
 		if(StringUtils.isEmpty(street)){
 			address = PortletUtils.getStringNotNull(municipalityName);
 		} else {
 			address = PortletUtils.getStringNotNull(street);
 		}
 		address = address.concat(" " + PortletUtils.getStringNotNull(String.valueOf(identificationNumber)));
 		if(!StringUtils.isEmpty(orientationNumber)){
 			address = address.concat("/" + PortletUtils.getStringNotNull(orientationNumber));
 		}
 		return address;
 	}
	
}

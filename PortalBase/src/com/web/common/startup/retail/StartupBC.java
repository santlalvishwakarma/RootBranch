package com.web.common.startup.retail;

import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.systemowner.MenuHierarchyDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.dao.SeqTypeValueMapper;
import com.web.foundation.dao.SqlRequest;
import com.web.foundation.dao.SqlResponse;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class StartupBC extends BackingClass {

	public StartupOpr getMenuMapping() throws FrameworkException {
		StartupOpr startupOpr = getHierarchicalMenuData();
		return startupOpr;
	}

	public StartupOpr getHierarchicalMenuData() throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getHierarchicalMenuData ::: ");

		SqlRequest sqlRequest = new SqlRequest();

		HashMap<Integer, SeqTypeValueMapper> hmSqlQuery = new HashMap<Integer, SeqTypeValueMapper>();

		myLog.debug(" StartupBC ::: Inside getMenuMapping method ::: ");

		HashMap<String, String> hmQueryDetail = new HashMap<String, String>();

		hmQueryDetail.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		hmQueryDetail.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		hmQueryDetail.put(IDAOConstant.SQL_TEXT, StartupSqlTemplate.GET_HIERARCHICAL_MENU_MAPPING);

		sqlRequest.setHmInputParams(hmQueryDetail);
		sqlRequest.setHmSqlQuery(hmSqlQuery);
		SqlResponse response = performDBOperation(sqlRequest);

		StartupOpr returnstartupOpr = new StartupOpr();
		HashMap<Integer, HashMap<String, Object>> responseMap = response.getHmDataValues();
		myLog.debug(" resultset got ::: " + responseMap);
		myLog.debug(" resultset size got ::: " + responseMap.size());

		int hierarchyMenuCount = -1;
		int categoryLevelOneMenuCount = -1;
		int categoryLevelTwoMenuCount = -1;
		int categoryLevelThreeMenuCount = -1;
		int categoryLevelFourMenuCount = -1;

		long hierarchyId = 0l;
		long categoryLevelOneId = 0l;
		long categoryLevelTwoId = 0l;
		long categoryLevelThreeId = 0l;
		long categoryLevelFourId = 0l;

		MenuHierarchyDVO menuHierarchyRecord = new MenuHierarchyDVO();

		if (responseMap != null) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);

				if (resultRow.get("hierarchy_id") != null
						&& (hierarchyId == 0l || !resultRow.get("hierarchy_id").equals(hierarchyId))) {
					hierarchyMenuCount++;

					menuHierarchyRecord.getHierarchyDetailsList().add(new MenuHierarchyDVO());

					// myLog.debug(" hierarchyMenuCount ::: " +
					// hierarchyMenuCount);
					// myLog.debug(" hierarchy ::: " +
					// menuHierarchyRecord.getHierarchyDetailsList().size());

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
							.setMenuHierarchyId(String.valueOf(resultRow.get("hierarchy_id").toString()));
					menuHierarchyRecord
							.getHierarchyDetailsList()
							.get(hierarchyMenuCount)
							.setMenuURL(
									CommonConstant.CONTEXT_PATH + "/" + (String) resultRow.get("hierarchy_code")
											+ CommonConstant.BROWSE_PRODUCTS_URL);
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
							.setMenuTitle((String) resultRow.get("hierarchy_name"));
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
							.setMenuRel((String) resultRow.get("hierarchy_name"));

					hierarchyId = Long.valueOf(resultRow.get("hierarchy_id").toString());

					categoryLevelOneMenuCount = -1;
					categoryLevelTwoMenuCount = -1;
					categoryLevelThreeMenuCount = -1;
					categoryLevelFourMenuCount = -1;

				}

				if (resultRow.get("category_level_1") != null
						&& (categoryLevelOneId == 0l || !resultRow.get("category_level_1").equals(categoryLevelOneId))) {
					categoryLevelOneMenuCount++;

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.add(new MenuHierarchyDVO());

					// myLog.debug(" categoryLevelOneMenuCount ::: " +
					// categoryLevelOneMenuCount);
					// myLog.debug(" category level 1 ::: "
					// +
					// menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
					// .getHierarchyDetailsList().size());

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount)
							.setMenuHierarchyId(String.valueOf(resultRow.get("category_level_1").toString()));
					menuHierarchyRecord
							.getHierarchyDetailsList()
							.get(hierarchyMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount)
							.setMenuURL(
									menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getMenuURL()
											.replaceAll("products/", (String) resultRow.get("category_level_one_code"))
											+ CommonConstant.BROWSE_PRODUCTS_URL);
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount)
							.setMenuTitle((String) resultRow.get("category_level_one_name"));
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount)
							.setMenuRel((String) resultRow.get("category_level_one_name"));
					categoryLevelOneId = Long.valueOf(resultRow.get("category_level_1").toString());

					categoryLevelTwoMenuCount = -1;
					categoryLevelThreeMenuCount = -1;
					categoryLevelFourMenuCount = -1;

				}

				if (resultRow.get("category_level_2") != null
						&& (categoryLevelTwoId == 0l || !resultRow.get("category_level_2").equals(categoryLevelTwoId))) {
					categoryLevelTwoMenuCount++;

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().add(new MenuHierarchyDVO());

					// myLog.debug(" categoryLevelTwoMenuCount ::: " +
					// categoryLevelTwoMenuCount);
					// myLog.debug(" category level 2 ::: "
					// +
					// menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
					// .getHierarchyDetailsList().get(categoryLevelOneMenuCount).getHierarchyDetailsList()
					// .size());

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.setMenuHierarchyId(String.valueOf(resultRow.get("category_level_2").toString()));
					menuHierarchyRecord
							.getHierarchyDetailsList()
							.get(hierarchyMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelTwoMenuCount)
							.setMenuURL(
									menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
											.getHierarchyDetailsList().get(categoryLevelOneMenuCount).getMenuURL()
											.replaceAll("products/", (String) resultRow.get("category_level_two_code"))
											+ CommonConstant.BROWSE_PRODUCTS_URL);
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.setMenuTitle((String) resultRow.get("category_level_two_name"));
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.setMenuRel((String) resultRow.get("category_level_two_name"));
					categoryLevelTwoId = Long.valueOf(resultRow.get("category_level_2").toString());

					categoryLevelThreeMenuCount = -1;
					categoryLevelFourMenuCount = -1;

				}

				if (resultRow.get("category_level_3") != null
						&& (categoryLevelThreeId == 0l || !resultRow.get("category_level_3").equals(
								categoryLevelThreeId))) {
					categoryLevelThreeMenuCount++;

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().add(new MenuHierarchyDVO());

					// myLog.debug(" categoryLevelThreeMenuCount ::: " +
					// categoryLevelThreeMenuCount);
					// myLog.debug(" category level 3 ::: "
					// +
					// menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
					// .getHierarchyDetailsList().get(categoryLevelOneMenuCount).getHierarchyDetailsList()
					// .get(categoryLevelTwoMenuCount).getHierarchyDetailsList().size());

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().get(categoryLevelThreeMenuCount)
							.setMenuHierarchyId(String.valueOf(resultRow.get("category_level_3").toString()));
					menuHierarchyRecord
							.getHierarchyDetailsList()
							.get(hierarchyMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelThreeMenuCount)
							.setMenuURL(
									menuHierarchyRecord
											.getHierarchyDetailsList()
											.get(hierarchyMenuCount)
											.getHierarchyDetailsList()
											.get(categoryLevelOneMenuCount)
											.getHierarchyDetailsList()
											.get(categoryLevelTwoMenuCount)
											.getMenuURL()
											.replaceAll("products/",
													(String) resultRow.get("category_level_three_code"))
											+ CommonConstant.BROWSE_PRODUCTS_URL);
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().get(categoryLevelThreeMenuCount)
							.setMenuTitle((String) resultRow.get("category_level_three_name"));
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().get(categoryLevelThreeMenuCount)
							.setMenuRel((String) resultRow.get("category_level_three_name"));
					categoryLevelThreeId = Long.valueOf(resultRow.get("category_level_3").toString());

					categoryLevelFourMenuCount = -1;
				}

				if (resultRow.get("category_level_4") != null
						&& (categoryLevelFourId == 0l || !resultRow.get("category_level_4").equals(categoryLevelFourId))) {
					categoryLevelFourMenuCount++;

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().get(categoryLevelThreeMenuCount).getHierarchyDetailsList()
							.add(new MenuHierarchyDVO());

					// myLog.debug(" categoryLevelFourMenuCount ::: " +
					// categoryLevelFourMenuCount);
					// myLog.debug(" category level 4 ::: "
					// +
					// menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount)
					// .getHierarchyDetailsList().get(categoryLevelOneMenuCount).getHierarchyDetailsList()
					// .get(categoryLevelTwoMenuCount).getHierarchyDetailsList()
					// .get(categoryLevelThreeMenuCount).getHierarchyDetailsList().size());

					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().get(categoryLevelThreeMenuCount).getHierarchyDetailsList()
							.get(categoryLevelFourMenuCount)
							.setMenuHierarchyId(String.valueOf(resultRow.get("category_level_4").toString()));
					menuHierarchyRecord
							.getHierarchyDetailsList()
							.get(hierarchyMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelThreeMenuCount)
							.getHierarchyDetailsList()
							.get(categoryLevelFourMenuCount)
							.setMenuURL(
									menuHierarchyRecord
											.getHierarchyDetailsList()
											.get(hierarchyMenuCount)
											.getHierarchyDetailsList()
											.get(categoryLevelOneMenuCount)
											.getHierarchyDetailsList()
											.get(categoryLevelTwoMenuCount)
											.getHierarchyDetailsList()
											.get(categoryLevelThreeMenuCount)
											.getMenuURL()
											.replaceAll("products/",
													(String) resultRow.get("category_level_three_code"))
											+ CommonConstant.BROWSE_PRODUCTS_URL);
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().get(categoryLevelThreeMenuCount).getHierarchyDetailsList()
							.get(categoryLevelFourMenuCount)
							.setMenuTitle((String) resultRow.get("category_level_four_name"));
					menuHierarchyRecord.getHierarchyDetailsList().get(hierarchyMenuCount).getHierarchyDetailsList()
							.get(categoryLevelOneMenuCount).getHierarchyDetailsList().get(categoryLevelTwoMenuCount)
							.getHierarchyDetailsList().get(categoryLevelThreeMenuCount).getHierarchyDetailsList()
							.get(categoryLevelFourMenuCount)
							.setMenuRel((String) resultRow.get("category_level_four_name"));
					categoryLevelFourId = Long.valueOf(resultRow.get("category_level_4").toString());
				}

			}
		}

		returnstartupOpr.setMenuHierarchyRecord(menuHierarchyRecord);

		return returnstartupOpr;
	}
}

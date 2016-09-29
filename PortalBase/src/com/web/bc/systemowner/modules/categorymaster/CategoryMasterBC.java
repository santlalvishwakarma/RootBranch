package com.web.bc.systemowner.modules.categorymaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class CategoryMasterBC extends BackingClass {
	public List<CategoryDVO> getAllCategories() throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Category BC :: getAllCategories starts ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_ALL_CATEGORIES);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getAllCategories :: Resultset got ::" + responseMap);

		List<CategoryDVO> productCategoryList = new ArrayList<CategoryDVO>();
		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				CategoryDVO productCategoryRecord = new CategoryDVO();
				if (resultSetMap.get("category_id") != null)
					productCategoryRecord.setId(Long.valueOf(resultSetMap.get("category_id").toString()));
				productCategoryRecord.setCode((String) resultSetMap.get("category_code"));
				productCategoryRecord.setName((String) resultSetMap.get("category_name"));
				productCategoryRecord.setDescription((String) resultSetMap.get("category_description"));

				productCategoryList.add(productCategoryRecord);
			}
		}
		return productCategoryList;
	}
}

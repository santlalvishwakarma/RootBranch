package com.web.common.startup.retail;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.systemowner.MenuHierarchyDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.util.CommonUtil;

public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = 5640813349861451799L;
	private OptionsDVO allOptions;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		try {
			// ServletContext context = getServletContext();
			ServletContext context = config.getServletContext();
			myLog.debug(" context.getContextPath() ::: " + context.getContextPath());

			// CREATE MENU
			StartupOpr startupOpr = new StartupBF().getMenuMapping();
			String menuHierarchyHtml = new MenuHierarchyDVO().createMenu(startupOpr.getMenuHierarchyRecord());

			System.out.println(menuHierarchyHtml);
			myLog.debug(" menuHierarchyHtml ::: " + menuHierarchyHtml);

			config.getServletContext().setAttribute(CommonConstant.RETAIL_DETAILS_MENU_ELEMENT, menuHierarchyHtml);
			String serverlUrl = CommonUtil.getServerUrl();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			externalContext.getApplicationMap().put("imageURL",
					(CommonConstant.HttpSchemes.HTTP + serverlUrl + CommonConstant.Urls.WEBDAV_CONTEXT_NAME));

		} catch (FrameworkException e) {
			myLog.error("Error occurred in startup" + e);
		} catch (BusinessException e) {
			myLog.error("Error occurred in startup" + e);
		}
	}

	// private MenuHierarchyDVO parseHierarchicalMenuData(StartupOpr startupOpr)
	// {
	// Gson gson = new Gson();
	// MenuHierarchyDVO rootNode = gson.fromJson(
	// startupOpr.getHierarchicalMenuText(), MenuHierarchyDVO.class);
	// if (rootNode == null) {
	// rootNode = new MenuHierarchyDVO();
	// }
	// return rootNode;
	// }

	public OptionsDVO getAllOptions() {
		return allOptions;
	}

}
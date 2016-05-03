package com.web.common.startup.retail;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.MenuHierarchyDVO;

public class StartupOpr extends OperationalDataValueObject {
	private static final long serialVersionUID = -3511809318826653456L;

	private MenuHierarchyDVO menuHierarchyRecord;
	private String hierarchicalMenuText;

	public String getHierarchicalMenuText() {
		return hierarchicalMenuText;
	}

	public void setHierarchicalMenuText(String hierarchicalMenuText) {
		this.hierarchicalMenuText = hierarchicalMenuText;
	}

	public MenuHierarchyDVO getMenuHierarchyRecord() {
		if (menuHierarchyRecord == null) {
			menuHierarchyRecord = new MenuHierarchyDVO();
		}
		return menuHierarchyRecord;
	}

	public void setMenuHierarchyRecord(MenuHierarchyDVO menuHierarchyRecord) {
		this.menuHierarchyRecord = menuHierarchyRecord;
	}

}

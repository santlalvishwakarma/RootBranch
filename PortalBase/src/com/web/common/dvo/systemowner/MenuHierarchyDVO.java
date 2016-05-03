package com.web.common.dvo.systemowner;

import java.util.ArrayList;

import com.web.common.dvo.common.BaseDVO;

public class MenuHierarchyDVO extends BaseDVO {

	private static final long serialVersionUID = -803206037566486659L;

	private ArrayList<MenuHierarchyDVO> hierarchyDetailsList;
	private String menuHierarchyId;
	private String menuURL;
	private String menuTitle;
	private String menuRel;

	public ArrayList<MenuHierarchyDVO> getHierarchyDetailsList() {
		if (hierarchyDetailsList == null) {
			hierarchyDetailsList = new ArrayList<MenuHierarchyDVO>();
		}
		return hierarchyDetailsList;
	}

	public void setHierarchyDetailsList(
			ArrayList<MenuHierarchyDVO> hierarchyDetailsList) {
		this.hierarchyDetailsList = hierarchyDetailsList;
	}

	public String getMenuHierarchyId() {
		return menuHierarchyId;
	}

	public void setMenuHierarchyId(String menuHierarchyId) {
		this.menuHierarchyId = menuHierarchyId;
	}

	public String getMenuRel() {
		return menuRel;
	}

	public void setMenuRel(String menuRel) {
		this.menuRel = menuRel;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	/**
	 * can be use for creating current reference menu
	 * 
	 * @return finalMenu
	 * 
	 */
	public String createMenu() {
		String finalMenu = "";
		if (getHierarchyDetailsList().size() > 0) {
			finalMenu = HierarchyRecursiveMethod(getHierarchyDetailsList(),
					getHierarchyDetailsList().size() - 1);
			finalMenu = finalMenu + " </ul> ";
			return finalMenu;
		}
		return finalMenu;
	}
	public String createMenu(MenuHierarchyDVO menuObject) {
		if (menuObject.getHierarchyDetailsList().size() > 0) {
			String finalMenu = HierarchyRecursiveMethod(
					menuObject.getHierarchyDetailsList(), menuObject
							.getHierarchyDetailsList().size() - 1);
			finalMenu = finalMenu + " </ul> ";
			return finalMenu;
		}
		return "";
	}

	private String HierarchyRecursiveMethod(
			ArrayList<MenuHierarchyDVO> hierarchyLocalList, int size) {
		if (size == -1) {
			return " <ul> ";

		}
		StringBuffer hierarchyUrl = new StringBuffer();
		hierarchyUrl.append(HierarchyRecursiveMethod(hierarchyLocalList,
				size - 1));
		hierarchyUrl.append(" <li> <a href=\""
				+ hierarchyLocalList.get(size).getMenuURL() + "\" > "
				+ hierarchyLocalList.get(size).getMenuTitle() + " </a> ");
		if (hierarchyLocalList.get(size).getHierarchyDetailsList().size() > 0) {
			hierarchyUrl.append(CategoryLevelOneRecursiveMethod(
					hierarchyLocalList.get(size).getHierarchyDetailsList(),
					hierarchyLocalList.get(size).getHierarchyDetailsList()
							.size() - 1));
			hierarchyUrl.append(" </ul> ");
		}
		hierarchyUrl.append(" </li>");
		return hierarchyUrl.toString();
	}

	private String CategoryLevelOneRecursiveMethod(
			ArrayList<MenuHierarchyDVO> levelLocalOneList, int size) {
		if (size == -1) {
			return " <ul> ";
		}
		StringBuffer levelOne = new StringBuffer();
		levelOne.append(CategoryLevelOneRecursiveMethod(levelLocalOneList,
				size - 1));
		levelOne.append(" <li>  <a href=\""
				+ levelLocalOneList.get(size).getMenuURL() + "\" > "
				+ levelLocalOneList.get(size).getMenuTitle() + " </a> ");
		if (levelLocalOneList.get(size).getHierarchyDetailsList().size() > 0) {
			levelOne.append(CategoryLevelTwoRecursiveMethod(levelLocalOneList
					.get(size).getHierarchyDetailsList(), levelLocalOneList
					.get(size).getHierarchyDetailsList().size() - 1));
			levelOne.append(" </ul> ");
		}
		levelOne.append(" </li>");
		return levelOne.toString();
	}

	private String CategoryLevelTwoRecursiveMethod(
			ArrayList<MenuHierarchyDVO> levelLocalTwoList, int size) {
		if (size == -1) {
			return " <ul> ";
		}
		StringBuffer levelTwo = new StringBuffer();
		levelTwo.append(CategoryLevelTwoRecursiveMethod(levelLocalTwoList,
				size - 1));
		levelTwo.append(" <li> <a href=\""
				+ levelLocalTwoList.get(size).getMenuURL() + "\" > "
				+ levelLocalTwoList.get(size).getMenuTitle() + " </a> ");
		if (levelLocalTwoList.get(size).getHierarchyDetailsList().size() > 0) {
			levelTwo.append(CategoryLevelThreeRecursiveMethod(levelLocalTwoList
					.get(size).getHierarchyDetailsList(), levelLocalTwoList
					.get(size).getHierarchyDetailsList().size() - 1));
			levelTwo.append(" </ul> ");
		}
		levelTwo.append(" </li> ");

		return levelTwo.toString();
	}

	private String CategoryLevelThreeRecursiveMethod(
			ArrayList<MenuHierarchyDVO> levelLocalThreeList, int size) {
		if (size == -1) {
			return " <ul> ";
		}
		StringBuffer levelThree = new StringBuffer();
		levelThree.append(CategoryLevelThreeRecursiveMethod(
				levelLocalThreeList, size - 1));
		levelThree.append(" <li> <a href=\""
				+ levelLocalThreeList.get(size).getMenuURL() + "\" > "
				+ levelLocalThreeList.get(size).getMenuTitle() + " </a> ");
		if (levelLocalThreeList.get(size).getHierarchyDetailsList().size() > 0) {
			levelThree.append(CategoryLevelFourRecursiveMethod(
					levelLocalThreeList.get(size).getHierarchyDetailsList(),
					levelLocalThreeList.get(size).getHierarchyDetailsList()
							.size() - 1));
			levelThree.append(" </ul> ");
		}
		levelThree.append(" </li> ");

		return levelThree.toString();
	}

	private String CategoryLevelFourRecursiveMethod(
			ArrayList<MenuHierarchyDVO> levelLocalFourList, int size) {
		if (size == -1) {
			return " <ul> ";
		}
		StringBuffer levelFour = new StringBuffer();
		levelFour.append(CategoryLevelFourRecursiveMethod(levelLocalFourList,
				size - 1));
		levelFour.append(" <li> <a href=\""
				+ levelLocalFourList.get(size).getMenuURL() + "\" > "
				+ levelLocalFourList.get(size).getMenuTitle() + " </a> "
				+ "  </li> ");

		return levelFour.toString();
	}
}

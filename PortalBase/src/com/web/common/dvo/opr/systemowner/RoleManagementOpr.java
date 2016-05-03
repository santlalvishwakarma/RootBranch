package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.retail.modules.user.UserRoleMappingDVO;
import com.web.common.dvo.systemowner.RoleDVO;

public class RoleManagementOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -461804100404418115L;
	private RoleDVO roleRecord;
	private ArrayList<RoleDVO> roleList;
	private UserDVO userRecord;
	ArrayList<UserRoleMappingDVO> userRoleList;

	/**
	 * @return the roleRecord
	 */
	public RoleDVO getRoleRecord() {
		if (roleRecord == null) {
			roleRecord = new RoleDVO();
		}
		return roleRecord;
	}

	/**
	 * @param roleRecord
	 *            the roleRecord to set
	 */
	public void setRoleRecord(RoleDVO roleRecord) {
		this.roleRecord = roleRecord;
	}

	/**
	 * @return the roleList
	 */
	public ArrayList<RoleDVO> getRoleList() {
		if (roleList == null) {
			roleList = new ArrayList<RoleDVO>();
		}
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(ArrayList<RoleDVO> roleList) {
		this.roleList = roleList;
	}

	public UserDVO getUserRecord() {
		if (userRecord == null) {
			userRecord = new UserDVO();
		}
		return userRecord;
	}

	public void setUserRecord(UserDVO userRecord) {
		this.userRecord = userRecord;
	}

	public ArrayList<UserRoleMappingDVO> getUserRoleList() {
		if (userRoleList == null) {
			userRoleList = new ArrayList<UserRoleMappingDVO>();
		}
		return userRoleList;
	}

	public void setUserRoleList(ArrayList<UserRoleMappingDVO> userRoleList) {
		this.userRoleList = userRoleList;
	}

}

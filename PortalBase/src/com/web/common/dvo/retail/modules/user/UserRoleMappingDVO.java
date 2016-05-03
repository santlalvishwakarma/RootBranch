package com.web.common.dvo.retail.modules.user;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.systemowner.RoleDVO;

public class UserRoleMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 5669464228755448044L;
	private RoleDVO roleRecord;
	private UserDVO userRecord;

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

	public UserDVO getUserRecord() {
		if (userRecord == null) {
			userRecord = new UserDVO();
		}
		return userRecord;
	}

	public void setUserRecord(UserDVO userRecord) {
		this.userRecord = userRecord;
	}

}

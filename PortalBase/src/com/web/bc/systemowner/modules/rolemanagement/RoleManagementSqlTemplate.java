package com.web.bc.systemowner.modules.rolemanagement;

import com.web.foundation.dao.SqlTemplate;

public class RoleManagementSqlTemplate implements SqlTemplate {

	static final String GET_ALL_ROLES = " SELECT role_id, role_code, role_name, role_description FROM core_role_master; ";

	static final String GET_ROLES_FOR_USER = "SELECT cr.core_roles_id, cr.role_code, cr.role_name, cr.role_description"
			+ " FROM core_roles cr, core_users_roles_mapping curm WHERE curm.user_id = ?"
			+ " AND curm.role_id = cr.core_roles_id;";

}

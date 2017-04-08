package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.GuestUserDVO;

public class GuestUserOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 4214818386716095125L;
	private GuestUserDVO guestUserRecord;
	private List<GuestUserDVO> guestUserList;
	private GuestUserDVO selectedUserRecord;

	public GuestUserDVO getGuestUserRecord() {
		if (guestUserRecord == null) {
			guestUserRecord = new GuestUserDVO();
		}
		return guestUserRecord;
	}

	public void setGuestUserRecord(GuestUserDVO guestUserRecord) {
		this.guestUserRecord = guestUserRecord;
	}

	public List<GuestUserDVO> getGuestUserList() {
		if (guestUserList == null) {
			guestUserList = new ArrayList<GuestUserDVO>();
		}
		return guestUserList;
	}

	public void setGuestUserList(List<GuestUserDVO> guestUserList) {
		this.guestUserList = guestUserList;
	}

	public GuestUserDVO getSelectedUserRecord() {
		if (selectedUserRecord == null) {
			selectedUserRecord = new GuestUserDVO();
		}
		return selectedUserRecord;
	}

	public void setSelectedUserRecord(GuestUserDVO selectedUserRecord) {
		this.selectedUserRecord = selectedUserRecord;
	}

}

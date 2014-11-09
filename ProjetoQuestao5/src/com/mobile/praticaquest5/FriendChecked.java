package com.mobile.praticaquest5;

public class FriendChecked{

	private Friend friend;
	private boolean checked;
	
	public FriendChecked() {
	}

	public FriendChecked(Friend friend, boolean checked) {
		this.friend = friend;
		this.checked = checked;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}

package com.ufc.mobile.quest1.model;

import java.io.Serializable;

public class Contact implements Serializable{

	private String formattedPhone;
	private String phone;
	private String facebookName;
	private String twitter;
	private String facebook;
	private String facebookUsername;
	
	public Contact() {
	}

	public Contact(String formattedPhone, String phone, String facebookName,
			String twitter, String facebook, String facebookUsername) {
		super();
		this.formattedPhone = formattedPhone;
		this.phone = phone;
		this.facebookName = facebookName;
		this.twitter = twitter;
		this.facebook = facebook;
		this.facebookUsername = facebookUsername;
	}

	public String getFormattedPhone() {
		return formattedPhone;
	}

	public void setFormattedPhone(String formattedPhone) {
		this.formattedPhone = formattedPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFacebookName() {
		return facebookName;
	}

	public void setFacebookName(String facebookName) {
		this.facebookName = facebookName;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((facebook == null) ? 0 : facebook.hashCode());
		result = prime * result
				+ ((facebookName == null) ? 0 : facebookName.hashCode());
		result = prime
				* result
				+ ((facebookUsername == null) ? 0 : facebookUsername.hashCode());
		result = prime * result
				+ ((formattedPhone == null) ? 0 : formattedPhone.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (facebook == null) {
			if (other.facebook != null)
				return false;
		} else if (!facebook.equals(other.facebook))
			return false;
		if (facebookName == null) {
			if (other.facebookName != null)
				return false;
		} else if (!facebookName.equals(other.facebookName))
			return false;
		if (facebookUsername == null) {
			if (other.facebookUsername != null)
				return false;
		} else if (!facebookUsername.equals(other.facebookUsername))
			return false;
		if (formattedPhone == null) {
			if (other.formattedPhone != null)
				return false;
		} else if (!formattedPhone.equals(other.formattedPhone))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String dados = "";
		dados+="formattedPhone: "+formattedPhone+"\n";
		dados+="facebookName: "+facebookName+"\n";
		dados+="twitter: "+twitter+"\n";
		return dados;
	}
}

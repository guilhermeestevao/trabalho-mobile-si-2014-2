package com.ufc.mobile.quest1.model;

public class Contact {

	private String formattedPhone;
	private String phone;
	
	public Contact() {
	}

	public Contact(String formattedPhone, String phone) {
		super();
		this.formattedPhone = formattedPhone;
		this.phone = phone;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((formattedPhone == null) ? 0 : formattedPhone.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Contact [formattedPhone=" + formattedPhone + ", phone=" + phone
				+ "]";
	}
	
}

package com.vivriti.modal;

public class CustomerVO {

	private String m_sCustomerName;

	private String m_sPhoneNumber;
	
	private int  m_iAge;
	
	private boolean isCurrentEmployee;
	
	private boolean isSeniorCitizen;
	
	private float m_fSeniorCitizenDiscount;
	
	private float m_fEmpDiscount;

	public String getCustomerName() {
		return m_sCustomerName;
	}

	public void setCustomerName(String p_sCustomerName) {
		this.m_sCustomerName = p_sCustomerName;
	}

	public String getPhoneNumber() {
		return m_sPhoneNumber;
	}

	public void setPhoneNumber(String p_sPhoneNumber) {
		this.m_sPhoneNumber = p_sPhoneNumber;
	}

	public int getAge() {
		return m_iAge;
	}

	public void setAge(int p_iAge) {
		this.m_iAge = p_iAge;
	}

	public boolean isCurrentEmployee() {
		return isCurrentEmployee;
	}

	public void setIsCurrentEmployee(boolean isCurrentEmployee) {
		this.isCurrentEmployee = isCurrentEmployee;
	}

	public boolean isSeniorCitizen() {
		return isSeniorCitizen;
	}

	public void setIsSeniorCitizen(boolean isSeniorCitizen) {
		this.isSeniorCitizen = isSeniorCitizen;
	}

	public float getSeniotCitizenDiscount() {
		return m_fSeniorCitizenDiscount;
	}

	public void setSeniotCitizenDiscount(float p_fSeniorCitizenDiscount) {
		this.m_fSeniorCitizenDiscount = p_fSeniorCitizenDiscount;
	}
	
	public float getEmpDiscount() {
		return m_fEmpDiscount;
	}

	public void setEmpDiscount(float p_fEmpDiscount) {
		this.m_fEmpDiscount = p_fEmpDiscount;
	}
	
	
}

package com.vivriti.modal;

public class ProductVO implements Cloneable{

	private String m_sItemName;

	private int m_iQuantity;

	private float m_fPrice;

	private float m_fDiscount;
	
	public ProductVO(){
		
	}
	
	public ProductVO(String m_sItemName, int m_iQuantity, float m_fPrice, float m_fDiscount) {
		super();
		this.m_sItemName = m_sItemName;
		this.m_iQuantity = m_iQuantity;
		this.m_fPrice = m_fPrice;
		this.m_fDiscount = m_fDiscount;
	}
	
	public Object clone() throws CloneNotSupportedException
    {
		ProductVO product = (ProductVO) super.clone();
		
		product.m_iQuantity = this.m_iQuantity;
 
        return product;
    }


	public String getItemName() {
		return m_sItemName;
	}
	
	public void setItemName(String p_sItemName) {
		this.m_sItemName = p_sItemName;
	}

	public int getQuantity() {
		return m_iQuantity;
	}

	public void setQuantity(int p_iQuantity) {
		this.m_iQuantity = p_iQuantity;
	}

	public float getPrice() {
		return m_fPrice;
	}

	public void setPrice(float p_fPrice) {
		this.m_fPrice = p_fPrice;
	}

	public float getDiscount() {
		return m_fDiscount;
	}

	public void setDiscount(float p_fDiscount) {
		this.m_fDiscount = p_fDiscount;
	}



}


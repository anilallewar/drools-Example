package com.anil.drools.model.decisiontable;

import java.io.Serializable;

/**
 * POJO determining insurance policy
 * 
 * @author anila
 * 
 */
public class Policy implements Serializable {
	private static final long serialVersionUID = 2373693126027288609L;

	private String policyType;
	private Double policyBasePrice;

	/**
	 * @return the policyType
	 */
	public String getPolicyType() {
		return policyType;
	}

	/**
	 * @param policyType
	 *            the policyType to set
	 */
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	/**
	 * @return the policyBasePrice
	 */
	public Double getPolicyBasePrice() {
		return policyBasePrice;
	}

	/**
	 * @param policyBasePrice
	 *            the policyBasePrice to set
	 */
	public void setPolicyBasePrice(Double policyBasePrice) {
		this.policyBasePrice = policyBasePrice;
	}

}

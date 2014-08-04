package com.anil.drools.model.decisiontable;

import java.io.Serializable;

/**
 * POJO for Driver
 * 
 * @author anila
 * 
 */
public class Driver implements Serializable {
	private static final long serialVersionUID = -6593133687858967543L;

	private int age;
	private String locationRiskProfile;
	private int numberOfPriorClaims;

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the locationRiskProfile
	 */
	public String getLocationRiskProfile() {
		return locationRiskProfile;
	}

	/**
	 * @param locationRiskProfile
	 *            the locationRiskProfile to set
	 */
	public void setLocationRiskProfile(String locationRiskProfile) {
		this.locationRiskProfile = locationRiskProfile;
	}

	/**
	 * @return the numberOfPriorClaims
	 */
	public int getNumberOfPriorClaims() {
		return numberOfPriorClaims;
	}

	/**
	 * @param numberOfPriorClaims
	 *            the numberOfPriorClaims to set
	 */
	public void setNumberOfPriorClaims(int numberOfPriorClaims) {
		this.numberOfPriorClaims = numberOfPriorClaims;
	}
}

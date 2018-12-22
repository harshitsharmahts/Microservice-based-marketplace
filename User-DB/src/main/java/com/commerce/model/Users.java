package com.commerce.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * <p>
 *     MongoDB JPA Model bean class to hold the information of an user.
 * </p>
 *
 * @author Harshit Sharma
 */

@Document(collection="users")
public class Users {

	@Id
	private String id;

	@Field(value="full_name")
	private String fullName;
	
	@Field(value="email")
	private String email;
	
	@Field(value="phn_no")
	private String phoneNumber;
	
	@Field(value="password")
	private String password;
	
	@Field(value="address")
	private String address;
	
	@Field(value="profile_pic_url")
	private String profilePic;

	@Field(value="checked_out")
	private List<String> checkoutList;
	
	@Field(value="purchased_item")
	private List<String> purchaseHistory;
	
	@Field(value="isSuspended")
	private boolean suspended;

	public Users() {

	}

	public Users(String fullName,
                 String email,
                 String phoneNumber,
                 String password,
                 String address,
                 String profilePic,
                 List<String> checkoutList,
                 List<String> purchaseHistory) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
		this.profilePic = profilePic;
		this.checkoutList = checkoutList;
		this.purchaseHistory = purchaseHistory;
		this.suspended = false;
	}

	public Optional<List<String>> getCheckoutList() {
		return Optional.ofNullable(checkoutList);
	}

	public Users setCheckoutList(List<String> checkoutList) {
		this.checkoutList = checkoutList;
		return this;
	}

	public Optional<List<String>> getPurchaseHistory() {
	    return Optional.ofNullable(purchaseHistory);
	}

	public Users setPurchaseHistory(List<String> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
		return this;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public Users setSuspended(boolean suspended) {
		this.suspended = suspended;
		return this;
	}

	public String getId() {
		return id;
	}

	public Users setId(String id) {
		this.id = id;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public Users setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Users setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Users setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Users setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Users setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public Users setProfilePic(String profilePic) {
		this.profilePic = profilePic;
		return this;
	}
}

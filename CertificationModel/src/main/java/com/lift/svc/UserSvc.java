package com.lift.svc;

import java.util.Set;

import com.lift.so.user.AddressSO;
import com.lift.so.user.UserSO;

public interface UserSvc {

	Integer createUser(UserSO userSO);
	
	boolean isValidUser(Integer userId);
	
	UserSO getUserDetails(Integer userId);
	
	Boolean updateUserProfile(UserSO userSO);
	
	Boolean updatePassword(Integer userId, String password);
	
	void deleteLocation(Integer addressId);
	
	void deleteLocations(Set<Integer> addressIds);
	
	Boolean deleteUser(Integer userId);
	
	void updateUserLocation(Integer locationId, AddressSO addressSO);
}

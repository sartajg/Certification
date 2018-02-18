package com.lift.service.user;

import javax.ejb.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.lift.so.user.AddressSO;
import com.lift.so.user.UserSO;
import com.lift.svc.UserSvc;

@Singleton
public class UserServiceImpl extends SpringBeanAutowiringSupport implements UserService {

	@Autowired
	private UserSvc userSvc;
	
	@Override
	public Boolean isValidUser(Integer id) {
		Boolean isValid = userSvc.isValidUser(id);
		return isValid;
	}

	@Override
	public Integer createUser(UserSO userSO) {
		
		Integer userId = userSvc.createUser(userSO);
		
		return userId;
	}

	@Override
	public UserSO getUserDetails(Integer userId) {
		
		return userSvc.getUserDetails(userId);
	}

	@Override
	public Boolean updateUserProfile(Integer userId, UserSO userSO) {
		userSO.setUserId(userId);
		return userSvc.updateUserProfile(userSO);
	}

	@Override
	public Boolean updatePassword(Integer userId, String password) {
		
		return userSvc.updatePassword(userId, password);
	}

	@Override
	public Boolean updateLocation(Integer userId, Integer locationId, AddressSO addressSO) {
		//Validate user
		
		Boolean isSuccess = true;
		userSvc.updateUserLocation(locationId, addressSO);
		return isSuccess;
	}

	@Override
	public Boolean updateLocation(Integer userId, AddressSO addressSO) {
		
		userSvc.updateUserLocation(addressSO.getLocationId(), addressSO);
		return true;
	}

	@Override
	public Boolean deleteLocation(Integer userId, Integer locationId) {
		//Validate user with addressId
		userSvc.deleteLocation(locationId);
		
		return true;
	}

	@Override
	public Boolean deleteUser(Integer userId) {
		return userSvc.deleteUser(userId);
	}

}

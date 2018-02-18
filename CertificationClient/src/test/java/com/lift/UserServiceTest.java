package com.lift;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lift.service.user.UserService;
import com.lift.so.enums.AddressType;
import com.lift.so.enums.UserType;
import com.lift.so.user.AddressSO;
import com.lift.so.user.UserSO;

public class UserServiceTest {
	
	ResteasyWebTarget target = null;
	ResteasyClient client = null;
	UserService userService = null;

	@Before
	public void init() {
		client = new ResteasyClientBuilder().build();
		client.abortIfClosed();
		target = client.target("http://localhost:8080/certification/rest/");
		userService = target.proxy(UserService.class);
	}
	
	@After
	public void destroy() {
		client.close();
	}
	
	@Test
	public void testCreateUser() {
	
		UserSO userSO = new UserSO();
		userSO.setFirstName("Sartaj");
		userSO.setLastName("Hussain");
		userSO.setPassword("Hello to all");
		userSO.setUserType(UserType.CUSTOMER);
		
		AddressSO addressSO = new AddressSO();
		addressSO.setAddressType(AddressType.CURRENT);
		addressSO.setPinCode("560029");
		addressSO.setStreet("Marathalli");
		
		AddressSO addressSO1 = new AddressSO();
		addressSO1.setAddressType(AddressType.PERMANENT);
		addressSO1.setPinCode("560037");
		addressSO1.setStreet("Marathalli");
		
		Set<AddressSO> locations = new HashSet<>();
		locations.add(addressSO);
		locations.add(addressSO1);
		
		userSO.setLocations(locations);
		
		Integer userId = userService.createUser(userSO);
		Assert.assertTrue(userService.isValidUser(userId));
		
	}
	
	@Test
	public void testUpdateUserProfile() {
		Integer userId = 1650;
		Assert.assertTrue(userService.isValidUser(userId));
		//Assert.assertTrue(userService.updatePassword(userId, "updated password"));
		UserSO userSO = new UserSO();
		userSO.setUserId(userId);
		AddressSO updateAddressSO1 = new AddressSO();
		updateAddressSO1.setAddressType(AddressType.OTHER);
		updateAddressSO1.setPinCode("560037");
		updateAddressSO1.setStreet("Marathalli update 2");
		
		Set<AddressSO> updateLocations = new HashSet<>();
		updateLocations.add(updateAddressSO1);
		userSO.setLocations(updateLocations);
		userSO.setFirstName("again update me "+userSO.getFirstName());
		Assert.assertTrue(userService.updateUserProfile(userId, userSO));
	}
	
	@Test
	public void testGetUserDetails() {
		UserSO so = userService.getUserDetails(1650);
		System.out.println(so);
		Assert.assertTrue(so != null);
	}
	
	@Test
	public void testUpdateUserLocation() {
		
		Integer userId = 1650;
		Integer locationId = 1050;
		AddressSO addressSO = new AddressSO();
		addressSO.setAddressType(AddressType.OTHER);
		addressSO.setPinCode("486002");
		Assert.assertTrue(userService.updateLocation(userId, locationId, addressSO));
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 1750;
		Assert.assertTrue(userService.deleteUser(userId));
		
	}
	
	@Test
	public void generateRandomNumberFromGivenRange() {
		
		int arr[] = {1,2,3,4,5,6,7,8,9,10};
		
		int count = arr.length;
		
		while(count >= arr.length/2) {
			int ofInt = ThreadLocalRandom.current().nextInt(0, count);
			
			int temp = arr[ofInt];
			arr[ofInt] = arr[count-1];
			arr[count-1] = temp;
			
			count--;
		}
		System.out.println(Arrays.toString(arr));
	}
}

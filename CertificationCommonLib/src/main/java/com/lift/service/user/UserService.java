package com.lift.service.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lift.so.user.AddressSO;
import com.lift.so.user.UserSO;

@Path(value = "/user")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface UserService {

	/**
	 * Check if user exists or user is valid user
	 * 
	 * @param userName
	 * @return
	 */
	@GET
	@Path("/isexists/{id}")
	Boolean isValidUser(@PathParam("id") Integer id);

	/**
	 * Return available user name or create new user name.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 *//*
	@POST
	@Path("/available/username/{fname}/{lname}")
	String createUserName(@PathParam("fname") String firstName, @PathParam("lname") String lastName);*/

	/**
	 * Any user can create his own account.
	 * 
	 * @param userSO
	 * @return
	 */
	@POST
	@Path("/add")
	Integer createUser(UserSO userSO);

	/**
	 * Get user details based on userId. Only ADMIN can see user details.
	 * 
	 * @param userId
	 * @return
	 */

	@GET
	@Path("/{id}")
	UserSO getUserDetails(@PathParam("id") Integer userId);

	/**
	 * User can update his profile.
	 * 
	 * @param userSO
	 * @return
	 */

	@PUT
	@Path("/{userId}")
	Boolean updateUserProfile(@PathParam("userId") Integer userId, UserSO userSO);
	
	/**
	 * 
	 * @param password
	 * @return
	 */
	@PUT
	@Path("/{id}/update/password/{password}")
	Boolean updatePassword(@PathParam("id") Integer userId, @PathParam("password") String password);
	
	/**
	 * 
	 * @param userId
	 * @param locationId
	 * @param addressSO
	 * @return
	 */
	@PUT
	@Path("/{id}/address/{locationId}")
	Boolean updateLocation(@PathParam("id") Integer userId, @PathParam("locationId") Integer locationId, AddressSO addressSO);
	
	/**
	 * Add new address to the user
	 * @param userId
	 * @param addressSO
	 * @return
	 */
	@POST
	@Path("/{id}/address/{locationId}")
	Boolean updateLocation(@PathParam("id") Integer userId, AddressSO addressSO);
	
	/**
	 * 
	 * @param userId
	 * @param locationId
	 * @return
	 */
	@DELETE
	@Path("/{id}/address/{locationId}")
	Boolean deleteLocation(@PathParam("id") Integer userId, @PathParam("locationId") Integer locationId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	Boolean deleteUser(@PathParam("id") Integer userId);
}

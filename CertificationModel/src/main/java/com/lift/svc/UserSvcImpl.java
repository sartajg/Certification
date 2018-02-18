package com.lift.svc;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lift.model.Address;
import com.lift.model.User;
import com.lift.so.user.AddressSO;
import com.lift.so.user.UserSO;
import com.lift.util.HibernateUtil;

public class UserSvcImpl implements UserSvc, HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Integer createUser(UserSO userSO) {

		Session session = getSession();
		
		User user = new User();
		user.getModelFromSo(userSO);
		Integer userId = (Integer) session.save(user);
		
		return userId;
	}

	@Override
	@Transactional
	public boolean isValidUser(Integer userId) {
		
		Session session = getSession();
		
		User user = (User) session.get(User.class, userId);
		
		return (user == null ? Boolean.FALSE : Boolean.TRUE);
	}

	@Override
	@Transactional
	public UserSO getUserDetails(Integer userId) {
		Session session = getSession();
		
		User user = (User) session.get(User.class, userId);
		UserSO userSO = user.getSoFromModel();
		
		return userSO;
	}

	@Override
	@Transactional
	public Boolean updateUserProfile(UserSO userSO) {
		
		Session session = getSession();
		User user = (User) session.get(User.class, userSO.getUserId());
		user.getModelFromSo(userSO);
		session.update(user);
		
		return true;
	}

	@Override
	@Transactional
	public Boolean updatePassword(Integer userId, String password) {
		
		Session session = getSession();
		User user = (User) session.load(User.class, userId);
		user.setPassword(password);
		
		session.update(user);
		
		return true;
	}

	@Override
	@Transactional
	public void updateUserLocation(Integer locationId, AddressSO addressSO) {
		
		Session session = getSession();
		Address address = (Address) session.get(Address.class, locationId);
		addressSO.setLocationId(null);
		address.getModelFromSo(addressSO);
		
		session.update(address);
	}
	
	@Override
	@Transactional
	public void deleteLocation(Integer addressId) {
		
		Session session = getSession();
		Address address = (Address) session.load(Address.class, addressId);
		session.delete(address);
	}

	@Override
	@Transactional
	public void deleteLocations(Set<Integer> addressIds) {
		Session session = getSession();
		Query query = session.createQuery("delete Address where id in (:addIds)");
		query.setParameterList("addIds", addressIds);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public Boolean deleteUser(Integer userId) {
		Session session = getSession();
		User user = new User();
		user.setUserId(userId);
		user.getLocations().clear();
		session.delete(user);
		return true;
	}

	@Override
	public Session getSession() {
		return sessionFactory.openSession();
	}
}

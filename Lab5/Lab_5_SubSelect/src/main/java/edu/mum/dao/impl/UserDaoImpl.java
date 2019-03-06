package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.UserDao;
import edu.mum.domain.User;


@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super.setDaoType(User.class );
	}

 	public List<User> findAllJoinFetch() {
 
 		Query query = entityManager.createQuery("select u from User u");
		List<User> users = (List<User>) query.getResultList();
		users.get(0).getBoughtItems().get(0);
		return users;

	}


 }
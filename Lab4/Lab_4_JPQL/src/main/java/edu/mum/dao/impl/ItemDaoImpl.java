package edu.mum.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import edu.mum.dao.ItemDao;
import edu.mum.domain.Item;
import edu.mum.domain.User;


@SuppressWarnings("unchecked")
@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

	public ItemDaoImpl() {
		super.setDaoType(Item.class);
	}
	
	public List<Item> findByCategoryName(String categoryName) {
		 
		//Query query =  entityManager.createQuery("SELECT i FROM Item i INNER JOIN i.categories c WHERE c.name = :name");
		// OR
		//Query query =  entityManager.createQuery("from Item i join fetch i.categories c where c.name = :name");
		
		// But since we have to use named query, we define that in Entity class and do this here
		Query query = entityManager.createNamedQuery("Item.findByCategoryName");
		return (List<Item>) query.setParameter("name", categoryName).getResultList();
	}

	
	public List<Item> findBySellerOrBuyer(Integer initialPrice, User buyer, User seller) {
		BigDecimal price = initialPrice == null ? null : new BigDecimal(initialPrice);
//		String sellerQuery = "select i from Item AS i LEFT JOIN i.seller AS s where s = :seller AND :seller != NULL AND i.initialPrice > :inPrice";
		String sellerQuery = "select distinct i from Item i where :seller != NULL AND i.initialPrice > :inPrice AND i.seller = :seller";
		String buyerQuery = "select i from User u LEFT JOIN u.boughtItems i WHERE :buyer != NULL AND u = :buyer AND i.reservePrice = i.initialPrice";
		String finalQuery = "select i from Item i "
								+ "WHERE i IN ("+ sellerQuery +") "
								   + "OR i IN ("+ buyerQuery +")";
		
		//String finalQuery = sellerQuery + " UNION " + buyerQuery;
		
		Query query = null;
		// TODO Seller Test
		if (buyer != null && seller != null) 
			query = entityManager.createQuery(finalQuery).setParameter("seller", seller).setParameter("buyer", buyer).setParameter("inPrice", price);
		else if (seller != null)	
			query = entityManager.createQuery(sellerQuery).setParameter("seller", seller).setParameter("inPrice", price);
		// TODO Buyer test
		else if (buyer != null)	
			query = entityManager.createQuery(buyerQuery).setParameter("buyer", buyer);
		
//		Query query = entityManager.createQuery("select distinct i from Item i, User u where "
//				+		"i.initialPrice > :inPrice AND i.seller = :seller" 
//				+		"OR i.reservePrice = i.initialPrice AND i.buyer = :buyer");

    
		return (List<Item>) query.getResultList();
	}
	
	public List<Item> findItemCriteria(Integer initialPrice, User buyer, User seller) {
	     CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    // Typed query - expected results are of the type Item
		CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
		// From part of the clause
		Root<Item> itemRoot = query.from(Item.class);
		// The Select
		query.select(itemRoot).distinct(true);
		
		 List<Predicate> predicateList = new ArrayList<Predicate>();
		  
		    // Seller & initialPrice
		    if ((seller != null) && (initialPrice != null)  ) {

              // TODO fill in query....
		    	
		    	
		    }
		 
		    // buyer & initial = reserve price
	    if ((buyer != null)) {

	    		// Get buyer: user = :buyer
	    		Root<User> userRoot = query.from(User.class);
		    	Predicate buyerPredicate = criteriaBuilder.equal(userRoot,buyer);
		    	
		    	// get items:  item is member of user.boughtItems
		    	Expression<Set<Item>> items = userRoot.get("boughtItems");
		    	Predicate memberOf = criteriaBuilder.isMember(itemRoot, items);

		    	// Combine...
		    	Predicate andBuyer = criteriaBuilder.and(buyerPredicate,memberOf);

		    	// item.initPrice == item.reservePrice
		    	Predicate pricePredicate = criteriaBuilder.equal(itemRoot.get("initialPrice"),itemRoot.get("reservePrice"));

		    	Predicate buyerMatchPredicate = criteriaBuilder.and(andBuyer,pricePredicate);
		        predicateList.add(buyerMatchPredicate);

		    }
		    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    query.where(criteriaBuilder.or(predicates));
		 
		 List<Item> items =  (List<Item>) entityManager.createQuery( query ).getResultList();
		 return items;	}
 }
package com.ideas2it.onlineshopping.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.database.Connector;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * This class stores the list of role details which can be stored, fetched,
 * updated and deleted
 * </p>
 */
public class RoleDAO {

    private static Logger logger = Logger.getLogger(RoleDAO.class);

    /**
     * <p>
     * Method in which all the role details are stored 
     * </p>
     *
     * @param role - contains each individual role information
     */
    public void createRole(Role role) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem occurred in database while creating role... Role Id: " +
                         role.getId() + "Role Name: " + role.getName(), exception);
            throw new ShoppingException("Error occurred while creating a role");
        } 
    }

    /**
     * <p>
     * To display all the roles
     * </p>
     *
     * @return - it returns all the role informations
     */
    public List<Role> displayRole() throws ShoppingException {
        List<Role> roles = new ArrayList<Role>();
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            roles = session.createQuery("FROM Role").list();
            transaction.commit();
            return roles;
        } catch (HibernateException exception) {
            logger.error("Problem occurred in database while displaying role...", exception);
            throw new ShoppingException("Error while displaying role information");
        }
    }

    /**
     * <p>
     * To update the particular role based on role id
     * </p>
     *
     * @param role - contains the role detail to be updated
     */
    public void updateRole(Role role) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem occurred in database while updating role... Role Id: " +
                         role.getId() + "Role Name: " + role.getName(), exception);
            throw new ShoppingException("Error occurred while updating a role");
        }
    }

    /**
     * <p>
     * Method used to remove particular role information
     * </p>
     *
     * @param roleId - indicates the role id entered by the user
     */
    public void delete(int roleId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Role role = (Role) session.get(Role.class, roleId);
            role.setIsActive(Boolean.FALSE);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Error occurred in database while deleting role... Role Id: " + roleId, exception);
            throw new ShoppingException("Problem while deleting role");
        }
    }

    /**
     * <p>
     * To get role details if present using role id
     * </p>
     *
     * @param roleId - indicates the role id entered by the user
     * @return - it returns the role object based on the id given
     */
    public Role getRoleById(int roleId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Role role = (Role) session.get(Role.class, roleId);
            transaction.commit();
            return role;
        } catch (HibernateException exception) {
            logger.error("Error while getting a particular role... Role Id: " + roleId, exception);
            throw new ShoppingException("Error while getting a particular role information");
        }
    }
}

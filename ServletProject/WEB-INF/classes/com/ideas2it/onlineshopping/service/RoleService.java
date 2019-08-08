package com.ideas2it.onlineshopping.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlineshopping.dao.RoleDAO;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.exception.ShoppingException;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.RoleDAOInitializer;

/**
 * <p>
 * It contains all the logical operations performed in role
 * </p>
 */
public class RoleService {

    private RoleDAO roleDAO = RoleDAOInitializer.getInstance();
    
    /**
     * <p>
     * This method used to create roles for each user
     * </p>
     *
     * @param role - it contains a particular role information
     */
    public void create(Role role) throws ShoppingException {
        Timestamp dateTime = DateUtils.getDateTime();
        role.setDateTime(dateTime);
        role.setUpdatedDateTime(dateTime);
        role.setIsActive(Boolean.TRUE);
        roleDAO.createRole(role);
    }

    /**
     * <p>
     * Method used to display all the roles
     * </p>
     *
     * @return - it returns all the role details created
     */
    public List<Role> display() throws ShoppingException {
        return roleDAO.displayRole();
    }

    /**
     * <p>
     * Method used to check if the role exists or not
     * </p>
     *
     * @param roleId - it indicates the role id given by the user
     * @return - it returns role if exist else throws error message
     */
    public Role getRole(int roleId) throws ShoppingException {
        Role role = roleDAO.getRoleById(roleId);
        if (null == role || role.getIsActive() == Boolean.FALSE) {
            throw new ShoppingException("Invalid Role id, Data does not exist");
        }
        return role;
    }

    /**
     * <p>
     * To update role information
     * </p>
     *
     * @param role - contains the role detail to update
     */
    public void updateRoleDetail(Role role, int roleId, String date) throws ShoppingException {
        getRole(roleId);
        Timestamp createdDate = DateUtils.convertDate(date);
        role.setDateTime(createdDate);
        Timestamp dateTime = DateUtils.getDateTime();
        role.setUpdatedDateTime(dateTime);
        role.setId(roleId);
        roleDAO.updateRole(role);
    }

    /**
     * <p>
     * In this method, particular role can be identified and removed using 
     * role id
     * </p>
     * 
     * @param roleId - role id mentioned by the user
     */
    public void deleteDetail(int roleId) throws ShoppingException {
        getRole(roleId);
        roleDAO.delete(roleId);
    }
}

package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.service.RoleService;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class RoleServiceInitializer {

    private static RoleService roleService;
    private static RoleServiceInitializer roleServiceInitializer;
    private static Logger logger = Logger.getLogger(RoleServiceInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private RoleServiceInitializer() {
        roleService = new RoleService();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static RoleService getInstance() {
        if (null == roleService) {
            logger.info("roleService singleton:" + roleService);
            roleServiceInitializer = new RoleServiceInitializer();
            return roleService;
        }
        return roleService;
    }
}

package com.gcatechnologies.constants;

public class UsersApiConstants {

    /** Users controller api **/
    public static final String USERS_API_PREFIX = "/users";

    /** Constantes CRUD genéricas **/
    public static final String CREATE = CrudApiConstants.CREATE;
    public static final String LIST = CrudApiConstants.LIST;
    public static final String UPDATE = CrudApiConstants.UPDATE; // NOT
    public static final String DELETE = CrudApiConstants.DELETE;

    /**
     * Obtener usuarios segun su id
     */
    public static final String GET_BY_ID = "/get-id";

    /**
     * Obtener usuarios segun su nombre de usuario
     */
    public static final String GET_BY_USERNAME = "/get-username";
}
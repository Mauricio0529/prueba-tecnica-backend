package com.gcatechnologies.constants;

public class RentalApiConstants {
    /** Users controller api **/
    public static final String RENTAL_API_PREFIX = "/rental";

    /** Constantes CRUD genéricas **/
    public static final String CREATE = CrudApiConstants.CREATE;
    public static final String LIST = CrudApiConstants.LIST;
    public static final String UPDATE = CrudApiConstants.UPDATE; // NOT
    //public static final String DELETE = CrudApiConstants.DELETE;

    /**
     * Obtener usuarios segun su id
     */
    public static final String GET_BY_ID = "/get-id";

    /**
     * Obtener usuarios segun su nombre de usuario
     */
    public static final String GET_BY_USER_ID = "/get-userId";

    /**
     * Actualizar el estado del alquiler
     */
    public static final String UPDATE_STATUS = "/update-status";

}

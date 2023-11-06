package com.gcatechnologies.constants;

public class MethodPaymentApiConstants {

    /** Method Payment controller api **/
    public static final String METHOD_PAYMENT_API_PREFIX = "/type-payment";

    /** Constantes CRUD genéricas **/
    public static final String CREATE = CrudApiConstants.CREATE;
    public static final String LIST = CrudApiConstants.LIST;
    public static final String UPDATE = CrudApiConstants.UPDATE;
    public static final String DELETE = CrudApiConstants.DELETE;

    /**
     * Obtener Medios de pagos segun su id
     */
    public static final String GET_BY_ID = "/get-id";

    /**
     * Obtener Medios de pagos segun el id de un usuario
     */
    public static final String GET_BY_USERID = "/get-userid";

    /**
     * Actualizar los Medios de pagos de un usuario
     */
    public static final String UPDATE_BY_USER = "/update-user";

    /**
     * Eliminar un Medio de pago de un usuario
     */
    public static final String DELETE_BY_USER = "/delete-users";
}

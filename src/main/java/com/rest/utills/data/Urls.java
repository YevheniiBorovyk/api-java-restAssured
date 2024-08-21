package com.rest.utills.data;

public class Urls {

    //pet
    public static final String PET_UPLOAD_IMAGE = "/pet/%s/uploadImage";
    public static final String PET = "/pet";
    public static final String PET_FIND_BY_STATUS = "/pet/findByStatus";
    public static final String PET_ID = "/pet/%s";

    //store
    public static final String STORE_INVENTORY = "/store/inventory";
    public static final String STORE_ORDER = "/store/order";
    public static final String STORE_ORDER_ID = "/store/order/%s";

    //user
    public static final String USER_URL = "/user";
    public static final String USER_CREATE_WITH_ARRAY = "/user/createWithArray";
    public static final String LOGIN_URL = "/user/login";
    public static final String LOGOUT_URL = "/user/logout";
}

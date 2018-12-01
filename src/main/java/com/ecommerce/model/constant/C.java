package com.ecommerce.model.constant;

public class C {

    public static class STATUS {
        public static final String LOGIN_SUCCESS = "TRUE";
        public static final String LOGIN_FAILURE = "FALSE";
        public static final String USER_DOES_NOT_EXIST = "UDNE";
        public static final String USER_ALREADY_EXIST = "UAE";
        public static final String PASSWORD_DOES_NOT_MATCH = "PDNM";
        public static final String SIGNUP_SUCCESS = "TRUE";
        public static final String SIGNUP_FAILURE = "FALSE";
    }

    public static class REST {
        private static final String BASE_URL = "http://db-user-service/user";
        public static final String GET_URL = BASE_URL + "/get/{email}";
        public static final String ADD_URL = BASE_URL + "/";
    }
}

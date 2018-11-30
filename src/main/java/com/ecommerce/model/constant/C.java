package com.ecommerce.model.constant;

public class C {

    public static class STATUS {

        public static final String ADDED = "added";
        public static final String UPDATED = "updated";
        public static final String DELETED = "deleted";
        public static final String TRUE = "true";
    }

    public static class REST {
        private static final String baseUrl = "http://db-item-service/item";
        public static final String GET = baseUrl+"/";
        public static final String POST_ADD_URL = baseUrl+"/";
        public static final String PUT_UPDATE_URL = baseUrl+"/";
        public static final String DELETE_URL = baseUrl+"/{id}";
    }
}

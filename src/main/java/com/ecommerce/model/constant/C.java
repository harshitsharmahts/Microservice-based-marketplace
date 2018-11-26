package com.ecommerce.model.constant;

public class C {

    public static class STATUS {

        public static final String ADDED = "added";
        public static final String UPDATED = "updated";
        public static final String DELETED = "deleted";
        public static final String TRUE = "true";
    }

    public static class REST {
        private static final String baseUrl = "http://localhost:8082/item";
        public static final String GET = baseUrl+"/";
        public static final String POST_ADD_URL = baseUrl+"/";
        public static final String PUT_UPDATE_URL = baseUrl+"/";
        public static final String DELETE_URL = baseUrl+"/{id}";
    }

    public static class AWS {

        public static final String BUCKET = "microservice-image";
        public static final String END_POINT_URL = "https://s3.amazonaws.com";
    }
}

package com.ecommerce.model.response;

import com.ecommerce.model.Users;

public class Status {

    private Boolean success;
    private Users content;

    public Status(Boolean success, Users content) {
        this.success = success;
        this.content = content;
    }

    public Status() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public Status setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Users getContent() {
        return content;
    }

    public Status setContent(Users content) {
        this.content = content;
        return this;
    }
}

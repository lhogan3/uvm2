package com.lbelivea.uvm2;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult() {
    }

    @Nullable
    Integer getError() {
        return error;
    }
}

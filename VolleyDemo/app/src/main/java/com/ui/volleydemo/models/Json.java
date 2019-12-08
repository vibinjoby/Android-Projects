
package com.ui.volleydemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Json {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("validation-factors")
    @Expose
    private ValidationFactors validationFactors;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ValidationFactors getValidationFactors() {
        return validationFactors;
    }

    public void setValidationFactors(ValidationFactors validationFactors) {
        this.validationFactors = validationFactors;
    }

    @Override
    public String toString() {
        return "Json{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", validationFactors=" + validationFactors +
                '}';
    }
}

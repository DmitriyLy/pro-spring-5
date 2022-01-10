package com.apress.prospring5.ch4.properties;

public class AppProperty {
    private String applicationHome;
    private String userHome;

    public String getApplicationHome() {
        return applicationHome;
    }

    public void setApplicationHome(String applicationHome) {
        this.applicationHome = applicationHome;
    }

    public String getUserHome() {
        return userHome;
    }

    public void setUserHome(String userHome) {
        this.userHome = userHome;
    }

    @Override
    public String toString() {
        return "AppProperty{" +
                "applicationHome='" + applicationHome + '\'' +
                ", userHome='" + userHome + '\'' +
                '}';
    }
}

package com.sbhachu.fanatixchallenge.application;

// application properties - ideally this should be loaded from a properties file at runtime
// so it can be integrated with a CI environemnt (specifying different files to debug, qa, prod
// environments.
public class ApplicationConfiguration {

    public static final String BASE_URL = "http://api.fanatix.com";

    public static final String APP_ID = "cos-iphone";

    public static final String APP_VERSION = "1.2.3AT";

    public static final String APP_PLATFORM = "android";

    public static final boolean INCLUDE_ALL = true;

    public static final int ITEM_ID = 49797863;

    public static final String AUTH_FANATIX_ID = "50f82e1d4a8b519d6d000069";

    public static final String AUTH_FANATIX_TOKEN = "5fd203caf74e219f585067338b5afae3";
}

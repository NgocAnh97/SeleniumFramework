package actions.commons;

import java.io.File;

public class GlobalConstants {
    public static final String DEV_USER_URL = "http://demo.nopcommerce.com/";
    public static final String STAGING_USER_URL = "http://staging.nopcommerce.com/";
    public static final String LIVE_USER_URL = "http://live.nopcommerce.com/";

    public static final String DEV_ADMIN_URL = "http://admin-demo.nopcommerce.com/";
    public static final String STAGING_ADMIN_URL = "http://admin-staging.nopcommerce.com/";
    public static final String LIVE_ADMIN_URL = "http://admin-live.nopcommerce.com/";

    public static final String ADMIN_USER = "admin@yourstore.com";
    public static final String ADMIN_PASSWORD = "admin";

    public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final String DASHBOARD_LINK = "Xpath=//img[@class='brand-image-xl logo-xl']";
    public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    //    public static final String PORTAL_PAGE_URL = "https://demo.guru99.com/";
    public static final String PORTAL_DEV_URL = "https://google.com";
    public static final String LOGOUT_LINK = "xpath=//a[text()='Logout']";

    public static final String OS_NAME = "MAC";
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String SEPARATOR = File.separator;
    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;

    public static class AdminLoginPageUI {
        public static final String EMAIL_TEXTBOX = "xpath=//input[contains(@id,'Email')]";
        public static final String PASSWORD_TEXTBOX = "xpath=//input[@id='Password']";
        public static final String LOGIN_BUTTON = "xpath=//button[contains(@class,'login-button')]";
        public static final String FIELD_VALIDATION_ERROR_MESSAGE = "xpath=//span[contains(@class,'field-validation-error')]";
        public static final String LOGIN_ERROR_MESSAGE = "xpath=//div[contains(@class,'message-error validation-summary-errors')]";
    }

    public static class AdminDashboardPageUI {
        public static final String HEADER_TITLE = "//head/title";
    }

    public static class HomePageUI {
        public static final String REGISTER_LINK = "xpath=//a[@class='ico-register']";
        public static final String LOGIN_LINK = "xpath=//a[@class='ico-login']";
        public static final String MY_ACCOUNT_LINK = "xpath=//a[contains(@class,'ico-account')]";
    }

    public static class LoginPageUI {
        public static final String EMAIL_TEXTBOX = "xpath=//input[contains(@id,'Email')]";
        public static final String PASSWORD_TEXTBOX = "id=Password";
        public static final String LOGIN_BUTTON = "xpath=//button[contains(@class,'login-button')]";
        public static final String FIELD_VALIDATION_ERROR_MESSAGE = "xpath=//span[contains(@class,'field-validation-error')]";
        public static final String LOGIN_ERROR_MESSAGE = "xpath=//div[contains(@class,'message-error validation-summary-errors')]";
    }

    public static class RegisterPageUI {
        public static final String FIRST_NAME_TEXTBOX = "id=FirstName";
        public static final String LAST_NAME_TEXTBOX = "id=LastName";
        public static final String EMAIL_TEXTBOX = "id=Email";
        public static final String PASSWORD_TEXTBOX = "xpath=//input[@id='Password']";
        public static final String CONFIRM_PASSWORD_TEXTBOX = "xpath=//input[@id='ConfirmPassword']";
        public static final String REGISTER_BUTTON = "xpath=//button[@id='register-button']";
        public static final String FIRST_NAME_ERROR_MESSAGE = "xpath=//span[@id='FirstName-error']";
        public static final String LAST_NAME_ERROR_MESSAGE = "xpath=//span[@id='LastName-error']";
        public static final String EMAIL_ERROR_MESSAGE = "xpath=//span[@id='Email-error']";
        public static final String PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='Password-error']";
        public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='ConfirmPassword-error']";
        public static final String EXISTING_EMAIL_ERROR_MESSAGE = "xpath=//div[contains(@class,'message-error')]//li";
        public static final String REGISTER_SUCCESS_MESSAGE = "xpath=//div[@class='result']";
        public static final String LOGOUT_LINK = "xpath=//a[contains(@class,'ico-logout')]";
    }

    public static class AccountPageUI {
        public static final String GENDER_FEMALE_CHECKBOX = "xpath=//span[@class='female']";
        public static final String FIRST_NAME_TEXTBOX = "xpath=//input[@id='FirstName']";
        public static final String LAST_NAME_TEXTBOX = "xpath=//input[@id='LastName']";
        public static final String DATE_SELECT = "xpath=//select[@name='DateOfBirthDay']";
        public static final String MONTH_SELECT = "xpath=//select[@name='DateOfBirthMonth']";
        public static final String YEAR_SELECT = "xpath=//select[@name='DateOfBirthYear']";
        public static final String EMAIL_TEXTBOX = "xpath=//input[@id='Email']";
        public static final String COMPANY_TEXTBOX = "xpath=//input[@id='Company']";
        public static final String SAVE_BUTTON = "xpath=//button[@id='save-info-button']";
        public static final String LOGOUT_LINK = "xpath=//a[@class='ico-logout']";
        public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";
        public static final String HEADER_TITLE = "xpath=//div[@class='page-title']/h1";
        public static final String CLOSE_SUCCESS_BUTTON = "xpath=//div[@class='bar-notification success']/span[@class='close']";
    }
}

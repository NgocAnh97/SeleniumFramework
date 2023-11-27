package actions.commons;

public class GlobalConstants {
    public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final String DASHBOARD_LINK = "//img[@class='brand-image-xl logo-xl']";
    public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String PORTAL_DEV_URL = "https://google.com";
    public static final String LOGOUT_LINK = "//a[text()='Logout']";

    public static class AdminLoginPageUI {
        public static final String EMAIL_TEXTBOX = "//input[contains(@id,'Email')]";
        public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
        public static final String LOGIN_BUTTON = "//button[contains(@class,'login-button')]";
        public static final String FIELD_VALIDATION_ERROR_MESSAGE = "//span[contains(@class,'field-validation-error')]";
        public static final String LOGIN_ERROR_MESSAGE = "//div[contains(@class,'message-error validation-summary-errors')]";
    }

    public static class AdminDashboardPageUI {
        public static final String HEADER_TITLE = "//head/title";
    }

    public static class HomePageUI {
        public static final String REGISTER_LINK = "//a[@class='ico-register']";
        public static final String LOGIN_LINK = "//a[@class='ico-login']";
        public static final String MY_ACCOUNT_LINK = "//a[contains(@class,'ico-account')]";
    }

    public static class LoginPageUI {
        public static final String EMAIL_TEXTBOX = "//input[contains(@id,'Email')]";
        public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
        public static final String LOGIN_BUTTON = "//button[contains(@class,'login-button')]";
        public static final String FIELD_VALIDATION_ERROR_MESSAGE = "//span[contains(@class,'field-validation-error')]";
        public static final String LOGIN_ERROR_MESSAGE = "//div[contains(@class,'message-error validation-summary-errors')]";
    }

    public static class RegisterPageUI {
        public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
        public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
        public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
        public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
        public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
        public static final String REGISTER_BUTTON = "//button[@id='register-button']";
        public static final String FIRST_NAME_ERROR_MESSAGE = "//span[@id='FirstName-error']";
        public static final String LAST_NAME_ERROR_MESSAGE = "//span[@id='LastName-error']";
        public static final String EMAIL_ERROR_MESSAGE = "//span[@id='Email-error']";
        public static final String PASSWORD_ERROR_MESSAGE = "//span[@id='Password-error']";
        public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "//span[@id='ConfirmPassword-error']";
        public static final String EXISTING_EMAIL_ERROR_MESSAGE = "//div[contains(@class,'message-error')]//li";
        public static final String REGISTER_SUCCESS_MESSAGE = "//div[@class='result']";
        public static final String LOGOUT_LINK = "//a[contains(@class,'ico-logout')]";
    }

    public static class AccountPageUI {
        public static final String GENDER_FEMALE_CHECKBOX = "//span[@class='female']";
        public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
        public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
        public static final String DATE_SELECT = "//select[@name='DateOfBirthDay']";
        public static final String MONTH_SELECT = "//select[@name='DateOfBirthMonth']";
        public static final String YEAR_SELECT = "//select[@name='DateOfBirthYear']";
        public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
        public static final String COMPANY_TEXTBOX = "//input[@id='Company']";
        public static final String SAVE_BUTTON = "//button[@id='save-info-button']";
        public static final String LOGOUT_LINK = "//a[@class='ico-logout']";
        public static final String MY_ACCOUNT_LINK = "//a[@class='ico-account']";
        public static final String HEADER_TITLE = "//div[@class='page-title']/h1";
        public static final String CLOSE_SUCCESS_BUTTON = "//div[@class='bar-notification success']/span[@class='close']";
    }
}

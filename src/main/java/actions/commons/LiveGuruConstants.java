package actions.commons;

public class LiveGuruConstants {
    public static class HomePageUI {
        public static final String MY_ACCOUNT_LINK = "//div[@class='footer']//a [@title='My Account']";
        public static final String REGISTER_LINK = "//a [@title='Create an Account']";
        public static final String LOGIN_LINK = "//button[@title='Login']";
    }

    public static class AccountPageUI {
        public static final String ACCOUNT_LINK = "//a[@class='skip-link skip-account']";
        public static final String LOGOUT_LINK = "//a[@title='Log Out']";
        public static final String REGISTER_SUCCESS_MESSAGE = "//li[@class='success-msg']";
    }

    public static class LoginPageUI {
        public static final String EMAIL_TEXTBOX = "//input[@id='email']";
        public static final String PASSWORD_TEXTBOX = "//input[@id='pass']";
        public static final String LOGIN_BUTTON = "//button[@title='Login']";
        public static final String REQUIRED_EMAIL_ERROR_MESSAGE = "//div[@id='advice-required-entry-email']";
        public static final String REQUIRED_PASSWORD_ERROR_MESSAGE = "//div[@id='advice-required-entry-pass']";
        public static final String INVALID_PASSWORD_ERROR_MESSAGE = "//div[@id='advice-validate-password-pass']";
        public static final String UNREGISTER_EMAIL_ERROR_MESSAGE = "//li[@class='error-msg']";
    }

    public static class RegisterPageUI {
        public static final String FIRST_NAME_TEXTBOX = "//input[@id='firstname']";
        public static final String LAST_NAME_TEXTBOX = "//input[@id='lastname']";
        public static final String EMAIL_TEXTBOX = "//input[@id='email_address']";
        public static final String PASSWORD_TEXTBOX = "//input[@id='password']";
        public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='confirmation']";
        public static final String REGISTER_BUTTON = "//button[@title='Register']";
        public static final String REQUIRED_FIRST_NAME_ERROR_MESSAGE = "//div[@id='advice-required-entry-firstname']";
        public static final String REQUIRED_LAST_NAME_ERROR_MESSAGE = "//div[@id='advice-required-entry-lastname']";
        public static final String REQUIRED_EMAIL_ERROR_MESSAGE = "//div[@id='advice-required-entry-email_address']";
        public static final String REQUIRED_PASSWORD_ERROR_MESSAGE = "//div[@id='advice-required-entry-password']";
        public static final String REQUIRED_CONFIRM_PASSWORD_ERROR_MESSAGE = "//div[@id='advice-required-entry-confirmation']";
        public static final String INVALID_PASSWORD_ERROR_MESSAGE = "//div[@id='advice-validate-password-password']";
        public static final String INVALID_CONFIRM_PASSWORD_ERROR_MESSAGE = "//div[@id='advice-validate-cpassword-confirmation']";
    }
}

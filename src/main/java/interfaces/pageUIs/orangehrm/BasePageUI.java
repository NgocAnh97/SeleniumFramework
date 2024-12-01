package interfaces.pageUIs.orangehrm;

public class BasePageUI {
    public static final String MENU_BY_PAGE_NAME = "Xpath=//ul[@class='oxd-main-menu']//a[string()='%s']";
    public static final String SUB_MENU_BY_SUB_PAGE_NAME = "xpath=//div[@class='oxd-topbar-body']//li[contains(string(),'%s')]";
    public static final String CHILD_MENU_BY_NAME = "xpath=//nav[@class='oxd-topbar-body-nav']//a[contains(string(),'%s')]";
    public static final String BUTTON_BY_TYPE = "xpath=//button[@type='%s' and contains(@class,'oxd-button--medium')]";
    public static final String TEXTBOX_BY_NAME = "xpath=//input[@name='%s']";
    public static final String LOADING_ICON = "css=div.oxd-loading-spinner";
}

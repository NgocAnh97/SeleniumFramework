package interfaces.pageUIs.hrm;

public class BasePageUI {
    public static final String MENU_BY_PAGE_NAME = "//ul[@class='oxd-main-menu']//a[string()='%s']";
    public static final String SUB_MENU_BY_SUB_PAGE_NAME = "//div[@class='oxd-topbar-body']//li[contains(string(),'%s')]";
    public static final String CHILD_MENU_BY_NAME = "//nav[@class='oxd-topbar-body-nav']//a[contains(string(),'%s')]";
    public static final String BUTTON_BY_TYPE = "//button[@type='%s' and contains(@class,'oxd-button--medium')]";
    public static final String TEXTBOX_BY_NAME = "//input[@name='%s']";
}

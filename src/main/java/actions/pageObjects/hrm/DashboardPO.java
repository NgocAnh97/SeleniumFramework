package actions.pageObjects.hrm;

import actions.commons.BasePage;
import actions.pageObjects.hrm.pim.EmployeePO;
import interfaces.pageUIs.orangehrm.DashboardPageUI;
import org.openqa.selenium.WebDriver;

public class DashboardPO extends BasePage {
    private WebDriver driver;

    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }


    public EmployeePO openEmployeeListPage() {
        return new EmployeePO(driver);
    }

    public EmployeePO clickToPIMPage() {
        waitForElementClickable(driver, DashboardPageUI.PIM_PAGE);
        clickToElement(driver, DashboardPageUI.PIM_PAGE);

//        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);
//        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);

        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getEmployeeListPage(driver);
    }
}

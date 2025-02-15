package actions.pageObjects.hrm.pim;

import actions.commons.BasePage;
import actions.pageObjects.hrm.PageGenerator;
import interfaces.pageUIs.orangehrm.DashboardPageUI;
import interfaces.pageUIs.orangehrm.pim.PersonaListTabsPageUI;
import org.openqa.selenium.WebDriver;

public class PersonalListTabs extends BasePage {
    protected WebDriver driver;

    public PersonalListTabs(WebDriver driver) {
        this.driver = driver;
    }

    public AddEmployeePO openAddEmployeePage() {
        waitForElementClickable(driver, DashboardPageUI.ADD_EMPLOYEE_PAGE);
        clickToElement(driver, DashboardPageUI.ADD_EMPLOYEE_PAGE);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getAddEmployeePage(driver);
    }

    public PersonalDetailPO openPersonalDetailPage() {
        waitForElementClickable(driver, PersonaListTabsPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, PersonaListTabsPageUI.PERSONAL_DETAIL_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getPersonalDetailPage(driver);
    }

    public PersonalDetailPO clickEditButton() {
        waitForElementClickable(driver, PersonaListTabsPageUI.EDIT_BUTTON);
        clickToElement(driver, PersonaListTabsPageUI.EDIT_BUTTON);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getPersonalDetailPage(driver);
    }

    public ContactDetailPO openContactDetailPage() {
        waitForElementClickable(driver, PersonaListTabsPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver, PersonaListTabsPageUI.CONTACT_DETAIL_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getContactDetailPage(driver);
    }

    public EmergencyContactPO openEmergencyContactPage() {
        waitForElementClickable(driver, PersonaListTabsPageUI.EMERGENCY_CONTACT_LINK);
        clickToElement(driver, PersonaListTabsPageUI.EMERGENCY_CONTACT_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getEmergencyContactPage(driver);
    }

    public DependentPO openDependentPage() {
        waitForElementClickable(driver, PersonaListTabsPageUI.DEPENDENT_LINK);
        clickToElement(driver, PersonaListTabsPageUI.DEPENDENT_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getDependentPage(driver);
    }
}

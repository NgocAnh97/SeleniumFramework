package actions.pageObjects.hrm.pim;

import actions.commons.BasePage;
import actions.pageObjects.hrm.PageGenerator;
import interfaces.pageUIs.orangehrm.pim.PersonaListTabsPageUI;
import org.openqa.selenium.WebDriver;

public class PersonalListTabs extends BasePage {
    protected WebDriver driver;

    public PersonalListTabs(WebDriver driver) {
        this.driver = driver;
    }

    public PersonalDetailPO openPersonalDetailPage(WebDriver driver) {
        waitForElementClickable(driver, PersonaListTabsPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, PersonaListTabsPageUI.PERSONAL_DETAIL_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getPersonalDetailPage(driver);
    }

    public ContactDetailPO openContactDetailPage(WebDriver driver) {
        waitForElementClickable(driver, PersonaListTabsPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver, PersonaListTabsPageUI.CONTACT_DETAIL_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getContactDetailPage(driver);
    }

    public EmergencyContactPO openEmergencyContactPage(WebDriver driver) {
        waitForElementClickable(driver, PersonaListTabsPageUI.EMERGENCY_CONTACT_LINK);
        clickToElement(driver, PersonaListTabsPageUI.EMERGENCY_CONTACT_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getEmergencyContactPage(driver);
    }

    public DependentPO openDependentPage(WebDriver driver) {
        waitForElementClickable(driver, PersonaListTabsPageUI.DEPENDENT_LINK);
        clickToElement(driver, PersonaListTabsPageUI.DEPENDENT_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getDependentPage(driver);
    }
}

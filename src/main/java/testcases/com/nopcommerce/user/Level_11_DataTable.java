package testcases.com.nopcommerce.user;

import actions.commons.BaseTest;
import actions.pageObjects.jquery.HomePO;
import actions.pageObjects.jquery.PageGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Level_11_DataTable extends BaseTest {
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserUrl(browserName, url);

        homePage = PageGenerator.getHomePage(driver);
    }

    //    @Test
    public void Table_01_Paging() {
        homePage.openPageByNumber("15");
        Assert.assertTrue(homePage.isPageNumberActive("15"));
        homePage.openPageByNumber("12");
        Assert.assertTrue(homePage.isPageNumberActive("12"));
        homePage.openPageByNumber("1");
        Assert.assertTrue(homePage.isPageNumberActive("1"));
    }

    //    @Test
    public void Table_02_Search_By_Header() {
        homePage.enterTextboxByHeaderName("Females", "384187");
        Assert.assertTrue(homePage.isRowDataValueDisplayed("384187", "Afghanistan", "407124", "791312"));
        homePage.refreshCurrentPage(driver);

        homePage.enterTextboxByHeaderName("Country", "AFRICA");
        Assert.assertTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.refreshCurrentPage(driver);

        homePage.enterTextboxByHeaderName("Total", "1580");
        Assert.assertTrue(homePage.isRowDataValueDisplayed("777", "Antigua and Barbuda", "803", "1580"));
        homePage.refreshCurrentPage(driver);
    }

    //    @Test
    public void Table_03_Edit_Delete() {
        homePage.enterTextboxByHeaderName("Country", "Armenia");
        homePage.editRowByCountryValue("Armenia");
        homePage.sleepInSeconds(2);
        homePage.refreshCurrentPage(driver);

        homePage.enterTextboxByHeaderName("Country", "Armenia");
        homePage.deleteRowByCountryValue("Armenia");
        homePage.sleepInSeconds(2);
        homePage.refreshCurrentPage(driver);
    }

    @Test
    public void Table_04_Action_By_Index() throws Exception {
        homePage.openPageURL(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/#google_vignette");

        homePage.clickToLoadDataButton();
        homePage.sleepInSeconds(2);
        homePage.enterToTextboxByIndex("6", "Company", "SM Entertainment");
        homePage.sleepInSeconds(2);
        homePage.enterToTextboxByIndex("2", "Contact Person", "Yoona");
        homePage.sleepInSeconds(2);
        homePage.enterToTextboxByIndex("5", "Order Placed", "1990");
        homePage.sleepInSeconds(2);

        homePage.selectToDropdownByIndex("5", "Country", "Taiwan");
        homePage.sleepInSeconds(2);
        homePage.selectToDropdownByIndex("1", "Country", "Hong Kong");
        homePage.sleepInSeconds(2);

        homePage.checkToCheckboxByIndex("6", "NPO?", true);
        homePage.sleepInSeconds(2);
        homePage.checkToCheckboxByIndex("6", "NPO?", false);
        homePage.sleepInSeconds(2);
        homePage.checkToCheckboxByIndex("1", "NPO?", false);
        homePage.sleepInSeconds(2);

        homePage.clickToIconByIndex("8", "Move Up");
        homePage.sleepInSeconds(2);
        homePage.clickToIconByIndex("2", "Remove Current Row");
        homePage.sleepInSeconds(2);
        homePage.clickToIconByIndex("4", "Insert Row Above");
        homePage.sleepInSeconds(2);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePO homePage;
}

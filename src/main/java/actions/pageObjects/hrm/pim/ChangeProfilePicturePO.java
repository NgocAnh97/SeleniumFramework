package actions.pageObjects.hrm.pim;

import actions.commons.GlobalConstants;
import interfaces.pageUIs.nopcommerce.BasePageUI;
import interfaces.pageUIs.orangehrm.pim.EmployeeListPageUI;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class ChangeProfilePicturePO extends PersonalListTabs {
    private final WebDriver driver;

    public ChangeProfilePicturePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isProfileAvatarUploadSuccess(Dimension beforeUpload) {
        sleepInSeconds(2);
        Dimension afterUpload = getAvatarSize();
        return !(beforeUpload.equals(afterUpload));
    }

    public Dimension getAvatarSize() {
        return getElementSize(driver, EmployeeListPageUI.EMPLOYEE_IMAGE);
    }


    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_PATH;
        StringBuilder fullFileName = new StringBuilder();
        for (String file : fileNames) {
            fullFileName.append(filePath).append(file).append("\n");
        }
        fullFileName = new StringBuilder(fullFileName.toString().trim());
        sendKeysToElementWithoutClear(driver, BasePageUI.UPLOAD_FILE_TYPE, fullFileName.toString());
        sleepInSeconds(1);
    }

    public void clickToSaveButtonAtProfileContainer() {
        waitForElementClickable(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PROFILE_CONTAINER);
        clickToElement(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PROFILE_CONTAINER);
    }
}

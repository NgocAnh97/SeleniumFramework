package interfaces.pageUIs.wordpress;

public class AdminPostDetailPageUI {
    public static final String POST_TITLE = "xpath=//h1[contains(@class,'editor-post-title') and text()='%s']";
    public static final String POST_BODY = "xpath=//p[contains(@class,'wp-block-paragraph') and text()='%s']";
}

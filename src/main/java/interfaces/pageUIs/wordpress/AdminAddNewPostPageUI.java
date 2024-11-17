package interfaces.pageUIs.wordpress;

public class AdminAddNewPostPageUI {
    public static final String HOME_BUTTON = "css=div.my-home-button";
    public static final String POSTS_MENU = "xpath=//ul[@role='presentation']//span[text()='Posts']";
    public static final String LOADED_IFRAME = "css=iframe[class='is-loaded']";
    public static final String EDITOR_IFRAME = "xpath=//iframe[@title='Editor canvas']";
    public static final String POST_TITLE_TEXTBOX = "css=h1.wp-block-post-title";
    public static final String POST_TITLE_SELECTED = "css=h1.editor-post-title span";
    public static final String POST_BODY_BUTTON = "css=p[class*='block-editor-default']";
    public static final String POST_BODY_TEXTBOX = "css=p[class*='block-editor-rich-text__editable']";
    public static final String PUBLISH_BUTTON = "css=button[class*='editor-post-publish-button']";
    public static final String POST_PUBLISH_POPUP = "css=div.components-snackbar-list__notice-container";
    public static final String POST_PUBLISH_MESSAGE = "xpath=//div[text()='%s']";
    public static final String VIEW_POST_BUTTON = "css=div.components-snackbar a.components-button";
    public static final String HOME_ICON = "css=a[aria-label='View Posts']";
}

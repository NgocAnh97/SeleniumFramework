package actions.helpers;

import java.io.File;

public class SystemsHelper {
    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }
}

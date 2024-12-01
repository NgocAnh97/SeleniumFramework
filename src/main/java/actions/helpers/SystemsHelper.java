package actions.helpers;

import java.io.File;

public class SystemsHelper {
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
}

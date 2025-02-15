package actions.helpers;

import actions.utilities.LogUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static String fileLink;
    private static FileInputStream file;
    private static final String propertyFilePath = "src/main/resources/configs.properties";

    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        files.add("src/main/resources/configs/configs.properties");
        files.add("src/main/resources/configs/data.properties");

        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                fileLink = SystemsHelper.getCurrentDir() + f;
                file = new FileInputStream(fileLink);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            LogUtils.info("Loaded all properties file.");
            return properties;

        } catch (IOException ioe) {
            return new Properties();
        }
    }

    public static void setFile(String relPropertiesFilePath) {
        properties = new Properties();
        try {
            fileLink = SystemsHelper.getCurrentDir() + relPropertiesFilePath;
            file = new FileInputStream(fileLink);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDefaultFile() {
        properties = new Properties();
        try {
            fileLink = SystemsHelper.getCurrentDir() + propertyFilePath;
            file = new FileInputStream(fileLink);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        String keyValue = null;
        try {
            if (file == null) {
                properties = new Properties();
                fileLink = SystemsHelper.getCurrentDir() + propertyFilePath;
                file = new FileInputStream(fileLink);
                properties.load(file);
                file.close();
            }
            keyValue = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return keyValue;
    }

    public static void setValue(String key, String keyValue) {
        fileLink = SystemsHelper.getCurrentDir() + propertyFilePath;
        try {
            FileOutputStream out;
            if (file == null) {
                properties = new Properties();
                file = new FileInputStream(fileLink);
                properties.load(file);
                file.close();
            }
            out = new FileOutputStream(fileLink);
            properties.setProperty(key, keyValue);
            properties.store(out, null);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

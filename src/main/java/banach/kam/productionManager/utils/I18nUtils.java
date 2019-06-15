package banach.kam.productionManager.utils;

public class I18nUtils {

    public static String getLabel(String key) {
        return ResourceProvider.getLocalizationBundle().getString(key);
    }
}

package banach.kam.productionManager.utils;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ResourceProvider {

    private static ResourceBundle i18n = ResourceBundle.getBundle("i18n.labels", new Locale("pl", "PL"));;

    public static ResourceBundle getLocalizationBundle() {
        return i18n;
    }

    public static void setLocale(Locale locale) {
        i18n = ResourceBundle.getBundle("i18n.labels", locale);
    }
}


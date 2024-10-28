package ru.mzpos.appmanager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import java.util.Arrays;
import java.util.HashMap;

import static com.codeborne.selenide.Selenide.*;

public class MyAppManager {
    private BaseObject baseObject;

    public MyAppManager() {
    }

    public void init() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.mzpo-s.ru/";
        Configuration.headless = false;
        Configuration.screenshots = true;
        Configuration.pageLoadStrategy = "eager";

        baseObject = new BaseObject(this);

        open(Configuration.baseUrl);
    }

    public void stop() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            localStorage().clear();
            clearBrowserCookies();
            closeWebDriver();
        }
    }

    public BaseObject base() {
        return baseObject;
    }
}

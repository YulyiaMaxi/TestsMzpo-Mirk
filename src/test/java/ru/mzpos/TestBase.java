package ru.mzpos;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import ru.mzpos.appmanager.MyAppManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    protected static final MyAppManager app
            = new MyAppManager();
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeTest
    @Step("Запускаем слушателя Allure")
    protected void globalLogs(final ITestContext context) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        logger.info("Cьют: " + context.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeClass
    @Step("Открываем браузер")
    protected void setUp(ITestContext context) {
        app.init();
        context.setAttribute("app", app);
    }

    @BeforeMethod
    @Step("Запускаем логи")
    protected void logTestStart(Method m, Object[] p) {
        logger.info("Тест " + m.getName() + " с параметрами " + Arrays.asList(p) + " запущен:-->");
    }

    @AfterClass(alwaysRun = true)
    @Step("Закрываем браузер")
    protected void tearDown() {
        app.stop();
    }
}
package ru.mzpos.appmanager;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.files.FileFilters;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class BaseObject {
    public MyAppManager app;

    public BaseObject(MyAppManager app) {
        this.app = app;
    }

    //*
    //* Получение и работа с selenide элементами и коллекциями
    //*
    @SafeVarargs
    @Step("Получить селенид элемент")
    public final SelenideElement getSelenideElement(String locator, Object... value) {
        return $(byXpath(new Formatter().format(locator, value).toString()));
    }

    @SafeVarargs
    @Step("Получить коллекцию селенид элементов")
    public final ElementsCollection getSelenideCollection(String locator, Object... value) {
        return $$(byXpath(new Formatter().format(locator, value).toString()));
    }

    @Step("Получить количество элементов в коллекции с ожидаемым размером")
    public int getCountOfCollection(ElementsCollection collection, int expectSize) {
        sleep(2000);
        return $$((Collection<? extends WebElement>) collection).shouldHave(CollectionCondition.size(expectSize)).size();
    }

    @Step("Получить количество элементов в коллекции без ожидаемого размера")
    public int getCountOfCollection(ElementsCollection collection) {
        return $$((Collection<? extends WebElement>) collection).size();
    }

    @Step("Получить индекс элемента коллекции по значению")
    public int getIndexOfElementInCollection(ElementsCollection locator, String value) {
        return locator.texts().indexOf(value);
    }

    @Step("Получить селенид элемент из коллекции по индексу {index}")
    public SelenideElement getSelenideElementFromCollection(ElementsCollection locator, int index) {
        return locator.get(index);
    }

    @SafeVarargs
    @Step("Получить селенид элемент из коллекции по индексу {index}")
    public final SelenideElement getSelenideElementFromCollection(String locator, int index, Object... value) {
        return getSelenideCollection(locator, value).get(index).shouldBe(visible);
    }

    //*
    //* Получение текста элемента.
    //*
    @Step("Получить значение атрибута {atr} элемента коллекции")
    public String getElementValueOfAttribute(ElementsCollection locator, String atr, int index) {
        return locator.get(index).getAttribute(atr);
    }

    @Step("Получить значение атрибута {atr} элемента")
    public String getElementValueOfAttribute(SelenideElement locator, String atr) {
        return locator.getAttribute(atr);
    }

    @Step("Получение текстовое значение элемента")
    public String getTextValueOfElement(SelenideElement locator) {
        locator.scrollTo();
        return locator.shouldBe(visible).getText().trim();
    }

    //*
    //* Проверки на доступность и недоступность
    //*
    @Step("Проверка элемента на доступность")
    public boolean isEnableElement(SelenideElement locator) {
        return locator.shouldBe(visible).is(enabled);
    }

    @Step("Проверка элемента на доступность")
    public boolean isEnableElement(String locator, String value) {
        return getSelenideElement(locator, value).shouldBe(visible).is(enabled);
    }

    @Step("Проверка элемента на редактируемость")
    public boolean isShouldEditableElement(SelenideElement locator) {
        return locator.isEnabled();
    }

    @Step("Проверка элемента на недоступность")
    public boolean isDisabledElement(SelenideElement locator) {
        return locator.shouldBe(visible).is(disabled);
    }

    //*
    //* Проверки на наличие и отсутствие
    //*
    @Step("Проверка элемента на наличие")
    public boolean isExistElement(SelenideElement locator) {
        return locator.exists();
    }

    @Step("Проверка элемента на наличие")
    public boolean isExistElement(String locator, String value) {
        return getSelenideElement(locator, value).exists();
    }

    @Step("Проверка элемента на наличие в коллекции")
    public boolean isElementExistInCollection(ElementsCollection locator, String value) {
        return locator.texts().contains(value);
    }

    @Step("Проверка элемента на наличие с заданным ожиданием в {wait} сек")
    public void isExistElementWithWait(SelenideElement locator, int wait) {
        try {
            locator.should(exist, Duration.ofSeconds(wait)).exists();
        } catch (Throwable ignored) {
        }
    }

    @Step("Проверка элемента на наличие с заданным ожиданием в {wait} сек")
    public boolean isExistElementWithWait(String locator, String value, int wait) {
        return getSelenideElement(locator, value).should(exist, Duration.ofSeconds(wait)).exists();
    }

    @Step("Проверка элемента на отсутствие с заданным ожиданием в {wait} сек")
    public boolean isNotExistElementWithWait(SelenideElement locator, int wait) {
        return locator.shouldNotBe(visible, Duration.ofSeconds(wait)).is(not(visible));
    }

    //*
    //* Проверки на содержание атрибута
    //*
    @Step("Проверить, что элемент коллекции содержит атрибут {atr}")
    public boolean isElementContainsAttribute(ElementsCollection locator, String atr, int index) {
        return locator.get(index).has(Condition.attribute(atr));
    }

    @Step("Проверить, что элемент содержит атрибут {atr}")
    public boolean isElementContainsAttribute(SelenideElement locator, String atr) {
        return locator.has(Condition.attribute(atr));
    }

    @Step("Проверить, что элемент содержит атрибут {atr}")
    public boolean isElementContainsAttribute(String locator, String atr) {
        return getSelenideElement(locator).has(Condition.attribute(atr));
    }

    @Step("Проверить, что элемент с именем содержит атрибут с ожидаемым значением")
    public boolean isElementHasExpectedAttributeValue(SelenideElement locator, String attrName, String value) {
        return locator.shouldHave(Condition.attribute(attrName, value)).isDisplayed();
    }

    @Step("Проверить, что элемент с именем {name} содержит атрибут {atr}")
    public boolean isElementContainsAttributeByName(String locator, String name, String atr) {
        return getSelenideElement(locator, name).has(Condition.attribute(atr));
    }

    @Step("Проверить, что элемент с именем {name} содержит атрибут {atr} со значением {value}")
    public boolean isElementContainsAttributeByNameAndValue(String locator, String name, String icon, String atr, String value) {
        return Objects.requireNonNull(getSelenideElement(locator, name, icon).getAttribute(atr)).contains(value);
    }

    @Step("Проверить, что элемент с именем {name} содержит атрибут {atr} со значением {value}")
    public boolean isElementContainsAttributeByNameAndValue(String locator, String name, String atr, String value) {
        return Objects.requireNonNull(getSelenideElement(locator, name).getAttribute(atr)).contains(value);
    }

    @Step("Проверить сортировку списка с значениями")
    public boolean isListSorted(List<String> list, boolean sortedByDESC) {
        if (!sortedByDESC) {
            if (list.get(0).chars().allMatch(Character::isDigit)) {
                return list.stream().sorted(Comparator.comparingInt(Integer::parseInt)).toList().equals(list);
            }
            return list.stream().sorted().toList().equals(list);
        } else {
            if (list.get(0).chars().allMatch(Character::isDigit)) {
                List<String> newList = list.stream().sorted(Comparator.comparingInt(Integer::parseInt)).collect(Collectors.toList());
                Collections.reverse(newList);
                return newList.equals(list);
            }
            return list.stream().sorted(Comparator.reverseOrder()).toList().equals(list);
        }
    }


    //*
    //* Проверки на содержание текста элементом.
    //*
    @Step("Проверить, что элемент содержит текст {str}")
    public boolean elementShouldHaveText(SelenideElement locator, String str) {
        return locator.is(text(str));
    }

    @Step("Проверить, что элемент содержит текст {str} как условие")
    public void elementShouldHaveTextAsCondition(SelenideElement locator, String str) {
        locator.shouldHave(text(str));
    }

    @Step("Проверка наличия текста в классе элемента")
    public boolean isElementClassNameHaveText(SelenideElement locator, String containsText) {
        return Arrays.asList(Objects.requireNonNull(locator.getAttribute("class")).split(" ")).contains(containsText);
    }

    @Step("Получить текстовое значение элемента из коллекции по индексу {index}")
    public String getTextValueElementFromCollection(ElementsCollection locator, int index) {
        ElementsCollection visibleElements = locator.filterBy(visible);
        return visibleElements.get(index).getText();
    }

    //*
    //* Клики и фокус на разные виды элементов
    //*
    @Step("Нажать на элемент")
    public void clickOnElement(SelenideElement locator) {
        locator.scrollIntoView("{block: 'center'}").shouldBe(enabled).click();
    }

    @Step("Нажать на элемент")
    public void clickOnElement(String locator, Object... value) {
        getSelenideElement(locator, value).scrollIntoView("{block: 'center'}").click();
    }

    @Step("Нажать на элемент с JS")
    public void clickOnElementWithJS(SelenideElement locator) {
        locator.scrollTo();
        locator.shouldBe(enabled).click(usingJavaScript());
    }

    @Step("Нажать на элемент с фокусом")
    public void clickOnElementWithHover(SelenideElement locator) {
        hoverOnElement(locator);
        locator.shouldBe(enabled).click();
    }

    @Step("Сделать фокус и нажать на элемент")
    public void hoverAndClickOnElement(SelenideElement locator) {
        locator.hover().click();
    }

    @Step("Сделать фокус на элемент")
    public void hoverOnElement(SelenideElement locator) {
        locator.scrollTo().hover();
    }

    @Step("Скролл к элементу с помощью JS")
    public void scrollToElementWithJS(SelenideElement locator) {
        executeJavaScript("arguments[0].scrollLeft = arguments[0].scrollWidth", locator);
    }

    @Step("Двойной клик на элемент")
    public void doubleClickOnElement(SelenideElement locator) {
        locator.shouldBe(enabled).doubleClick();
    }

    @Step("Клик правой кнопки мышки на элемент")
    public void clickRightButtonMouse(SelenideElement locator) {
        locator.shouldBe(enabled).contextClick();
    }

    @Step("Нажать на элемент по индексу")
    public void clickOnElementByIndex(ElementsCollection locator, int index) {
        locator.get(index).scrollTo().shouldBe(visible).click();
    }

    @Step("Нажать на элемент по индексу")
    public void clickOnElementByIndex(String locator, int index) {
        getSelenideCollection(locator).get(index).shouldBe(enabled, visible).click();
    }

    @Step("Нажать на элемент по индексу без видимости элемента")
    public void clickOnElementByIndexWithoutVisible(ElementsCollection locator, int index) {
        locator.get(index).shouldBe(enabled).click();
    }

    @Step("Скролл к элементу")
    public void scrollIntoViewElement(SelenideElement locator) {
        locator.scrollIntoView("{block: \"end\", inline: \"end\"}");
    }

    //*
    //* Работа с полем ввода
    //*
    @Step("Ввод текстового поля")
    public void inputField(SelenideElement locator, String str) {
        locator.hover().shouldBe(enabled).setValue(str);
    }

    @Step("Заполнить поле ввода")
    public void setValueInput(SelenideElement locator, String value) {
        locator.shouldBe(enabled).setValue(value);
    }

    @Step("Очистить и заполнить поле ввода")
    public void cleanAndSetValueInput(SelenideElement locator, String value) {
        locator.sendKeys(Keys.CONTROL + "a");
        locator.sendKeys(Keys.BACK_SPACE);
        locator.setValue(value);
    }

    @Step("Получение значения поля")
    public String getFieldValue(SelenideElement locator) {
        return locator.getValue();
    }

    @Step("Ввод текстового поля значением и нажатие Enter")
    public void inputFieldAndEnter(SelenideElement locator, String str) {
        locator.hover().shouldBe(enabled).setValue(str).pressEnter();
    }

    @Step("Ввод значения в текстовое поле с помощью Actions")
    public void setValueInputWithActions(SelenideElement locator, String value) {
        actions()
                .doubleClick(locator)
                .click(locator)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(value)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
    }

    @Step("Ввод значения в текстовое поле с помощью Actions")
    public void setValueInputWithoutEnter(SelenideElement locator, String value) {
        actions()
                .doubleClick(locator)
                .click(locator)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(value)
                .build()
                .perform();
    }

    @Step("Ввод значения в текстовое поле с помощью Actions и паузой между вводом символов")
    public void setValueInputWithPause(SelenideElement locator, String value) {
        this.isExistElementWithWait(locator, 10);
        locator.sendKeys(Keys.CONTROL + "a");
        locator.sendKeys(Keys.BACK_SPACE);
        actions().click(locator).perform();
        for (char c : value.toCharArray()) {
            actions().sendKeys(String.valueOf(c)).perform();
            sleep(100); // Пауза 100 миллисекунд между вводом символов
        }
    }

    @Step("Проверить сортировку списка с значениями")
    public boolean isListSortedNew(List<String> list, boolean sortedByDESC) {
        if (!sortedByDESC) {
            list.sort(null);
            return list.equals(list.stream().sorted().collect(Collectors.toList()));
        } else {
            list.sort(Collections.reverseOrder());
            return list.equals(list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        }
    }

    //*
    //* Таблицы
    //*
    @Step("Нажать на иконку в заголовке столбца таблицы")
    public void clickOnIconInHeaderColumn(SelenideElement element) {
        isExistElementWithWait(element, 15);
        hoverOnElement(element);
        clickOnElement(element);
    }

    @Step("Получить коллекцию селенид элементов столбца по индексу столбца {indexCol}")
    public ElementsCollection getSelenideCollection(String locator, int indexCol) {
        return $$(byXpath(String.format(locator, indexCol)));
    }

    @Step("Проверка строк при переходе через 10")
    public int checkNumberRow(int numberRow) {
        if (numberRow > 10) {
            return 1;
        } else {
            return numberRow;
        }
    }

    //*
    //* Работа с файлами
    //*
    @Step("Скачать файл с расширением {format}")
    public File downloadFile(SelenideElement locator, String format) {
        return locator.download(FileFilters.withExtension(format));
    }

    @Step("Загрузить файл {fileName}")
    public File uploadFile(SelenideElement locator, String fileName) {
        File file = new File(new Formatter().format("src/test/resources/data-files/%s", fileName).toString());
        executeJavaScript("arguments[0].value = '';", locator);
        return locator.scrollTo().shouldBe(enabled).uploadFile(file);
    }
}

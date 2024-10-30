package ru.mzpos.ui.pages; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.mzpo.forms.*;
import ru.mzpos.TestBase;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class MainPage extends TestBase {// проверяем формы заявок на главной странице

   @Name("Кнопка 'Обратная связь'")
    private final SelenideElement FeedBackButton = $("body > header > div.wrapper > div.header-bottom-line.d-flex.justify-content-between.align-items-center > div.btn-cb-bh-block > div > a > span");

    @Name("Селенид-значение полей формы Обратная связь")
    private final SelenideElement formNameField = $("#callBack > div.modal-body > div:nth-child(1) > div > input");
    private final SelenideElement formEmailField = $("#callBack > div.modal-body > div:nth-child(2) > div > input");
    private final SelenideElement formTelField = $("#callBack #intl-tel-phone");
    //  private final SelenideElement formMessageField = $("#callBack > div.modal-body > div:nth-child(4) > div > textarea");
    private final SelenideElement sendButton = $("#callBack > div.modal-footer > button");
    private final SelenideElement successWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-body.text-center.answer-modal > p");
    private final SelenideElement closeModalWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-header.border-0 > button > span");


    // Методы вставки input значений полей и отправка
    public void inputDataFeedBack() {
        FeedBackButton.hover();
        FeedBackButton.click();
        formNameField.setValue(FeedBack.getName());
        formEmailField.setValue(FeedBack.getEmail());
        formTelField.setValue(FeedBack.getTel());
        //  formMessageField.setValue(FeedBack.getMessage());
        // отправляем форму
        sendButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindow() {
        successWindow.shouldBe(visible, Duration.ofSeconds(15));
        closeModalWindow.click();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Name("Кнопка Заказать обратный звонок")
    private final SelenideElement CallBackButton = $("#buttonCallback > span");

    @Name("Селенид-значение полей формы Обратная связь")
    private final SelenideElement formCallBackNameField = $("#orderCall > div.modal-body > div:nth-child(1) > div > input");
    private final SelenideElement formCallBackTelField = $("#orderCall #intl-tel-phone");
    private final SelenideElement CallBackSendButton = $("#orderCall > div.modal-footer > button");
    private final SelenideElement CallBackSuccessWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-body.text-center.answer-modal > p");
    private final SelenideElement CallBackCloseModalWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-header.border-0 > button > span");

    // Методы вставки input значений полей и отправка
    public void inputDataCallBack() {
        CallBackButton.hover();
        CallBackButton.click();
        formCallBackNameField.setValue(CallBack.getName());
        formCallBackTelField.setValue(CallBack.getTel());
        // отправляем форму
        CallBackSendButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowCallBack() {
        CallBackSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        CallBackCloseModalWindow.click();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Name("Селенид-значение полей формы Напишите нам")
    private final SelenideElement Write2UsFormNameField = $("#footerForm > div:nth-child(1) > div.col-md-5.col-sm-5 > div:nth-child(1) > div > input");
    private final SelenideElement Write2UsEmailField = $("#footerForm > div:nth-child(1) > div.col-md-5.col-sm-5 > div:nth-child(2) > div > input");
    private final SelenideElement Write2UsFormTelField = $("#footerForm #intl-tel-phone");
    // private final SelenideElement Write2UsFormMessageField = $("#footerForm > div:nth-child(1) > div.col-md-7.col-sm-7.mt-xs-20 > textarea");

    private final SelenideElement Write2UsSendButton = $("#footerForm > div:nth-child(2) > div > button");
    private final SelenideElement Write2UsSuccessWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-body.text-center.answer-modal > p");
    private final SelenideElement Write2UsCloseModalWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-header.border-0 > button > span");
    //private final SelenideElement Write2UsSuccessWindow = $(byText("Ваша заявка принята. Скоро с вами свяжется наш менеджер")).parent().$("[class=\"modal-body text-center answer-modal\"]");
    //private final SelenideElement Write2UsCloseModalWindow =body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-header.border-0 > button > span

    // Методы вставки input значений полей и отправка
    public void inputDataWrite2Us() {
        $(byText("НАПИШИТЕ НАМ!")).scrollTo();
        Write2UsFormNameField.setValue(Write2Us.getName());
        Write2UsEmailField.setValue(Write2Us.getEmail());
        Write2UsFormTelField.setValue(Write2Us.getTel());

        // отправляем форму
        Write2UsSendButton.click();
    }

    // проверяем сообщение об отправке
    public void getWrite2UsModalWindow() {
        Write2UsSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        Write2UsCloseModalWindow.click();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Name("Кнопка Подарочный сертификат")
    private final SelenideElement MenuProfilesTab = $("body > div.main-menu.jlks > div > nav > ul > li:nth-child(3) > a"); // Меню Направления обучения
    private final SelenideElement GiftCertButton = $("body > div.main-menu.jlks > div > nav > ul > li.active > ul > li:nth-child(6) > a > span");// в меню раздел Подарочные сертификаты

    private final SelenideElement GiftCertFormNameField = $("#name");
    private final SelenideElement GiftCertFormTelField = $("#certs #intl-tel-phone");
    private final SelenideElement GiftCertFormEmailField = $("#certs > div:nth-child(3) > div > input");

    private final SelenideElement GiftCertSubmitButton = $("#certs > div.row.justify-content-center > div:nth-child(1) > button");
    private final SelenideElement GiftCertSuccessWindow = $("body > main > div.wrapper > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-body.text-center.answer-modal > p");
    private final SelenideElement GiftCertCloseModalWindow = $("body > main > div.wrapper > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-header.border-0 > button");

    @Step("Кнопка Подарочный сертификат")
    // Методы вставки input значений полей и отправка
    public void inputDataGiftCert() {
        // прописать путь к отрытию формы
        MenuProfilesTab.hover();// наводим курсор на Меню - Напрвления обучения
        MenuProfilesTab.click();// кликаем на Меню - Напрвления обучения

        GiftCertButton.hover();//наводим курсор на Подарочные сертификаты
        GiftCertButton.click(); // кликаем на Подарочные сертификаты
        // заполняем поля формы

        $(byText("Подари сертификат на обучение родным и близким!")).scrollTo();

        GiftCertFormNameField.setValue(GiftCert.getName());
        GiftCertFormEmailField.setValue(GiftCert.getEmail());
        GiftCertFormTelField.setValue(GiftCert.getTel());

        // отправляем форму
        GiftCertSubmitButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindowGiftCert() {
        GiftCertSuccessWindow.shouldBe(visible, Duration.ofSeconds(15));
        GiftCertCloseModalWindow.click();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Name("Форма Корзина") //тестируем корзину через покупку подарочного сертификата
    private final SelenideElement MenuProfilesTab1 = $("body > div.main-menu.jlks > div > nav > ul > li:nth-child(3) > a"); // Меню Направления обучения
    private final SelenideElement GiftCertButton1 = $("body > div.main-menu.jlks > div > nav > ul > li.active > ul > li:nth-child(6) > a > span");// в меню раздел Подарочные сертификаты

    private final SelenideElement GiftCertFormNameField1 = $("#name");
    private final SelenideElement GiftCertFormTelField1 = $("#certs #intl-tel-phone");
    private final SelenideElement GiftCertFormEmailField1 = $("#certs > div:nth-child(3) > div > input");

    private final SelenideElement BasketBuyButton = $("#certs > div.row.justify-content-center > div:nth-child(2) > button");
    private final SelenideElement BasketExecuteButton = $("body > main > div.wrapper > form > div:nth-child(2) > div.col-md-4.col-sm-6.mx-auto > div > div.text-center > a");// кнопка Перейти к оформлению
    private final SelenideElement BasketFormNameField = $("body > main > div.wrapper > div:nth-child(5) > div.col-md-8.pr-md-5.order-md-1.order-sm-1 > div > div > div:nth-child(2) > div > input");
    private final SelenideElement BasketFormTelField = $(By.name("order_phone"));
    private final SelenideElement BasketFormEmailField = $("body > main > div.wrapper > div:nth-child(5) > div.col-md-8.pr-md-5.order-md-1.order-sm-1 > div > div > div:nth-child(4) > div > input");
    private final SelenideElement BasketFormPayButton = $(byText("Оплатить")).parent().$("[class=\"btn btn-mzpo btn-lg\"]");
    private final SelenideElement PaymentPage = $("#root > div > div:nth-child(1) > div > div > div > div > div > div > a");

    @Step("Форма Корзина")
    // Методы вставки input значений полей и отправка
    public void inputDataBasket() {
        // прописать путь к отрытию формы
        MenuProfilesTab1.hover();// наводим курсор на Меню - Напрвления обучения
        MenuProfilesTab1.click();// кликаем на Меню - Напрвления обучения

        GiftCertButton1.hover();//наводим курсор на Подарочные сертификаты
        GiftCertButton1.click(); // кликаем на Подарочные сертификаты

        GiftCertFormNameField.setValue(Basket.getName()); //заполняем поля
        GiftCertFormEmailField.setValue(Basket.getEmail());
        GiftCertFormTelField.setValue(Basket.getTel());

        BasketBuyButton.hover();
        BasketBuyButton.click();

        BasketExecuteButton.hover(); //Переходим к оформлению
        BasketExecuteButton.click();


        BasketFormNameField.setValue(Basket.getName()); // заполняем поля формы
        BasketFormTelField.setValue(Basket.getTel());
        BasketFormEmailField.setValue(Basket.getEmail());

        // отправляем форму
        $(byText("Оплатить")).scrollTo();
        BasketFormPayButton.hover();
        BasketFormPayButton.click();
    }

    public void getPaymentPage() {
        PaymentPage.shouldBe(visible, Duration.ofSeconds(30));
        GiftCertCloseModalWindow.click();

    }
}

// появление страницы Юмани - это знак успеха операции






/* навести мышку на элемент */
   /* @Step("Навести на таб в хэдере {tabButton}")
   public void hoverOnTabButton(String tabButton) {
       app.base().hoverOnElement(app.base().getSelenideCollection(TAB_BUTTONS, tabButton).first());
    }

   /* @Step("Навести на кнопку Обратная связь")
    public void hoverOnTabButton(String FeedBackButton) {
        app.base().hoverOnElement(app.base().getSelenideCollection(TAB_BUTTONS, tabButton).first());*/
  /* @Step("Навести на кнопку Обратная связь")
   public void hoverOnFeedBackButton(String feedBackButton) {
       app.base().hoverOnElement(FeedBackButton);
   }
    @Step("Навести на кнопку Обратная связь")
    public void clickOnFeedBackButton(String feedBackButton) {
        app.base().hoverOnElement(FeedBackButton);
    }*/
//= $(".call").shouldHave(text("Обратная связь"));
package ru.mzpos.ui.pages; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import ru.mzpo.forms.CallBack;
import ru.mzpo.forms.FeedBack;
import ru.mzpos.TestBase;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class MainPage extends TestBase {// проверяем формы заявок на главной странице

    @Name("Кнопка 'Обратная связь'")
    private final SelenideElement FeedBackButton = $("body > header > div.wrapper > div.header-bottom-line.d-flex.justify-content-between.align-items-center > div.btn-cb-bh-block > div > a");

    @Name("Селенид-значение полей формы Обратная связь")
    private final SelenideElement formNameField = $("#callBack > div.modal-body > div:nth-child(1) > div > input");
    private final SelenideElement formEmailField = $("#callBack > div.modal-body > div:nth-child(2) > div > input");
    private final SelenideElement formTelField = $("#callBack #intl-tel-phone");
    private final SelenideElement formMessageField = $("#callBack > div.modal-body > div:nth-child(4) > div > textarea");
    private final SelenideElement sendButton = $("#callBack > div.modal-footer > button");
    private final SelenideElement successWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-body.text-center.answer-modal");
    private final SelenideElement closeModalWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-header.border-0 > button > span");

        // Методы вставки input значений полей и отправка
        public void inputDataFeedBack() {
        FeedBackButton.hover();
        FeedBackButton.click();
        formNameField.setValue(FeedBack.getName());
        formEmailField.setValue(FeedBack.getEmail());
        formTelField.setValue(FeedBack.getTel());
        formMessageField.setValue(FeedBack.getMessage());
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





}







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
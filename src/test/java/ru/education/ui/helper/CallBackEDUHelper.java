package ru.education.ui.helper; // страница для прописывания селекторов и методов (клики и наведения), селекторов полей и методов заполнения полей

import com.codeborne.selenide.SelenideElement;
import ru.education.TestBaseEDU;
import ru.mzpo.forms.*;
import ru.mzpos.TestBase;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


    public class CallBackEDUHelper extends TestBaseEDU {// проверяем формы заявок на главной странице

    @Name("Кнопка 'Заказать обратный звонок")
    private final SelenideElement FeedBackButtonEDU = $("#modalCallbackButton");

    @Name("Селенид-значение полей формы Обратная связь")
    private final SelenideElement formNameField = $("#callBack > div.modal-body > div:nth-child(1) > div > input");
    private final SelenideElement formEmailField = $("#callBack > div.modal-body > div:nth-child(2) > div > input");
    private final SelenideElement formTelField = $("#callBack #intl-tel-phone");
    private final SelenideElement sendButton = $("#callBack > div.modal-footer > button");
    private final SelenideElement successWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-body.text-center.answer-modal > p");
    private final SelenideElement closeModalWindow = $("body > main > div.modal.fade.bs-example-modal-sm.modal-answer.show > div > div > div.modal-header.border-0 > button > span");


    // Методы вставки input значений полей и отправка
    public void inputDataFeedBack() {
        CallBackEDUButton.hover();
        CallBackEDUButton.click();
        formNameField.setValue(FeedBack.getName());
        formEmailField.setValue(FeedBack.getEmail());
        formTelField.setValue(FeedBack.getTel());

        // отправляем форму
        sendButton.click();
    }

    // проверяем сообщение об отправке
    public void getModalWindow() {
        successWindow.shouldBe(visible, Duration.ofSeconds(15));
        closeModalWindow.click();
    }

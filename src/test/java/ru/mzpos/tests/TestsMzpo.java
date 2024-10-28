package ru.mzpos.tests;

import org.testng.annotations.Test;
import ru.mzpos.TestBase;
import ru.mzpos.ui.modals.CallBackModalWindow;
import ru.mzpos.ui.modals.FeedBackModalWindow;
import ru.mzpos.ui.pages.MainPage;


public class TestsMzpo extends TestBase {
    MainPage mainPage = new MainPage();


    @Test()
    public void FeedBackTest() {
        //mainPage.hoverOnFeedBackButton(FeedBackButton);
        // mainPage.clickOnTabButton(tabButton);
        FeedBackModalWindow modalWindow = new FeedBackModalWindow();
        String FeedBackButton = "Обратная связь";
        mainPage.inputDataFeedBack();
        mainPage.getModalWindow();
    }


     @Test()
    public void CallBackTest() {
        //mainPage.hoverOnFeedBackButton(FeedBackButton);
        // mainPage.clickOnTabButton(tabButton)
           CallBackModalWindow callBackModalWindow = new CallBackModalWindow();
           String CallBackButton = "Заказать звонок";
           mainPage.inputDataCallBack();
        mainPage.getModalWindowCallBack();
        }



}


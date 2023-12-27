package ru.netology.diploma.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.diploma.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement heading = $("h2.heading");
    private final SelenideElement byButton = $(byText("Купить"));
    private final SelenideElement byInCreditButton = $(byText("Купить в кредит"));
    private final SelenideElement headingBy = $(byText("Оплата по карте"));
    private final SelenideElement headingByInCredit = $(byText("Оплата по карте"));
    private final SelenideElement сardNumberField = $("[placeholder='0000 0000 0000 0000']");

    private final SelenideElement monthField = $("[placeholder='08']");

    private final SelenideElement yearField = $("[placeholder='22']");

    private final SelenideElement ownerField = $$("[class=input__inner]").findBy(text("Владелец")).$("[class=input__control]");

    private final SelenideElement CVCField = $("[placeholder='999']");

    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement successfulNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement unsuccessfulNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    private SelenideElement errorCardNumberField = $$("[class=input__inner]").findBy(text("Номер карты")).$(byText("Неверный формат"));
    private SelenideElement errorMonthField = $$("[class=input__inner]").findBy(text("Месяц")).$(byText("Неверный формат"));
    private SelenideElement periodErrorYearField = $$("[class=input__inner]").findBy(text("Год")).$(byText("Истёк срок действия карты"));
    private SelenideElement errorYearField = $$("[class=input__inner]").findBy(text("Год")).$(byText("Неверный формат"));
    private SelenideElement errorOwnerField = $$("[class=input__inner]").findBy(text("Владелец")).$(byText("Поле обязательно для заполнения"));

    private SelenideElement errorCVCField = $$("[class=input__inner]").findBy(text("CVC/CVV")).$(byText("Неверный формат"));
    public MainPage(){
        heading.shouldBe(Condition.visible);
    }

    public void verifyErrorNotification(String expectedText) {
        continueButton.click();
        unsuccessfulNotification.shouldHave(exactText(expectedText)).shouldBe(visible, Duration.ofSeconds(15));
    }
    public void verifySuccessfulNotification(String expectedText) {
        continueButton.click();
        successfulNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }
    public void notVerifySuccessfulNotification() {
        successfulNotification.shouldNotBe(visible);
    }
    public void chooseBy(String expectedText) {
        byButton.click();
        headingBy.shouldHave(exactText(expectedText)).shouldBe(visible);
    }
    public void chooseByInCredit(String expectedText) {
        byInCreditButton.click();
        headingByInCredit.shouldHave(exactText(expectedText)).shouldBe(visible);
    }
    public MainPage enteringApprovedCard () {
        сardNumberField.setValue(DataHelper.getApprovedCardNumber());
        return new MainPage();
    }
    public MainPage enteringInvalidCard () {
        сardNumberField.setValue("AAAA BBBB CCCC DDDD");
        return new MainPage();
    }
    public MainPage enteringValidCardValidityPeriod () {
        monthField.setValue(DataHelper.generateMonth(1));
        yearField.setValue(DataHelper.generateYear(1));
        return new MainPage();
    }
    public MainPage enteringInvalidCardValidityPeriod () {
        monthField.setValue(DataHelper.generateMonth(-1));
        yearField.setValue(DataHelper.generateYear(0));
        return new MainPage();
    }
    public MainPage enteringValidOwner() {
        ownerField.setValue(DataHelper.generateOwner("en"));
        return new MainPage();
    }
    public MainPage enteringInValidOwner() {
        ownerField.setValue(DataHelper.generateOwner("ru"));
        return new MainPage();
    }
    public MainPage enteringValidCVC() {
        CVCField.setValue(DataHelper.generateCVC());
        return new MainPage();
    }
    public MainPage enteringInValidCVC() {
        ownerField.setValue(DataHelper.generateInvalidCVC());
        return new MainPage();
    }
}

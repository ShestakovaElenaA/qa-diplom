package ru.netology.diploma.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.diploma.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement heading = $("h2.heading");
    private final SelenideElement byButton = $(byText("Купить"));
    private final SelenideElement byInCreditButton = $(byText("Купить в кредит"));
    private final SelenideElement headingBy = $(byText("Оплата по карте"));
    private final SelenideElement headingByInCredit = $("Кредит по данным карты");
    private final SelenideElement сardNumberField = $("0000 0000 0000 0000");

    private final SelenideElement monthField = $("08");

    private final SelenideElement yearField = $(byText("22"));

    private final SelenideElement ownerField = $("Владелец");

    private final SelenideElement CVCField = $("999");

    private final SelenideElement continueButton = $(byText("Продолжить"));

    private final SelenideElement successfulNotification = $(byText("Операция одобрена Банком"));
    private final SelenideElement unsuccessfulNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    public MainPage(){
        heading.shouldBe(Condition.visible);
    }

    public void verifyErrorNotification(String expectedText) {
        continueButton.click();
        unsuccessfulNotification.shouldHave(exactText(expectedText)).shouldBe(visible);
    }
    public void verifySuccessfulNotification(String expectedText) {
        continueButton.click();
        successfulNotification.shouldHave(exactText(expectedText)).shouldBe(visible);
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
    public MainPage enteringApprovedCard (DataHelper.CardNumber cardNumber) {
        сardNumberField.setValue(cardNumber.getApprovedCardNumber());
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
        ownerField.setValue(DataHelper.generateCVC());
        return new MainPage();
    }
    public MainPage enteringInValidCVC() {
        ownerField.setValue(DataHelper.generateInvalidCVC());
        return new MainPage();
    }
}

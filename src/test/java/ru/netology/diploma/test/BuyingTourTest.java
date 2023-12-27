package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.data.SQLHelper;
import ru.netology.diploma.page.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.diploma.data.SQLHelper.cleanDatabase;

public class BuyingTourTest {
    MainPage MainPage;
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("Allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setUp() { MainPage = open("http://localhost:8080/", MainPage.class);}
    @AfterEach
    void tearDownAllDatabase() {cleanDatabase();}

    @Test
    @DisplayName("Purchase of a tour when filling out the form with valid debit card data APPROVED")
    void shouldSuccessfulPurchaseOfTourWithValidApprovedDebitCard() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.enteringApprovedCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.verifySuccessfulNotification("Операция одобрена Банком.");
        var actualStatusLastLinePaymentRequestEntity = SQLHelper.getStatusLastLinePaymentRequestEntity();
        var expectedStatus = "APPROVED";
        assertEquals(actualStatusLastLinePaymentRequestEntity, expectedStatus);
        }
    @Test
    @DisplayName("The bank's refusal to purchase a tour using a debit card when filling out the form with valid DECLINED card data.")
    void shouldUnsuccessfulPurchaseOfTourWithValidDeclancedDebitCard() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.enteringDeclinedCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
        var actualStatusLastLinePaymentRequestEntity = SQLHelper.getStatusLastLinePaymentRequestEntity();
        var expectedStatus = "DECLINED";
        assertEquals(actualStatusLastLinePaymentRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("The bank's refusal to purchase a tour using a debit card when filling out the form with card data that is not registered in the system")
    void shouldUnsuccessfulPurchaseOfTourWithDebitCardNotRegisteredInSystem() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.enteringRandomCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
        }
    @Test
    @DisplayName("Successful purchase of a tour with payment on credit with valid APPROVED card.")
    void shouldSuccessfulPurchaseOfTourInCreditWithValidApprovedDebitCard() {
        MainPage.chooseByInCredit("Кредит по данным карты");
        MainPage.enteringApprovedCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.verifySuccessfulNotification("Операция одобрена Банком.");
        var actualStatusLastLineCreditRequestEntity = SQLHelper.getStatusLastLineCreditRequestEntity();
        var expectedStatus = "APPROVED";
        assertEquals(actualStatusLastLineCreditRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("The bank's refusal to purchase a tour on credit when filling out the form with valid DECLINED card data.")
    void shouldUnsuccessfulPurchaseOfTourInCreditWithValidDeclancedDebitCard() {
        MainPage.chooseByInCredit("Кредит по данным карты");
        MainPage.enteringDeclinedCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
        var actualStatusLastLineCreditRequestEntity = SQLHelper.getStatusLastLinePaymentRequestEntity();
        var expectedStatus = "DECLINED";
        assertEquals(actualStatusLastLineCreditRequestEntity, expectedStatus);
    }
    @Test
    @DisplayName("The bank's refusal to purchase a tour on credit when filling out the form with card data that is not registered in the system.")
    void shouldUnsuccessfulPurchaseOfTourInCreditWithDebitCardNotRegisteredInSystem() {
        MainPage.chooseByInCredit("Кредит по данным карты");
        MainPage.enteringRandomCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
        }
    @Test
    @DisplayName("Getting an error when submitting a form with letters in the card number.")
    void shouldReturnErrorWhenlettersInCardNumber() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.enteringInvalidCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.notSuccessfulNotification("Операция одобрена Банком.");
        MainPage.verifyErrorCardNumberField("Неверный формат");
    }
    @Test
    @DisplayName("Receiving an error when filling out the card payment form with expired card data.")
    void shouldReturnErrorWhenCardWithExpiredCardData() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.enteringApprovedCard();
        MainPage.enteringInvalidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringValidCVC();
        MainPage.notSuccessfulNotification("Операция одобрена Банком.");
        MainPage.verifyPeriodErrorYearField("Истёк срок действия карты");
    }
    @Test
    @DisplayName("Error when buying a tour with invalid cardholder data on the form.")
    void shouldReturnErrorWhenCardWithInvalidCardholder() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.enteringApprovedCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringInValidOwner();
        MainPage.enteringValidCVC();
        MainPage.notSuccessfulNotification("Операция одобрена Банком.");
        MainPage.verifyErrorOwnerField("Поле обязательно для заполнения");
    }
    @Test
    @DisplayName("Error occurred when submitting a completed form with invalid CVC/CVV data.")
    void shouldReturnErrorWhenCardWithInvalidCVC() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.enteringApprovedCard();
        MainPage.enteringValidCardValidityPeriod();
        MainPage.enteringValidOwner();
        MainPage.enteringInValidCVC();
        MainPage.notSuccessfulNotification("Операция одобрена Банком.");
        MainPage.verifyErrorCVCField("Неверный формат");
    }

    @Test
    @DisplayName("Receiving an error when sending an empty application form for the purchase of a tour.")
    void shouldReturnErrorWhenEmptyForm() {
        MainPage.chooseBy("Оплата по карте");
        MainPage.notSuccessfulNotification("Операция одобрена Банком.");
        MainPage.verifyErrorCardNumberField("Неверный формат");
        MainPage.verifyErrorMonthField("Неверный формат");
        MainPage.verifyErrorYearField("Неверный формат");
        MainPage.verifyErrorOwnerField("Поле обязательно для заполнения");
        MainPage.verifyErrorCVCField("Неверный формат");
    }

}

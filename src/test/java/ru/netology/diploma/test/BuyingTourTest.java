package ru.netology.diploma.test;

import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class BuyingTourTest {
    MainPage MainPage;
    @BeforeEach
    void setUp() { MainPage = open("http://localhost:8080/", MainPage.class);}

    @Test
    @DisplayName("Purchase of a tour when filling out the form with valid debit card data APPROVED")
    void shouldSuccessfulPurchaseOfTourWithValidApprovedDebitCard {
    }
    @Test
    @DisplayName("The bank's refusal to purchase a tour using a debit card when filling out the form with valid DECLINED card data.")
    void shouldUnsuccessfulPurchaseOfTourWithValidDeclancedDebitCard {
    }

    @Test
    @DisplayName("The bank's refusal to purchase a tour using a debit card when filling out the form with card data that is not registered in the system")
    void shouldUnsuccessfulPurchaseOfTourWithDebitCardNotRegisteredInSystem {
    }
    @Test
    @DisplayName("Successful purchase of a tour with payment on credit with valid APPROVED card.")
    void shouldSuccessfulPurchaseOfTourInCreditWithValidApprovedDebitCard {
    }
    @Test
    @DisplayName("The bank's refusal to purchase a tour on credit when filling out the form with valid DECLINED card data.")
    void shouldUnsuccessfulPurchaseOfTourInCreditWithValidDeclancedDebitCard {
    }
    @Test
    @DisplayName("The bank's refusal to purchase a tour on credit when filling out the form with card data that is not registered in the system.")
    void shouldUnsuccessfulPurchaseOfTourInCreditWithDebitCardNotRegisteredInSystem {
    }
    @Test
    @DisplayName("Getting an error when submitting a form with letters in the card number.")
    void shouldReturnErrorWhenlettersInCardNumber {
    }
    @Test
    @DisplayName("Receiving an error when filling out the card payment form with expired card data.")
    void shouldReturnErrorWhenCardWithExpiredCardData {
    }
    @Test
    @DisplayName("Error when buying a tour with invalid cardholder data on the form.")
    void shouldReturnErrorWhenCardWithInvalidCardholder {
    }
    @Test
    @DisplayName("Error occurred when submitting a completed form with invalid CVC/CVV data.")
    void shouldReturnErrorWhenCardWithInvalidCVC {
    }
    @Test
    @DisplayName("Receiving an error when sending an empty application form for the purchase of a tour.")
    void shouldReturnErrorWhenEmptyForm {
    }

}

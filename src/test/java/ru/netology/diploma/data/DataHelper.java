package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static CardNumber getApprovedCardNumber() {
        return new CardNumber("4444 4444 4444 4441");
    }

    public static CardNumber getDeclinedCardNumber() {
        return new CardNumber("4444 4444 4444 4442");
    }

    public static CardNumber getRandomCardNumber() {
        return new CardNumber(faker.business().creditCardNumber());
    }

    public static String generateMonth(int shift) {
        var date = LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
        return date;
    }

    public static String generateYear(int shift) {
        var date = LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
        return date;
    }

    public static String generateOwner(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generateCVC() {
        var faker = new Faker(new Locale("en"));
        return faker.numerify("###");
    }
    public static String generateInvalidCVC() {
        var faker = new Faker(new Locale("en"));
        return faker.numerify("##");
    }

    @Value
    public static class CardNumber {
        String cardNumber;
    }

    @Value
    public static class bankIdLastLineCreditRequestEntity {
        String bank_id;
    }

    @Value
    public static class statusLastLineCreditRequestEntity {
        String status;
    }

    @Value
    public static class transactionIdLastLinePaymentRequestEntity {
        String transaction_id;
    }

    @Value
    public static class statusLastLinePaymentRequestEntity {
        String status;
    }

    @Value
    public static class paymentIdLastLineOrderEntity {
        String payment_id;
    }

    @Value
    public static class countCreditRequestEntity {
        int countCredit;
    }
    @Value
    public static class countOrderEntity {
        int countOrder;
    }
    @Value
    public static class countPaymentEntity {
        int countPayment;
    }

}

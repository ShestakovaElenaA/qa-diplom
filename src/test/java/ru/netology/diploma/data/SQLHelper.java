package ru.netology.diploma.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper(){
    }
    private static Connection getCoon() throws SQLException {
        return DriverManager.getConnection(System.getProperty("jdbc:mysql://localhost:3306/app"),"app","pass");
    }
    @SneakyThrows
    public static DataHelper.bankIdLastLineCreditRequestEntity getBankIdLastLineCreditRequestEntity(){
        var codeSQL = "SELECT bank_id FROM credit_request_entity order by created DESC limit 1;";
        var conn = getCoon();
        var bank_id = runner.query(conn,codeSQL,new ScalarHandler<String>());
        return new DataHelper.bankIdLastLineCreditRequestEntity(bank_id);
    }
    @SneakyThrows
    public static DataHelper.statusLastLineCreditRequestEntity getStatusLastLineCreditRequestEntity(){
        var codeSQL = "SELECT bank_id FROM credit_request_entity order by created DESC limit 1;";
        var conn = getCoon();
        var status = runner.query(conn,codeSQL,new ScalarHandler<String>());
        return new DataHelper.statusLastLineCreditRequestEntity(status);
    }
    @SneakyThrows
    public static DataHelper.transactionIdLastLinePaymentRequestEntity getTransactionIdLastLinePaymentRequestEntity(){
        var codeSQL = "SELECT transaction_id FROM payment_entity order by created DESC limit 1;";
        var conn = getCoon();
        var transaction_id = runner.query(conn,codeSQL,new ScalarHandler<String>());
        return new DataHelper.transactionIdLastLinePaymentRequestEntity(transaction_id);
    }
    @SneakyThrows
    public static DataHelper.statusLastLinePaymentRequestEntity getStatusLastLinePaymentRequestEntity(){
        var codeSQL = "SELECT status FROM payment_entity order by created DESC limit 1;";
        var conn = getCoon();
        var status = runner.query(conn,codeSQL,new ScalarHandler<String>());
        return new DataHelper.statusLastLinePaymentRequestEntity(status);
    }
    @SneakyThrows
    public static DataHelper.paymentIdLastLineOrderEntity getPaymentIdLastLineOrderEntity(){
        var codeSQL = "SELECT payment_id  FROM order_entity order by created DESC limit 1;";
        var conn = getCoon();
        var payment_id = runner.query(conn,codeSQL,new ScalarHandler<String>());
        return new DataHelper.paymentIdLastLineOrderEntity(payment_id);
    }
    @SneakyThrows
    public static DataHelper.countCreditRequestEntity getCountCreditRequestEntity(){
        var codeSQL = "SELECT COUNT(*) FROM credit_request_entity;";
        var conn = getCoon();
        var countCredit = runner.query(conn,codeSQL,new ScalarHandler<Integer>());
        return new DataHelper.countCreditRequestEntity(countCredit);
    }
    @SneakyThrows
    public static DataHelper.countOrderEntity getCountOrderEntity(){
        var codeSQL = "SELECT COUNT(*) FROM order_entity;";
        var conn = getCoon();
        var countOrder = runner.query(conn,codeSQL,new ScalarHandler<Integer>());
        return new DataHelper.countOrderEntity(countOrder);
    }
    @SneakyThrows
    public static DataHelper.countPaymentEntity getCountPaymentEntity(){
        var codeSQL = "SELECT COUNT(*) FROM payment_entity;";
        var conn = getCoon();
        var countPayment = runner.query(conn,codeSQL,new ScalarHandler<Integer>());
        return new DataHelper.countPaymentEntity(countPayment);
    }
}

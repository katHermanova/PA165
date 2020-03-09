package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

//main class JavaConfig Spring application context example

public class MainJavaConfig {
    /*public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.scan("cz.muni.fi.pa165.currency");
        ctx.refresh();
        ExchangeRateTable exchangeRateTable = (ExchangeRateTableImpl)ctx.getBean("exchangeRateTable");
        //CurrencyConvertor currencyConvertor = (CurrencyConvertorImpl)ctx.getBean("currencyConvertor");

        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);

        Currency CZK = Currency.getInstance("CZK");
        Currency EUR = Currency.getInstance("EUR");

        System.out.println(currencyConvertor.convert(EUR, CZK, new BigDecimal("1")));
    }*/
    //from here https://www.codejava.net/frameworks/spring/spring-dependency-injection-example-with-annotations

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        ExchangeRateTable exchangeRateTable = ctx.getBean(ExchangeRateTable.class);
        //CurrencyConvertor currencyConvertor = ctx.getBean(CurrencyConvertorImpl.class);

        CurrencyConvertor currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);

        Currency CZK = Currency.getInstance("CZK");
        Currency EUR = Currency.getInstance("EUR");

        System.out.println(currencyConvertor.convert(EUR, CZK, new BigDecimal("1")));
    }
    //from here https://www.tutorialspoint.com/spring/spring_java_based_configuration.htm
}

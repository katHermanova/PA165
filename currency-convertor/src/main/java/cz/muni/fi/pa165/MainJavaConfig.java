package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainJavaConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.scan("cz.muni.fi.pa165.currency");
        ctx.refresh();
        ExchangeRateTable exchangeRateTable = (ExchangeRateTableImpl)ctx.getBean("exchangeRateTable");
        CurrencyConvertor currencyConvertor = (CurrencyConvertorImpl)ctx.getBean("currencyConvertor");

        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);

        Currency CZK = Currency.getInstance("CZK");
        Currency EUR = Currency.getInstance("EUR");

        System.out.println(currencyConvertor.convert(EUR, CZK, new BigDecimal("1")));
    }
    //from here https://www.codejava.net/frameworks/spring/spring-dependency-injection-example-with-annotations
}

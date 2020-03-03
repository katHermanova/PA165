package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.math.BigDecimal;
import java.util.Currency;

public class MainXml {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        ExchangeRateTable exchangeRateTable = ctx.getBean(ExchangeRateTableImpl.class);
        CurrencyConvertor currencyConvertor = ctx.getBean(CurrencyConvertorImpl.class);

        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);

        Currency CZK = Currency.getInstance("CZK");
        Currency EUR = Currency.getInstance("EUR");

        System.out.println(currencyConvertor.convert(EUR, CZK, new BigDecimal("1")));
    }
    //from here https://howtodoinjava.com/spring5/core/spring-bean-xml-config/ and here https://www.baeldung.com/spring-xml-injection
}

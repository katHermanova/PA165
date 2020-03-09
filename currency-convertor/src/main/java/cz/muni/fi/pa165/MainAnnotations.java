package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.math.BigDecimal;
import java.util.Currency;

//main class JSR-330 annotations example

public class MainAnnotations {
    /*public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(AppConfig.class);
        ctx.refresh();

        ExchangeRateTable exchangeRateTable = ctx.getBean(ExchangeRateTableImpl.class);
        CurrencyConvertor currencyConvertor = ctx.getBean(CurrencyConvertorImpl.class);

        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);

        Currency CZK = Currency.getInstance("CZK");
        Currency EUR = Currency.getInstance("EUR");

        System.out.println(currencyConvertor.convert(EUR, CZK, new BigDecimal("1")));
        ctx.close();
    }*/
    //from here http://www.javabyexamples.com/using-jsr-330-annotations-with-spring/ and here https://www.concretepage.com/spring/spring-named-and-inject-jsr-330-annotation-example

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-AutoScan.xml");

        ExchangeRateTable exchangeRateTable = ctx.getBean(ExchangeRateTable.class);
        //CurrencyConvertor currencyConvertor = ctx.getBean(CurrencyConvertorImpl.class);

        CurrencyConvertor currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);

        Currency CZK = Currency.getInstance("CZK");
        Currency EUR = Currency.getInstance("EUR");

        System.out.println(currencyConvertor.convert(EUR, CZK, new BigDecimal("1")));
    }
    //from here https://mkyong.com/spring3/spring-3-and-jsr-330-inject-and-named-example/
}

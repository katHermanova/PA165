package cz.muni.fi.pa165.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;


/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        logger.trace("convert({},{},{})",sourceCurrency, targetCurrency, sourceAmount);

        if (sourceCurrency == null || targetCurrency == null || sourceAmount == null) {
            throw new IllegalArgumentException("Invalid input argument");
        }

        try {
            if (exchangeRateTable.getExchangeRate(sourceCurrency,targetCurrency) == null) {
                logger.warn("Missing exchange rate.");
                throw new UnknownExchangeRateException("Exchange rate not found.");
            }
            return exchangeRateTable.getExchangeRate(sourceCurrency,targetCurrency).multiply(sourceAmount).setScale(2, RoundingMode.HALF_EVEN);
        } catch (ExternalServiceFailureException e) {
            logger.error("Error while trying to convert currency.");
            throw new UnknownExchangeRateException("Error while trying to convert currency.", e);
        }
    }

}

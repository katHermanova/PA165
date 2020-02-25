
package cz.muni.fi.pa165.currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConvertorImplTest {

    @Mock
    ExchangeRateTable exchangeRateTable;

    private CurrencyConvertor currencyConvertor;
    private Currency eur = Currency.getInstance(Locale.GERMANY);
    private Currency us = Currency.getInstance(Locale.US);

    @Before
    public void init() {
        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
    }

    @Test
    public void testConvert() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(eur,us)).thenReturn(new BigDecimal(1.08));

        assertEquals(new BigDecimal("1.08"), currencyConvertor.convert(eur, us, BigDecimal.ONE));
        assertEquals(new BigDecimal("1.09"), currencyConvertor.convert(eur, us, new BigDecimal("1.01")));
        assertEquals(new BigDecimal("1.10"), currencyConvertor.convert(eur, us, new BigDecimal("1.02")));
        assertEquals(new BigDecimal("1.11"), currencyConvertor.convert(eur, us, new BigDecimal("1.03")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceCurrency() {
        currencyConvertor.convert(eur, us, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullTargetCurrency() {
        currencyConvertor.convert(eur, null, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceAmount() {
        currencyConvertor.convert(null, us, BigDecimal.ONE);
    }

    @Test(expected = UnknownExchangeRateException.class)
    public void testConvertWithUnknownCurrency() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(eur, us))
                .thenReturn(null);
        currencyConvertor.convert(eur, us, BigDecimal.ONE);
    }

    @Test(expected = UnknownExchangeRateException.class)
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(eur, us))
                .thenThrow(UnknownExchangeRateException.class);
        currencyConvertor.convert(eur, us, BigDecimal.ONE);
    }

}

package cz.muni.fi.pa165.currency;

import org.junit.BeforeClass;
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
    private BigDecimal amount = new BigDecimal("1");

    @BeforeClass
    public void setUp() throws ExternalServiceFailureException {
        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
        when(exchangeRateTable.getExchangeRate(eur,us)).thenReturn(new BigDecimal(1.08));
    }

    @Test
    public void testConvert() {
        assertEquals(new BigDecimal(1.08), currencyConvertor.convert(eur, us, amount));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceCurrency() {
        currencyConvertor.convert(eur, us, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullTargetCurrency() {
        currencyConvertor.convert(eur, null, amount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceAmount() {
        currencyConvertor.convert(null, us, amount);
    }

    @Test(expected = UnknownExchangeRateException.class)
    public void testConvertWithUnknownCurrency() {
        currencyConvertor.convert(Currency.getInstance("UNKNOWN"), us, amount);
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
        fail("Test is not implemented yet.");
    }

}
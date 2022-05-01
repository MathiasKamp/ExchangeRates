package dk.zbc.exchangerates;

import java.util.ArrayList;

/**
 * Currency data access object
 * Is used to collect the currency rates from a given data source.
 * the classes that implements this interface has to write their own method to collect the currency rates
 */
public interface CurrencyDAO {

    /**
     * This method returns the currency rates based on a given currency base eg. USD
     * @param base the base currency name
     * @return list of Rate objects if the rates exists otherwise null
     */
     ArrayList<Rate> getRates(String base);
}

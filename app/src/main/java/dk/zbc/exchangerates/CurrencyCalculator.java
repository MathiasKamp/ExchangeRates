package dk.zbc.exchangerates;

import java.util.ArrayList;

/**
 * this class is presenting a currency calculator.
 * due to the api's free version we can only get the currencies in USD.
 * therefore this class is needed in order to convert the currencies to a given base currency.
 *
 */
public class CurrencyCalculator {


    public CurrencyCalculator(){

    }

    /**
     * this method returns the given Rate object i a list of currencies provided by the api
     * the method is private because its only getting used in the method calculateFromUsdToSelectedBaseCurrency
     * @param usdRates              : complete list of currencies with USD as base currency
     * @param selectedBaseCurrency  : base currency name provided by the user
     * @return Rate                 : the rate object of the baseName given
     */

    private Rate getTheChosenBaseCurrency(ArrayList<Rate> usdRates, String selectedBaseCurrency){

        Rate baseRate = null;

        if (usdRates != null) {

            for (Rate rate : usdRates){

                if (selectedBaseCurrency.equals(rate.getName())){

                    baseRate = rate;
                }
            }
        }
        return baseRate;
    }

    /**
     * this method calculates the currencies value based on the
     * @param usdRates              : list of rate objects provided by the api call.
     * @param baseCurrencyName      : the selected base currency's name, provided by the user
     * @param amountOfBaseCurrency  : the amount of base currency that the user wants to convert into other available currencies.
     * @return                      : the converted list of currencies based on the selected base currency and its amount.
     */

    public ArrayList<Rate> calculateFromUsdToSelectedBaseCurrency(ArrayList<Rate> usdRates,String baseCurrencyName, Double amountOfBaseCurrency){

        ArrayList<Rate> baseRates;

        // get the chosen Rate object based on the base currency name from user
        Rate baseRate = getTheChosenBaseCurrency(usdRates, baseCurrencyName);

        // calculate the amount of base currency spot rate for one USD
        baseRates = amountOfUsdForOneBaseCurrency(usdRates, baseRate);

        // calculate the currencies rates by multiplying the base rate with the amount of base currency
        baseRates = calculateCurrenciesValuesBasedOnBaseCurrency(baseRates,amountOfBaseCurrency);

        return baseRates;
    }

    /**
     * this method calculates the currencies values based on their spotRate and the amount
     * @param baseRates             : list of rates with their spot rate value calculated based on the base currency spot rate.
     * @param amountOfBaseCurrency  : the amount of base currency provided by the user
     * @return                      : the list of rates with each of the spot rates multiplied by the amount of base currency.
     */

    private ArrayList<Rate> calculateCurrenciesValuesBasedOnBaseCurrency(ArrayList<Rate> baseRates, Double amountOfBaseCurrency){

        if (!baseRates.isEmpty()){

            for (Rate rate : baseRates){

                // calculate the actual value of the rate by multiplying the spotRate of the rate with the amount
                double rateValue = (rate.getSpotRate() * amountOfBaseCurrency);

                // round the actual value to 5 digits
                double rateValueRounded = (double) Math.round(rateValue * 100000d) /100000d;

                // set the rate's spotRate to the new actual rate
                baseRates.get(baseRates.indexOf(rate)).setSpotRate(rateValueRounded);
            }
        }

        return baseRates;
    }

    /**
     * this method calculates the amount of USD currency that one currency is worth.
     * by dividing their spot rate with the spot rate of USD.
     * @param usdRates      : list of rate with their spot rate calculated based on the spot rate of USD
     * @param baseRate      : the base rate object that the user has chosen
     * @return              : list of rate with their spot rates divided by the usd spot rate
     */
    private ArrayList<Rate> amountOfUsdForOneBaseCurrency(ArrayList<Rate> usdRates, Rate baseRate){

        ArrayList<Rate> baseRates = new ArrayList<>();

        if (!usdRates.isEmpty() && baseRate != null){

            double baseRateInUsd = baseRate.getSpotRate();

            for (Rate rate : usdRates){

                double baseRateCurrencyPerOneBaseRate = (rate.getSpotRate() / baseRateInUsd);

                rate.setSpotRate(baseRateCurrencyPerOneBaseRate);
                baseRates.add(rate);
            }

        }
        return baseRates;
    }

}


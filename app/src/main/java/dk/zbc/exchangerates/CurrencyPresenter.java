package dk.zbc.exchangerates;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


import java.util.ArrayList;

/**
 * This class represents a currencyPresenter
 * its purpose is to collect and fill data in the Ui
 * therefore it also acts as a manager class for CurrencyDAO.
 */
public class CurrencyPresenter {

    public final CurrencyDAO CurrencyDAO;
    public final CurrencyCalculator currencyCalculator;
    private ArrayList<Rate> calculatedRates;
    private ArrayList<Rate> ratesCurrencyCodes;
    private final Context context;
    private String base;

    /**
     * Constructor
     * @param context   : the context
     * @param useMock   : if true CurrencyDAO will be instantiated as MockCurrency, otherwise FixerCurrency.
     */
    public CurrencyPresenter(Context context, boolean useMock){

        this.context = context;
        if (useMock){
            CurrencyDAO = new MockCurrency(context);
        }
        else{
            CurrencyDAO = new FixerCurrency(context);
        }
        currencyCalculator = new CurrencyCalculator();
        setRatesCurrencyCodes(CurrencyDAO.getRates(""));
        setCalculatedRates(ratesCurrencyCodes);
    }

    /**
     * This method sets the base currency name
     */
    public void setBase(String base){

        this.base = base;
    }

    /**
     * this method returns the base currency name
     * @return  : base currency name
     */
    public String getBase(){
        return this.base;
    }

    /**
     * this method is used to show the available currency codes in the Spinner on the Ui
     */
    public void presentCurrencyCodes(){

        Spinner SBaseCurrencyCodePicker = ((Activity) context).findViewById(R.id.S_CurrencyCodePicker);

        ArrayAdapter<Rate> currencyCodes = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, getRatesCurrencyCodes());
        currencyCodes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SBaseCurrencyCodePicker.setAdapter(currencyCodes);
    }

    /**
     * this method is used to show the currencies value
     * @param amount    : amount of base currency
     */
    public void presentCurrencies(Double amount){

        setRatesToPresentWithCalculatedCurrencies(amount);
        ListView lvCurrencies = ((Activity) context).findViewById(R.id.Lv_Currencies);
        RatesAdapter ratesAdapter = new RatesAdapter(context, 0, getCalculatedRates());
        lvCurrencies.setAdapter(ratesAdapter);
    }

    /**
     * This method is used to return the list of calculated rates
     * @return      : list of rates
     */
    public ArrayList<Rate> getCalculatedRates(){
        return this.calculatedRates;
    }

    /**
     * This method is used to set the list of calculated rates.
     * @param calculatedRates
     */
    public void setCalculatedRates(ArrayList<Rate> calculatedRates) {
        this.calculatedRates = calculatedRates;
    }

    /**
     * this method is used to set the calculated rates with a list of rates that are calculated with the amount provided by the user
     * @param amountOfBaseCurrency : amount of base currency
     */
    public void setRatesToPresentWithCalculatedCurrencies(Double amountOfBaseCurrency){
        setCalculatedRates(currencyCalculator.calculateFromUsdToSelectedBaseCurrency(getCalculatedRates(), getBase(), amountOfBaseCurrency));
    }

    /**
     * This method is used to return the list of rates that are beeing used to show the available currencies in the spinner
     * @return : list of rate
     */
    public ArrayList<Rate> getRatesCurrencyCodes() {
        return ratesCurrencyCodes;
    }

    /**
     * this method is used to set the list of currency code
     * @param ratesCurrencyCodes : list of currency codes
     */
    public void setRatesCurrencyCodes(ArrayList<Rate> ratesCurrencyCodes) {
        this.ratesCurrencyCodes = ratesCurrencyCodes;
    }
}
package dk.zbc.exchangerates;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    private EditText EtCurrencyAmount;
    private Spinner SCurrencyCodePicker;
    private CurrencyPresenter currencyPresenter;
    // this property is used to determine if currencies should come from the MockCurrency or FixerCurrency class.
    private boolean useMock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        useMock = true;
        EtCurrencyAmount = findViewById(R.id.Et_CurrencyAmount);
        EtCurrencyAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        SCurrencyCodePicker = findViewById(R.id.S_CurrencyCodePicker);
        Button btnCalculateCurrency = findViewById(R.id.Btn_Calculate);
        this.currencyPresenter = new CurrencyPresenter(this, useMock);

        // show the available currencies
        currencyPresenter.presentCurrencyCodes();

        // set a listener to the calculate button
        btnCalculateCurrency.setOnClickListener(View -> getCurrencies());

        // if the amount field is empty. don't show the calculate button
        if (EtCurrencyAmount.getText().toString().equals("")){
            btnCalculateCurrency.setEnabled(false);
        }

        // set a TextWatcher to the amount field. if the field is empty. don't show the calculate button
        EtCurrencyAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()==0){
                    btnCalculateCurrency.setEnabled(false);
                } else {
                    btnCalculateCurrency.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * this method is used to show the calculated currencies based on the base currency name and the amount provided by the user
     */
    public void getCurrencies() {

        double currencyAmount = Double.parseDouble(String.valueOf(EtCurrencyAmount.getText()));

        Rate potentialSelectedCurrencyCode = getSelectedCurrencyCode();

        if (currencyAmount > 0 && potentialSelectedCurrencyCode != null) {

            currencyPresenter.setBase(potentialSelectedCurrencyCode.getName());

            currencyPresenter.presentCurrencies(currencyAmount);
        }
    }

    /**
     * this method is used to collect which base currency name that the user has selected
     * @return  : rate of the base currency name
     */
    public Rate getSelectedCurrencyCode(){

        Rate selectedRate = (Rate) SCurrencyCodePicker.getSelectedItem();

        if (selectedRate != null){
            return selectedRate;
        }
        return null;
    }
}
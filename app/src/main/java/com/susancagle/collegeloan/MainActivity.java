package com.susancagle.collegeloan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    EditText loanInput;
    EditText interestInput;
    Button submitButton;
    TextView showFiveYear;
    TextView showTenYear;
    TextView showFifteenYear;

    double loanAmt, interestRate;
    private double calculation = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loanInput = findViewById(R.id.loan_amt);
        interestInput = findViewById(R.id.rate_amt);
        showFiveYear = findViewById(R.id.five_year_view);
        showTenYear = findViewById(R.id.ten_year_view);
        showFifteenYear = findViewById(R.id.fifteen_year_view);
        submitButton = findViewById(R.id.calculate_button);

    }

    public void calculateAmt(View view) {

       loanAmt = Double.parseDouble(loanInput.getText().toString());
       interestRate = Double.parseDouble(interestInput.getText().toString());



        double calculation = calculateMonths(60);
            String numberAsString = String.format("%.2f", calculation);
            showFiveYear.setText("Five Year Monthly Payment: $" + numberAsString);

        double calculation2 = calculateMonths(120);
        String numberAsString2 = String.format("%.2f", calculation2);
        showTenYear.setText("Ten Year Monthly Payment: $" + numberAsString2);

        double calculation3 = calculateMonths(180);
        String numberAsString3 = String.format("%.2f", calculation3);
        showFifteenYear.setText("Fifteen Year Monthly Payment: $" + numberAsString3);

        // Removes keyboard when button clicked.
        showFiveYear.onEditorAction(EditorInfo.IME_ACTION_DONE);

    }

    double calculateMonths(double months) {

        // (loan x interest) / 1-(1+ interest)^-n (- n is the negative number of periods
        // In this case interest is calculated monthly, so we
        // divide the interest rate percent by 100 to get the interest number.
        // then we divide that by 12 to obtain the interest rate per month.
        // then we use months in the "-n" which is number of periods.
        //
        //60, 120, 180 The year is pass through as a value
        double interest = (interestRate / 100) / 12;
        double var1 = 1 + interest;
        double var2 = Math.pow(var1, months);
        double var3 = 1 / var2;
        double denominator = 1 - (var3);
        double numerator = interest * loanAmt;

        calculation = numerator / denominator;
        return calculation;


    }
}

package com.example.carloancalculator

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        buttonCalculate.setOnClickListener{
            calculateLoan()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calculateLoan(){
        //TODO get all inputs and display outputs
        if(editTextCarPrice.text.isEmpty()){
            editTextCarPrice.setError(getString(R.string.error_input))
            return
        }
        if(editTextDownPayment.text.isEmpty()){
            editTextDownPayment.setError(getString(R.string.error_input))
            return
        }
        if(editTextLoanPeriod.text.isEmpty()){
            editTextLoanPeriod.setError(getString(R.string.error_input))
            return
        }
        if(editTextInterestRate.text.isEmpty()){
            editTextInterestRate.setError(getString(R.string.error_input))
            return
        }
        val carPrice = editTextCarPrice.text.toString().toFloat()
        val downPayment = editTextDownPayment.text.toString().toFloat()
        val loan = carPrice - downPayment

        val interestRate = editTextInterestRate.text.toString().toFloat()
        val loanPeriod = editTextLoanPeriod.text.toString().toFloat()
        val interest = loan*(interestRate/100)*loanPeriod

        val monthlyRepayment = (loan+interest)/loanPeriod/12


        textViewLoan.setText(getString(R.string.loan)+"${Currency.getInstance(Locale.getDefault()).getSymbol()}"+"${String.format("%.2f",loan)}")
        textViewInterest.setText(getString(R.string.interest)+"${Currency.getInstance(Locale.getDefault()).getSymbol()}"+"${String.format("%.2f",interest)}")
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment)+"${Currency.getInstance(Locale.getDefault()).getSymbol()}"+"${String.format("%.2f",monthlyRepayment)}")

        Toast.makeText(this,"Calculate Loan",Toast.LENGTH_SHORT).show()
    }

    fun reset(view: View){
        //TODO clear all inputs and outputs
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextLoanPeriod.setText("")
        editTextInterestRate.setText("")
        textViewLoan.setText(getString(R.string.loan))
        textViewInterest.setText(getString(R.string.interest))
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment))
        Toast.makeText(this,"Reset",Toast.LENGTH_SHORT).show()
    }
}

package com.example.insurancecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemClickListener,
    AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = spinnerAge.getItemAtPosition(position)
        Toast.makeText(this, "Selected Item = + $selectedItem", Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for the spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener {
            calculatePremium()
        }


    }

    private fun calculatePremium() {
        // Get the age group. Position of an array
        val age: Int = spinnerAge.selectedItemPosition
        var premium = when (age) {
            0 -> 60  //less than 17
            1 -> 70  //17 - 25
            2 -> 90  //26 - 30
            3 -> 120 //31 - 40
            else -> 150 //41 - <55
        }

        //Get the gender selection. ID of radio button
        val gender: Int = radioGroupGender.checkedRadioButtonId
        var premiumGender = 0
        if (gender == R.id.radioButtonMale) {
            //calculate premium for male
             premiumGender = when (age) {
                0 -> 50  //17 - 25
                1 -> 100  //26 - 30
                2 -> 150 //31 - 40
                else -> 200 //41 - <55
            }
        }
        else {
            //calculate premium for female

        }

        //Determine smoker or non-smoker
        val smoker: Boolean = checkBoxSmoker.isChecked
        var premiumSmoker = 0
        if (smoker) {
            //calculate premium for smoker
             premiumSmoker = when (age) {
                0 -> 100  //17 - 25
                1 -> 150  //26 - 30
                2 -> 200 //31 - 40
                3 -> 250 //41 - 55
                else -> 300 //<55
            }
        }

        val totalPremium = premium + premiumGender + premiumSmoker

        val symbol = Currency.getInstance(Locale.getDefault()).symbol
        textViewPremium.text = String.format("%s %d", symbol, totalPremium)
    }

    fun reset(view: View) {
        spinnerAge.setSelection(0)
        radioGroupGender.clearCheck()
        checkBoxSmoker.isChecked = false
        textViewPremium.text = getString(R.string.insurance_premium)
    }

}

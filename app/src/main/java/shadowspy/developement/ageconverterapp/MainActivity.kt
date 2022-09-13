package shadowspy.developement.ageconverterapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDateTextView : TextView? = null
    private var minutesPassedView : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectDateButton : Button = findViewById(R.id.buttonSelectDate)
        selectedDateTextView  = findViewById(R.id.txtSelectedDate)
        minutesPassedView = findViewById(R.id.txtMinutesPassed)

        selectDateButton.setOnClickListener{
            clickDatePicker()
        }



        }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {  view,selectedYear,selectedMonth,selectedDay ->

                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                selectedDateTextView?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val date = sdf.parse(selectedDate)

                date?.let{
                    val selectedDateInMinute = date.time / 60000 //Convert from milisecond to minutes , time calculated from 1970

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinute

                        minutesPassedView?.text = differenceInMinutes.toString()
                    }
                }
            },
            year,
            month,
            day
            )

            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()
    }
}
package com.doodle.my5plan

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import java.util.Calendar.YEAR
import java.util.Calendar.getInstance
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    var sYear = 0
    var sMonth = 0
    var sDay = 0
    var sHour = 0
    var sMin = 0
    var sSec = 0

    var timerTask : Timer? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFiveDate()
    }

    private fun getFiveDate() {
        val nowDate: Calendar = Calendar.getInstance()
        val fiveDate: Calendar = Calendar.getInstance()
        val DFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        fiveDate.add(YEAR, 5)
        var sNowDate = DFormat.format(fiveDate.time)
        var calcuDate = (fiveDate.time.time - nowDate.time.time) / (60 * 60 * 24 * 1000)


        textView_Count.text = calcuDate.toString()

//        nowDate.time = DFormat.format(nowDate)
//        textView_Count.text = nowDate.time.toString()
//        val fivelaterDate: Calendar = Calendar.getInstance()
//        fivelaterDate.add(YEAR,5)
//        Log.i("%%%", fivelaterDate.time.toString())


//        val sYear = nowDate.get(Calendar.YEAR).toString()
//        var sMonth = (nowDate.get(Calendar.MONTH) + 1).toString()
//        var sDate = nowDate.get(Calendar.DATE).toString()
//
//        if (sMonth.toInt() < 10) {
//            sMonth = "0$sMonth"
//        }
//
//        if (sDate.toInt() < 10) {
//            sDate = "0$sDate"
//        }



//        var sFiveDate: String = sYear + sMonth + sDate
//        Log.i("%%%", nowDate.get(Calendar.DAY_OF_YEAR).toString())
//        textView_Count.text = sFiveDate
    }


    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }




    fun CountTime() {
        sYear = 4
        sMonth = 11
        sDay = 31
        sHour = 23
        sMin = 59
        sSec = 59

        timerTask = timer(period = 1000) {
            sSec --
            if (sHour == 0 && sMin == 0 && sSec == 0) {
                cancel()
            }

            if (sMin == 0 && sSec == 0) {
                sHour --
                sMin = 59
                sSec = 59
            }

            if (sSec == 0) {
                sMin --
                sSec = 59
            }

            runOnUiThread {
                if (sHour == 0 && sMin == 0 && sSec == 0) {
                    textView_Count.text= "오늘은 여기까지 그럼 안녕"
                } else {
                    textView_Count.text= sHour.toString() + ":" + sMin.toString() + ":" + sSec.toString()
                }


            }
        }
    }


}
package weatherapp.project.com.weatherapp

import org.json.JSONObject
import java.io.Serializable
import kotlin.math.roundToInt

class CitiModel:Serializable{

    var citiName: String
    var date: String
    var temprature: String
    var info: String
    var min_temp: String
    var max_temp: String
    var humidity: String
    var state_abr: String




    constructor(str: String){
        var obj = JSONObject(str)
        citiName = obj.get("title") as String
        var cw = obj.getJSONArray("consolidated_weather")
        var obj2 = JSONObject(cw.get(1).toString())
        date = parseDate(obj2.get("applicable_date").toString())
        temprature = getFahrenheit(obj2.get("the_temp") as Double)
        info = obj2.get("weather_state_name") as String
        min_temp = getFahrenheit(obj2.get("min_temp") as Double)
        max_temp = getFahrenheit(obj2.get("max_temp") as Double)
        humidity = obj2.get("humidity").toString()+"%"
        state_abr = obj2.get("weather_state_abbr") as String
    }

    fun getFahrenheit(temp: Double): String{

        var far = temp*1.8
        far = far+32
        val intval = far.roundToInt()
        var str = intval.toString()
        str=str+" °F"
        return str

    }

    fun parseDate(date: String): String{
        /***************Works for API 26 and above
        val res_val = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)
        val res = res_val.dayOfMonth+", "+res_val.month+" "+res_val.year
         *********************/
        val res = "Tomorrow's forcast"

        return res
    }
}
package weatherapp.project.com.weatherapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import kotlin.math.roundToInt
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailsActivity : AppCompatActivity(){

    val TAG = "WEATHER_APP"
    companion object {
        fun newIntent(ctx: Context, model:CitiModel?): Intent {

            val intent1 = Intent(ctx, DetailsActivity::class.java)
            intent1.putExtra("obj", model)

            return intent1
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.details_activity)
        setSupportActionBar(toolbar)
        Log.e(TAG,"onCreate");



        var citiModel = intent.extras.get("obj") as CitiModel



        val citiName = findViewById(R.id.citiName) as TextView
        val date = findViewById(R.id.date) as TextView
        val image = findViewById(R.id.imageIcon) as ImageView
        val temp = findViewById(R.id.temprature) as TextView
        val info = findViewById(R.id.info) as TextView
        val minTemp = findViewById(R.id.minTemp) as TextView
        val maxTemp = findViewById(R.id.maxTemp) as TextView
        val hum = findViewById(R.id.humVal) as TextView

        citiName.text = citiModel.citiName

        date.text = citiModel.date
        temp.text = citiModel.temprature
        info.text = citiModel.info
        minTemp.text = citiModel.min_temp
        maxTemp.text = citiModel.max_temp
        hum.text = citiModel.humidity


        Picasso.get().load("https://www.metaweather.com/static/img/weather/png/64/"+citiModel.state_abr+".png").into(image)



    }


}
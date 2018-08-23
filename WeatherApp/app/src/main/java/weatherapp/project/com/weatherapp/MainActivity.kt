package weatherapp.project.com.weatherapp

import android.content.Intent
import android.opengl.Visibility
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var welcome: TextView
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    lateinit var listItems : ArrayList<String>
    public lateinit var myMap: HashMap<String,CitiModel>

    val OBJ = "obj"
    val TAG = "WEATHER_APP"

    val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.e("TAG","onCreate")


        listView = findViewById<ListView>(R.id.listView)
        welcome = findViewById(R.id.welcome)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)

        val listWoeid = arrayListOf<String>("890869","906057","2455920","44418","2459115","638242")


        myMap = HashMap()


        val adapter = ListItemAdapter(this, myMap)

        listView.adapter = adapter



        button.setOnClickListener { view ->


            for (str in listWoeid) {

                var str2 = getData().execute("https://www.metaweather.com/api/location/" + str + "/").get()
                val json_obj = JSONObject(str2)
                var citiModel = CitiModel(str2)
                myMap.put(json_obj.get("title") as String, citiModel)


            }

            welcome.visibility = View.INVISIBLE
            button.visibility = View.INVISIBLE
            listView.visibility = View.VISIBLE

        }







        listView.setOnItemClickListener { _, _, pos, _ ->

            var name: String
            name = ""
            if(pos==0){
               name = "Gothenburg"
            }
            else if(pos==1){
                name = "Stockholm"
            }
            else if(pos==2){
                name = "Mountain View"
            }
            else if(pos==3){
                name = "London"
            }
            else if(pos==4){
                name = "New York"
            }
            else if(pos==5){
                name = "Berlin"
            }


            val intent_val = DetailsActivity.newIntent(ctx, myMap.get(name))

            startActivity(intent_val)
            

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

    class getData() : AsyncTask<String, String, String>() {



        override fun onPreExecute() {
            super.onPreExecute()





        }
        override fun doInBackground(vararg args: String?): String {
            Log.e("WEATHER_APP","doInBackground")
            var connection: HttpURLConnection? = null
            try {


                var url = URL(args[0])

                connection = url.openConnection() as HttpURLConnection


                val inputStream = connection.inputStream

                val buffer = BufferedReader(InputStreamReader(inputStream))

                var text: String
                var result = ""

                text = buffer.readLine()

                result += text


                inputStream.close()


                return result
            } catch (exp: Exception) {
                Log.e("WEATHER_APP", "doInBackground : Exception "+exp)
            } finally {
                if (connection != null)
                    connection.disconnect()
            }

            return " "
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }



}

}



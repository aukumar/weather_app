package weatherapp.project.com.weatherapp

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject
import kotlin.math.roundToInt
import com.squareup.picasso.Picasso

class ListItemAdapter(private val ctx:Context,
                      private val listItems: HashMap<String,CitiModel>) : BaseAdapter(){

    private val inflater: LayoutInflater =
            ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
        }

    override fun getCount(): Int {
        return listItems.size
        }


    override fun getItem(pos: Int): CitiModel? {
        if(pos==0){
            return listItems.get("Gothenburg")
        }
        else if(pos==1){
            return listItems.get("Stockholm")
        }
        else if(pos==2){
            return listItems.get("Mountain View")
        }
        else if(pos==3){
            return listItems.get("London")
        }
        else if(pos==4){
            return listItems.get("New York")
        }
        else if(pos==5){
            return listItems.get("Berlin")
        }
        return null

         }


    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {

        val row = inflater.inflate(R.layout.list_item,parent,false)

        val citiName = row.findViewById(R.id.citiName) as TextView
        val temp = row.findViewById(R.id.temprature) as TextView
        val info = row.findViewById(R.id.info) as TextView
        val image = row.findViewById(R.id.icon) as ImageView





        var model = getItem(position) as CitiModel

        citiName.text = model.citiName


        temp.text = model.temprature
        info.text = model.info


        Picasso.get().load("https://www.metaweather.com/static/img/weather/png/64/"+model.state_abr+".png").into(image)


        return row
    }




}
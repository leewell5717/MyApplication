package liwei.com.other.kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import liwei.com.R

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.MyViewHolder>{

    private var context : Context
    private var items : ArrayList<MyData>

    constructor(context: Context,items: ArrayList<MyData>){
        this.context = context
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_forecast,parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder!!.city.text = items[position].city
        holder.weather.text = items[position].weather
        holder.temp.text = items[position].temp
    }


    class MyViewHolder : RecyclerView.ViewHolder{
        var weather : TextView
        var city : TextView
        var temp : TextView

        constructor(view: View) : super(view){
            weather = view.findViewById(R.id.weather) as TextView
            city = view.findViewById(R.id.city) as TextView
            temp = view.findViewById(R.id.temp) as TextView
        }
    }

}
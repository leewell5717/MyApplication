package liwei.com.other.kotlin

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_kotlin.*
import liwei.com.R

class KotlinActivity : Activity() {

    var items : ArrayList<MyData> = ArrayList<MyData>()
    var adapter : ForecastAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val data1 = MyData("成都","晴朗","23.5°")
        val data2 = MyData("北京","小雨","11.4°")
        val data3 = MyData("上海","多云","26°")
        val data4 = MyData("深圳","中雨","30°")
        val data5 = MyData("广州","晴朗","31°")
        val data6 = MyData("杭州","中雨","22°")
        val data7 = MyData("武汉","大雨","13°")
        val data8 = MyData("重庆","晴朗","28°")

        items.add(data1)
        items.add(data2)
        items.add(data3)
        items.add(data4)
        items.add(data5)
        items.add(data6)
        items.add(data7)
        items.add(data8)


        jisuan_btn.setOnClickListener({
            val num1 : Int = Integer.parseInt(et1?.text.toString().trim())
            val num2 : Int = Integer.parseInt(et2?.text.toString().trim())
            result?.text = "结果是："+addNum(num1,num2)
        })

        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)
        adapter = ForecastAdapter(this,items)
        forecastList.adapter = adapter
    }

    private fun addNum(num1 : Int,num2 : Int): Int = num1 + num2

}

package com.zhj.schoolmap

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_lxactivity.*

class ZGCActivity : AppCompatActivity() {
    private val loc = arrayOf("研究生公寓2", "研究生公寓一号楼", "研究生公寓三号楼", "教职工公寓2号", "南门入口", "三号学生公寓", "七食堂", "四号教工公寓", "南门出口", "九号教学楼", "一号学生公寓", "八号教学楼", "研究生教学楼", "图书馆", "锅炉房", "车辆实验室", "发动机实验室", "五号教学楼", "理工科技大厦", "四号教学楼", "七号教学楼", "逸夫楼", "篮球场", "足球场", "十一号学生公寓", "四五六食堂", "体育馆", "学生活动中心", "中心教学楼", "东门", "一号教学楼", "海淀科技大厦", "校史馆", "广播楼", "二号办公楼", "三号教学楼", "信息中心", "求是楼", "校医院", "精工餐厅", "延园餐厅", "一食堂", "二食堂", "三食堂", "东北门", "班车停靠点")
    private val xdata = arrayOf(33, 32, 26, 36, 38, 38, 38, 38, 38, 38, 33, 33, 34, 31, 35, 37, 37, 26, 36, 28, 30, 32, 32, 24, 32, 35, 21, 28, 24, 22, 19, 14, 15, 17, 17, 18, 14, 19, 16, 16, 16, 16, 16, 16, 10, 26)
    private val ydata = arrayOf(11, 18, 11, 15, 24, 32, 40, 49, 56, 61, 60, 72, 84, 105, 106, 102, 110, 114, 122, 86, 78, 68, 63, 67, 51, 43, 46, 46, 78, 120, 109, 118, 97, 93, 100, 88, 83, 78, 68, 50, 54, 42, 38, 46, 79, 24)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zgcactivity)
        val adapter_from: ArrayAdapter<*> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loc)
        val adapter_to: ArrayAdapter<*> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loc)
        val adapter_to_two: ArrayAdapter<*> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loc)

        list_from.setAdapter(adapter_from)
        //为ListView启动过滤
        list_from.setTextFilterEnabled(true)
        //设置SearchView自动缩小为图标
        search_from.setIconifiedByDefault(false)
        //设置该SearchView显示搜索按钮
        search_from.setSubmitButtonEnabled(true)
        //设置默认提示文字
        search_from.setQueryHint("输入起点")

        list_to.setAdapter(adapter_to)
        //为ListView启动过滤
        list_to.setTextFilterEnabled(true)
        //设置SearchView自动缩小为图标
        search_to.setIconifiedByDefault(false)
        //设置该SearchView显示搜索按钮
        search_to.setSubmitButtonEnabled(true)
        //设置默认提示文字
        search_to.setQueryHint("输入终点")

        list_to_two.setAdapter(adapter_to_two)
        //为ListView启动过滤
        list_to_two.setTextFilterEnabled(true)
        //设置SearchView自动缩小为图标
        search_to_two.setIconifiedByDefault(false)
        //设置该SearchView显示搜索按钮
        search_to_two.setSubmitButtonEnabled(true)
        //设置默认提示文字
        search_to_two.setQueryHint("输入终点2")

        var from = ""
        var to = ""
        var to_two = ""

        //配置监听器
        search_from.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            //点击搜索按钮时触发
            override fun onQueryTextSubmit(query: String): Boolean {
                //此处添加查询开始后的具体时间和方法
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //list_from.setFilterText(newText)
                from = newText.toString()
                //Toast.makeText(this@LXActivity, from, Toast.LENGTH_SHORT).show()
                adapter_from.getFilter().filter(newText.toString());
                return true
            }
        })
        list_from.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val string = adapter_from.getItem(position)
            search_from.setQuery(string.toString(), true)
        })
        //*******************************
        search_to.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            //点击搜索按钮时触发
            override fun onQueryTextSubmit(query: String): Boolean {
                //此处添加查询开始后的具体时间和方法
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //list_to.setFilterText(newText)
                to = newText.toString()
                //Toast.makeText(this@LXActivity, to, Toast.LENGTH_SHORT).show()
                adapter_to.getFilter().filter(newText.toString());
                return true
            }
        })
        list_to.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val string = adapter_to.getItem(position)
            search_to.setQuery(string.toString(), true)
        })
        //**********************************
        search_to_two.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            //点击搜索按钮时触发
            override fun onQueryTextSubmit(query: String): Boolean {
                //此处添加查询开始后的具体时间和方法
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //list_to.setFilterText(newText)
                to_two = newText.toString()
                //Toast.makeText(this@LXActivity, to, Toast.LENGTH_SHORT).show()
                adapter_to_two.getFilter().filter(newText.toString());
                return true
            }
        })
        list_to_two.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val string = adapter_to_two.getItem(position)
            search_to_two.setQuery(string.toString(), true)
        })


        button.setOnClickListener {
            if (from != "" && to != "" && to_two != "") {
                val index_from = loc.indexOf(from)
                val index_to = loc.indexOf(to)
                val index_to_two = loc.indexOf(to_two)

                var from_x = xdata[index_from]
                val from_y = ydata[index_from]

                val to_x = xdata[index_to]
                val to_y = ydata[index_to]

                val to_x_two = xdata[index_to_two]
                val to_y_two = ydata[index_to_two]

                val pref = getSharedPreferences("search", Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putInt("from_x", from_x)
                editor.putInt("from_y", from_y)
                editor.putInt("to_x", to_x)
                editor.putInt("to_y", to_y)
                editor.putInt("to_x_two", to_x_two)
                editor.putInt("to_y_two", to_y_two)
                editor.apply()

                val intent = Intent(this, ShowZhong2Activity::class.java)
                startActivity(intent)
            } else if (to_two == "") {
                val index_from = loc.indexOf(from)
                val index_to = loc.indexOf(to)

                var from_x = xdata[index_from]
                val from_y = ydata[index_from]

                val to_x = xdata[index_to]
                val to_y = ydata[index_to]

                val pref = getSharedPreferences("search", Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putInt("from_x", from_x)
                editor.putInt("from_y", from_y)
                editor.putInt("to_x", to_x)
                editor.putInt("to_y", to_y)
                editor.apply()

                val intent = Intent(this, ShowZhongActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "请选择起点和终点", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
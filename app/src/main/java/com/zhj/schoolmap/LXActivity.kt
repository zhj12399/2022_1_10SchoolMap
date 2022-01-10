package com.zhj.schoolmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_lxactivity.*


class LXActivity : AppCompatActivity() {
    private val loc = arrayOf(
        "北京理工大学良乡南校区宿舍楼至善园",
        "北京理工大学地面机动装备实验教学中心",
        "北京理工大学良乡校区南区食堂",
        "北京理工大学良乡校区北校区丹枫园学生公寓6号B号楼（宿舍楼）",
        "北京理工大学良乡南校区排球场",
        "北京理工大学良乡校区北校区丹枫园学生公寓6号C号楼（宿舍楼）",
        "北京理工大学良乡校区北校区丹枫园学生公寓6号A号楼（宿舍楼）",
        "北京理工大学良乡校区北校区留学生公寓（宿舍楼）",
        "北京理工大学良乡南校区篮球场",
        "北京理工大学良乡校区北校区槐泽B公寓楼（宿舍楼）",
        "北京理工大学良乡校区南校区疏桐园学生公寓A座（宿舍楼）",
        "先进结构技术研究院",
        "北京理工大学良乡校区南校区化学实验中心",
        "北京理工大学生物实验教学中心",
        "疏桐园B座（宿舍楼）",
        "北京理工大学良乡南校区基础化学教学实验中心",
        "北京理工大学良乡南校区分析测试中心",
        "北京理工大学基础化学教学实验中心",
        "北京理工大学良乡校区北校区槐泽A公寓楼（宿舍楼）",
        "北理工良乡校区游泳馆",
        "北京理工大学良乡校区南校区疏桐园学生公寓C座（宿舍楼）",
        "北京理工大学良乡校区北校区篮球场",
        "北京理工大学文化体育中心",
        "北京理工大学良乡校区南校区疏桐园E（宿舍楼）",
        "北京理工大学物理教学实验中心",
        "理工大学良乡校区-理学楼",
        "北京理工大学良乡南校区足球场",
        "北京理工大学良乡校区东校区甘棠园C座（宿舍楼）",
        "国家电网汽车充电站",
        "北京理工大学原子分子簇科学重点实验室",
        "理工大学邮政所",
        "北京理工大学良乡校区北校区静园A（宿舍楼）",
        "北京理工大学良乡南校区电工电子教学实验中心",
        "美联福生活超市(理工大学店)",
        "北京理工大学良乡校区北校区静园B（宿舍楼）",
        "学生社团文化广场",
        "北京理工大学良乡校区南校区理科教学楼",
        "北京理工大学良乡校区北校区-锅炉房",
        "北京理工大学良乡校区北校区(南门)",
        "北京理工大学良乡校区北校区食堂",
        "北京理工大学良乡校区东校区(西门)",
        "学生服务中心",
        "北京理工大学良乡校区北校区行政办公楼",
        "理工大学复印店",
        "北京理工大学良乡校区北区(东门)",
        "北京理工大学良乡校区北校区演艺厅",
        "北京理工大学良乡校区北校区静园学生公寓C号楼（宿舍楼）",
        "北京理工大学良乡校区北校区静园学生公寓D号楼（宿舍楼）",
        "北京理工大学良乡校区北校区综合教学大楼A（宿舍楼）",
        "北京理工大学良乡校区班车停靠站",
        "北京理工大学良乡校区北校区-锅炉房",
        "北京理工大学良乡校区北校区校医院",
        "北京理工大学良乡校区北校区心理与社会工作实验室",
        "北京理工大学良乡校区北校区心理健康教育与咨询中心",
        "北京理工大学(良乡校区北区)",
        "宿舍楼甘棠B",
        "宿舍楼甘棠A",
        "宿舍楼甘棠D",
        "综合教学楼B",
        "打印店丹枫A",
        "打印店丹枫B",
        "打印店丹枫C",
        "打印店槐泽A",
        "打印店槐泽B",
        "打印店静园A",
        "打印店静园B",
        "打印店静园C",
        "打印店静园D",
        "打印店综教A",
        "打印店图书馆",
        "打印店理教",
        "学生服务中心打印店",
        "打印店理学楼",
        "打印店疏桐A",
        "打印店疏桐B",
        "打印店疏桐C",
        "打印店疏桐D",
        "打印店疏桐E",
        "打印店甘棠A",
        "打印店甘棠B",
        "打印店甘棠C",
        "打印店甘棠D",
        "打印店文教北楼",
        "打印店文教南楼",
        "打印店生态楼"
    )

    private val xdata = arrayOf(
        52,
        43,
        48,
        6,
        46,
        6,
        7,
        6,
        43,
        5,
        41,
        43,
        43,
        43,
        40,
        43,
        43,
        43,
        7,
        30,
        38,
        9,
        30,
        35,
        39,
        39,
        37,
        13,
        39,
        43,
        3,
        3,
        43,
        26,
        28,
        30,
        35,
        21,
        31,
        30,
        20,
        26,
        26,
        26,
        20,
        28,
        23,
        22,
        22,
        43,
        21,
        23,
        23,
        23,
        31,
        17,
        17,
        13,
        24,
        7,
        6,
        6,
        7,
        5,
        30,
        29,
        23,
        22,
        22,
        29,
        35,
        26,
        39,
        41,
        40,
        38,
        36,
        35,
        17,
        17,
        13,
        13,
        21,
        30,
        53
    )

    private val ydata = arrayOf(
        7,
        28,
        7,
        7,
        19,
        10,
        5,
        23,
        16,
        30,
        7,
        28,
        21,
        21,
        7,
        21,
        21,
        21,
        30,
        57,
        7,
        26,
        57,
        7,
        21,
        28,
        19,
        41,
        34,
        21,
        28,
        19,
        21,
        6,
        9,
        13,
        25,
        10,
        19,
        16,
        37,
        6,
        27,
        6,
        35,
        33,
        9,
        6,
        21,
        20,
        10,
        16,
        18,
        18,
        19,
        41,
        47,
        47,
        28,
        6,
        6,
        10,
        30,
        30,
        9,
        6,
        9,
        7,
        21,
        21,
        24,
        6,
        28,
        7,
        7,
        7,
        7,
        7,
        47,
        41,
        41,
        46,
        44,
        44,
        20
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lxactivity)

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
        list_from.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
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
        list_to.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
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
        list_to_two.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
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

                val intent = Intent(this, ShowLiang2Activity::class.java)
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

                val intent = Intent(this, ShowLiangActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "请选择起点和终点", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


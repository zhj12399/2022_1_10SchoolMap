package com.zhj.schoolmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_liang.*
import java.io.InputStreamReader
import kotlin.math.abs


class ShowLiangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_liang)

        //初始信息
        val pref = this.getSharedPreferences("search", MODE_PRIVATE)
        val from_x = pref.getInt("from_x", 0)
        val from_y = pref.getInt("from_y", 0)
        val to_x = pref.getInt("to_x", 0)
        val to_y = pref.getInt("to_y", 0)
//        val from_x = 53
//        val from_y = 1
//        val to_x = 8
//        val to_y = 27

        //读入地图
        val liang_res = resources.openRawResource(R.raw.liangxiang)
        val bufReader = InputStreamReader(liang_res, "utf8")
        var liang_map = emptyArray<Array<Int>>()
        bufReader.useLines { lines ->
            for (line in lines) {
                var row = emptyArray<Int>()
                for (i in line) {
                    row = row.plus(i.toInt() - 48)
                }
                liang_map = liang_map.plus(row)
            }
        }

        //算法开始
        var open_list = mutableListOf(mutableListOf(from_x, from_y, 0))
        var close_list = mutableListOf<List<Int>>()
        val i_range = liang_map.size
        val j_range = liang_map[0].size
        var num = 0
        var R_list = mutableListOf<List<Int>>()
        var path = mutableListOf<List<Int>>()
        while (true) {
            var F_list = mutableListOf<Int>()
            for (point in open_list) {
                val H = abs(point[0] - to_x) + abs(point[1] - to_y)
                F_list.add(H + point[2])
            }

            val Fmin_point_index = F_list.indexOf(F_list.minOrNull())
            val Fmin_point = open_list[Fmin_point_index]
            open_list.removeAt(Fmin_point_index)
            close_list.add(mutableListOf(Fmin_point[0], Fmin_point[1]))
            for (i in (Fmin_point[0] - 1)..(Fmin_point[0] + 1)) {
                for (j in (Fmin_point[1] - 1)..(Fmin_point[1] + 1)) {
                    //Log.i("show", "$i-$j " + (i in 0 until i_range) + (j in 0 until j_range))
                    if (i in 0 until i_range && j in 0 until j_range) {
                        if (liang_map[i][j] == 1 && !close_list.contains(listOf(i, j))) {//是路
                            var tmp_point = listOf<Int>()
                            if (abs(i - Fmin_point[0]) + abs(j - Fmin_point[1]) == 2) {
                                tmp_point = listOf(i, j, Fmin_point[2] + 14)
                            } else if (abs(i - Fmin_point[0]) + abs(j - Fmin_point[1]) == 1) {
                                tmp_point = listOf(i, j, Fmin_point[2] + 10)
                            }

                            if (tmp_point.isNotEmpty()) {
                                var flag = true
                                for (open_list_item in open_list) {
                                    if (tmp_point[0] == open_list_item[0] && tmp_point[1] == open_list_item[1]) {
                                        if (tmp_point[2] < open_list_item[2]) {//新点距离小于老点
                                            open_list.removeAt(open_list.indexOf(open_list_item))
                                            open_list.add(tmp_point as MutableList<Int>)
                                            R_list.add(listOf(Fmin_point[0], Fmin_point[1], i, j))
                                            flag = false
                                            break
                                        } else {
                                            flag = false
                                        }
                                    }
                                }

                                if (flag) {
                                    open_list.add(tmp_point as MutableList<Int>)
                                    R_list.add(listOf(Fmin_point[0], Fmin_point[1], i, j))
                                }
                            }
                        }
                    }
                }
            }

            var end_flag = false
            for (open_list_item in open_list) {
                if (open_list_item[0] == to_x && open_list_item[1] == to_y) {
                    end_flag = true
                }
            }
            if (end_flag) {
                var tmp_point = mutableListOf(to_x, to_y)
                path.add(listOf(to_x,to_y))
                while (true) {
                    for (item in R_list) {
                        if (item[2] == tmp_point[0] && item[3] == tmp_point[1]) {
                            path.add(listOf(item[0], item[1]))
                            tmp_point[0] = item[0]
                            tmp_point[1] = item[1]
                        }
                    }
                    if (tmp_point[0] == from_x && tmp_point[1] == from_y) {
                        break
                    }
                }
                Log.i("show", path.toString())
                break
            }
            num += 1
        }


        //Toast.makeText(this, "$from_x-$from_y $to_x-$to_y" ,Toast.LENGTH_SHORT).show()
        val pic = resources.getIdentifier("liang", "drawable", packageName)
        val res = BitmapFactory.decodeResource(resources, pic)
        val new = Bitmap.createBitmap(res.width, res.height, Bitmap.Config.ARGB_8888)
        for (i in 0 until new.width) {
            for (j in 0 until new.height) {
                val color = res.getColor(i, j).toArgb()
                new.setPixel(i, j, color)
            }
        }
        for (point in path){
            val point_x = (170+13.5*point[1]).toInt()
            val point_y = 160+28*point[0]

            for(i in point_x..(point_x+10)){
                for (j in point_y..(point_y+10)){
                    new.setPixel(i, j, Color.argb(255,255,0,0))
                }
            }
        }
        Log.i("show","${new.width} - ${new.height}")
        //i170 j170
        //i1095 1660
        //chazhii 925 1490
        //每一格占（16.8）21
        //yuantu 55 71

        imageView.setImageBitmap(new)  //将bitmap在image中显示
    }
}

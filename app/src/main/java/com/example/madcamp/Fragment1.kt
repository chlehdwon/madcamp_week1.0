package com.example.madcamp

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestData(
    private var data1: String? = null,
    private var data2: String? = null,
    private var data3: String? = null
){
    fun getData1(): String? {
        return data1
    }
    fun setData1(name: String) {
        this.data1 = data1
    }
    fun getData2(): String? {
        return data2
    }
    fun setData2(address: String) {
        this.data2 = data2
    }
    fun getData3(): String? {
        return data3
    }
    fun setData3(type: String) {
        this.data3 = data3
    }
}

class Fragment1 : Fragment() {
    // RecyclerView.adapter에 지정할 Adapter
    private lateinit var listAdapter: ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataList = arrayListOf<TestData>()
//        var list: ArrayList<TestData> = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<TestData>
//        Log.e("FirstFragment", "Data List: ${list}")
        val assetManager: AssetManager = requireContext().resources.assets
        val inputStream = assetManager.open("Data.json")
        val jsonString = inputStream.bufferedReader().use{it.readText()}
        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("phonebook")

        for(i in 0 until jArray.length()){
            val obj = jArray.getJSONObject(i)
            val name = obj.getString("name")
            val number = obj.getString("number")
            dataList.add(TestData(name, number, "123"))
        }

        // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
        listAdapter = ListAdapter(dataList)
        val listView: RecyclerView = requireView().findViewById<View>(R.id.listView) as RecyclerView
        listView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        // RecyclerView.adapter에 지정
        listView.adapter = listAdapter
    }

}
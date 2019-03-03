package com.example.recyclerviewapi

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapi.adapter.MemberAdapter
import com.example.recyclerviewapi.entity.Member
import com.example.recyclerviewapi.network.MainService
import com.example.recyclerviewapi.utils.FontHelper
import com.example.recyclerviewapi.utils.TypefaceManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.data_card.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),CoroutineScope {

    private  lateinit var mJob: Job
    //API to Recycler View
    private lateinit var member_adapter: MemberAdapter
    //Font Detector
    private lateinit var typefaceManager: TypefaceManager
    private val myMember: MutableList<Member> = ArrayList()

    override val coroutineContext: CoroutineContext
    get() = mJob+ Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        member_adapter = MemberAdapter(myMember)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = member_adapter
        }
        mJob = Job()
        val api = MainService()
        GlobalScope.launch {
            val request = api.getMemberAsync()
            try{
                val response = request.await()
                myMember.addAll(response.body()!!.data)
                member_adapter.notifyDataSetChanged()

                if(response.isSuccessful){
                    Log.d("MY_RESPONSE",response.body().toString())
                    typefaceManager = TypefaceManager(assets)
                    if (FontHelper.isUnicode()){
                        member_name.typeface = typefaceManager.uni
                        previous_occupation.typeface = typefaceManager.uni
                    }else{
                        member_name.typeface= typefaceManager.zawgyi
                        previous_occupation.typeface = typefaceManager.zawgyi
                    }
                }
                else{
                    Log.d("MY_ERROR" , "something wrong")
                }
            }
            catch (e:Throwable){
                Log.d("error","some error")
            }

        }
        //Font Detect


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
}

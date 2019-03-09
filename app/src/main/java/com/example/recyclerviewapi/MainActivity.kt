package com.example.recyclerviewapi

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapi.adapter.MemberAdapter
import com.example.recyclerviewapi.entity.Member
import com.example.recyclerviewapi.network.MainService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),CoroutineScope {

    private  lateinit var mJob: Job
    //API to Recycler View
    private lateinit var memberAdapter: MemberAdapter

    private val myMember: MutableList<Member> = ArrayList()

    override val coroutineContext: CoroutineContext
    get() = mJob+ Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        memberAdapter = MemberAdapter(myMember,assets)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = memberAdapter
        }
        mJob = Job()
        val api = MainService()
        GlobalScope.launch {
            val request = api.getMemberAsync()
            try{
                val response = request.await()
                myMember.addAll(response.body()!!.data)
                memberAdapter.notifyDataSetChanged()

                if(response.isSuccessful){
                    Log.d("MY_RESPONSE",response.body().toString())

                }
                else{
                    Log.d("MY_ERROR" , "something wrong")
                }
            }
            catch (e:Throwable){
                Log.d("error","some error")
            }

        }



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

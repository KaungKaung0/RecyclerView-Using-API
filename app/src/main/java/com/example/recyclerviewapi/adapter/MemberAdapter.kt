package com.example.recyclerviewapi.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapi.R
import com.example.recyclerviewapi.entity.Member
import com.example.recyclerviewapi.utils.inflate
import com.squareup.picasso.Picasso

class MemberAdapter(val members: MutableList<Member>):RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
     return MemberViewHolder(parent.inflate(R.layout.data_card))
    }

    override fun getItemCount() = members.size

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
       val member = members[position]
        holder.bind(member)
    }


    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val proifle_pic = itemView.findViewById<ImageView>(R.id.imageCard)
             val member_name = itemView.findViewById<TextView>(R.id.member_name)
        val pre_occc=itemView.findViewById<TextView>(R.id.previous_occupation)
        fun bind(member: Member){
            Picasso.get().load(member.profilePic).into(proifle_pic)
            member_name.text = member.name
            pre_occc.text = member.previousOccupation
        }
    }
}
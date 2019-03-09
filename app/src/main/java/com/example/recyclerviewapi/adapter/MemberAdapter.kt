package com.example.recyclerviewapi.adapter

import android.content.res.AssetManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapi.R
import com.example.recyclerviewapi.entity.Member
import com.example.recyclerviewapi.utils.FontHelper
import com.example.recyclerviewapi.utils.TypefaceManager
import com.example.recyclerviewapi.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_card.*

class MemberAdapter(val members: MutableList<Member> , val assets:AssetManager):RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
     return MemberViewHolder(parent.inflate(R.layout.data_card))
    }

    override fun getItemCount() = members.size

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
       val member = members[position]
        holder.bind(member)
    }


    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val proifle_pic = itemView.findViewById<ImageView>(R.id.imageCard)
        val member_name = itemView.findViewById<TextView>(R.id.member_name)
        val pre_occc = itemView.findViewById<TextView>(R.id.previous_occupation)
        fun bind(member: Member) {
            //Font Detector

          val typefaceManager: TypefaceManager = TypefaceManager(assets)

            //Font Detect
            if (FontHelper.isUnicode()) {
                member_name.text = member.name
                pre_occc.text = member.previousOccupation
                member_name.typeface = typefaceManager.uni
                pre_occc.typeface = typefaceManager.uni
            } else {
                Log.d("fonttest", "Zawgyi")
                member_name.text = member.name
                pre_occc.text = member.previousOccupation
                member_name.typeface = typefaceManager.zawgyi
                pre_occc.typeface = typefaceManager.zawgyi
            }
                Picasso.get().load(member.profilePic).into(proifle_pic)
            }
        }
    }

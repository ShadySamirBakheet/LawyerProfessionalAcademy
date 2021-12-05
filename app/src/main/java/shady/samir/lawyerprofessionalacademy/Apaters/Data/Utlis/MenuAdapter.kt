package com.aplexgold.aplexshipping.Adapters.Data.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplexgold.aplexshipping.Data.Model.Home.MenuItem
import shady.samir.lawyerprofessionalacademy.R

class MenuAdapter(private val context: Context?,val listData: ArrayList<MenuItem>?) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    var last = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 0){
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_menu , parent, false)
            return ViewHolder(view)
        }else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_menu_main , parent, false)
            return ViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData?.get(position)?.isMain == true){
            1
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData?.get(position)
        holder.apply {
            if (item != null) {
                title.text = item.name
                imgicon.setImageResource(item.icon)

                if (item.isMain){

                    menuSubRView.setHasFixedSize(true)
                    menuSubRView.layoutManager = LinearLayoutManager(context)
                    menuSubRView.adapter = MenuAdapter(context,item.child)
                }

                if (last == position){
                    img_down.setImageResource(R.drawable.ic_up_exg)
                    menuSubRView.visibility = View.VISIBLE
                }else{
                    img_down.setImageResource(R.drawable.ic_down_exg)
                    menuSubRView.visibility = View.GONE
                }

                itemView.setOnClickListener {
                    if(item.isMain){
                        if ( menuSubRView.visibility == View.GONE){
                            last = position
                        }else{
                            last = -1
                        }
                        notifyDataSetChanged()
                    }else{
                        onItemClickListener.let {
                            if (it != null) {
                                it(item.id)
                            }
                        }
                    }

                }

            }
        }
    }


    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
        if (listData != null) {
            return listData.size
        }else{
            return 0
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleItem)
        val imgicon : ImageView = itemView.findViewById(R.id.imgicon)
        val img_down : ImageView = itemView.findViewById(R.id.img_down)
        val menuSubRView : RecyclerView = itemView.findViewById(R.id.menuSubRView)
    }
}
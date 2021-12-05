package shady.samir.lawyerprofessionalacademy.Apaters.Data.Programes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.lawyerprofessionalacademy.R

class AdvProgramesAdapter : RecyclerView.Adapter<AdvProgramesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.item_adv_programe, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    /*val category = data[position]
        val  url = Values.BASEURIIamge+category.image
        Glide.with(holder.itemView.context).load(url)
            .placeholder(R.drawable.pngegg)
            .into(holder.image)*/

        holder.apply {
            when(position){
                0->imgItem.setImageResource(R.drawable.image)
                1->imgItem.setImageResource(R.drawable.image2)
                2->imgItem.setImageResource(R.drawable.image3)
                3->imgItem.setImageResource(R.drawable.image4)
                4->imgItem.setImageResource(R.drawable.image5)
                5->imgItem.setImageResource(R.drawable.image6)
                6->imgItem.setImageResource(R.drawable.image7)
            }
        }

    }

    override fun getItemCount(): Int {
        return 8
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView = itemView.findViewById(R.id.image)
    }

}

package shady.samir.lawyerprofessionalacademy.Apaters.Sliders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter
import shady.samir.lawyerprofessionalacademy.R

class SliderHomeAdapter (var context: Context?): SliderViewAdapter<SliderHomeAdapter.SliderAdapterVH>() {

    var size = 8

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_slider_home, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.apply {
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


    override fun getCount(): Int {
        return size
    }

    inner class SliderAdapterVH(view: View) : ViewHolder(view) {
        var imgItem: ImageView =itemView.findViewById(R.id.imgItem)
    }
}
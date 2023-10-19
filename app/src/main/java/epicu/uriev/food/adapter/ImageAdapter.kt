package epicu.uriev.food.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val context: Context, private val imageIds: MutableList<Int>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return 45
    }

    override fun getItem(position: Int): Any {
        return imageIds[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams =
                AbsListView.LayoutParams(120, 120)
            imageView.setPadding(4, 4, 4, 4)
        } else {
            imageView = convertView as ImageView
        }

        val imageId = imageIds[position]
        imageView.setImageResource(imageId)

        return imageView
    }
}
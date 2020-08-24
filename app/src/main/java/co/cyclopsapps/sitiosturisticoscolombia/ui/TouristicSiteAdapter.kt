package co.cyclopsapps.sitiosturisticoscolombia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import co.cyclopsapps.sitiosturisticoscolombia.R
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSite
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_tourisitc_site.view.*

class TouristicSiteAdapter(private var touristicsites: List<TouristicSite>) : RecyclerView.Adapter<TouristicSiteAdapter.MViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TouristicSiteAdapter.MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_tourisitc_site, parent, false)
        return MViewHolder(view)
    }

    override fun getItemCount(): Int {
        return touristicsites.size
    }

    override fun onBindViewHolder(holder: TouristicSiteAdapter.MViewHolder, position: Int) {
        holder.bind(touristicsites[position])
    }

    fun update(data: List<TouristicSite>) {
        touristicsites = data
        notifyDataSetChanged()
    }

    class MViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val textViewName : TextView = view.textViewName
        private val imageView : ImageView = view.imageView

        fun bind(touristicsite: TouristicSite) {
            textViewName.text = touristicsite.name
            Glide.with(imageView.context).load(touristicsite.photo).into(imageView)
        }
    }

}

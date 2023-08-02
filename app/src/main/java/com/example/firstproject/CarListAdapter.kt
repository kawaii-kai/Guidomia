package com.example.firstproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.databinding.CarListBinding



class CarListAdapter(
    var list: List<CarData>,

): RecyclerView.Adapter<CarListAdapter.ViewHolderCarList>(){

    inner class ViewHolderCarList(val binding: CarListBinding): RecyclerView.ViewHolder(binding.root){
        val title : TextView = binding.titleTv
        val price : TextView = binding.priceTv
        val pic: ImageView = binding.picIv
        val  starRatingLinearLayout = binding.starRatingLayout

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCarList {
        val v = CarListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolderCarList(v)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderCarList, position: Int) {
       val car = list[position]
        //set data in items

            holder.pic.setImageResource(car?.pic ?: R.drawable.alpineroadster)
            holder.title.text = (car.title)
            // TODO: Add function to format price can be here or in CarListAdapter

            holder.price.text = (car.price).toString()

        createStarRating(car.rating, holder)
    }
    private fun createStarRating(rating: Int, holder: ViewHolderCarList){
        for( i in 0 until rating){
            val starImageView = LayoutInflater.from(holder.binding.root.context).inflate(R.layout.star_rating, null)
            holder.starRatingLinearLayout.addView(starImageView, i)
        }

    }
}


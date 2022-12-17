package com.redmechax00.vkcup2022qualification.ui.favorites

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.redmechax00.vkcup2022qualification.App
import com.redmechax00.vkcup2022qualification.R
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteItem
import com.redmechax00.vkcup2022qualification.utils.MyDiffUtilCallback


class FavoritesAdapter(private val onItemClicked: (item: FavoriteItem) -> Unit) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder>() {

    private val data: AsyncListDiffer<FavoriteItem> =
        AsyncListDiffer(this, MyDiffUtilCallback())

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return FavoritesHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_item, parent, false),
            onItemClicked
        )
    }

    override fun onBindViewHolder(viewHolder: FavoritesHolder, position: Int) {
        viewHolder.bind(data.currentList[position])
    }

    override fun getItemCount() = data.currentList.size

    fun updateData(newData: ArrayList<FavoriteItem>) {
        data.submitList(newData)
    }

    class FavoritesHolder(itemView: View, private val onItemClicked: (item: FavoriteItem) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var item: FavoriteItem

        private val favoriteBackground = itemView.findViewById<ImageView>(R.id.favorite_background)
        private val btnFavoriteAdd: LottieAnimationView =
            itemView.findViewById(R.id.favorite_btn_add)
        private val favoriteTitle = itemView.findViewById<TextView>(R.id.favorite_text_title)
        private val favoriteSeparator = itemView.findViewById<View>(R.id.favorite_separator)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            item.itemIsChecked = !item.itemIsChecked
            onItemClicked(item)

            item.updateItemIsChecked()
        }

        fun bind(bindItem: FavoriteItem) {
            item = bindItem
            favoriteTitle.text = item.itemText

            item.setItemIsChecked()
        }

        private fun FavoriteItem.setItemIsChecked() {
            if (this.itemIsChecked) {
                btnFavoriteAdd.progress = 0.5f
                favoriteBackground.setBackgroundColorFilter(App.Colors.get(R.color.color_btn_favorite_secondary))
                favoriteSeparator.visibility = View.INVISIBLE
            } else {
                btnFavoriteAdd.progress = 0f
                favoriteBackground.setBackgroundColorFilter(App.Colors.get(R.color.color_btn_favorite_primary))
                favoriteSeparator.visibility = View.VISIBLE
            }
        }

        private fun FavoriteItem.updateItemIsChecked() {
            if (this.itemIsChecked) {
                btnFavoriteAdd.progress = 0f
                btnFavoriteAdd.setMinAndMaxProgress(0f, 0.5f)
                btnFavoriteAdd.speed = 1.5f
                btnFavoriteAdd.playAnimation()
                favoriteBackground.updateBackgroundColorFilter(
                    App.Colors.get(R.color.color_btn_favorite_primary),
                    App.Colors.get(R.color.color_btn_favorite_secondary)
                )
                favoriteSeparator.visibility = View.INVISIBLE
            } else {
                btnFavoriteAdd.progress = 0.5f
                btnFavoriteAdd.setMinAndMaxProgress(0.5f, 1f)
                btnFavoriteAdd.speed = 1.5f
                btnFavoriteAdd.playAnimation()
                favoriteBackground.updateBackgroundColorFilter(
                    App.Colors.get(R.color.color_btn_favorite_secondary),
                    App.Colors.get(R.color.color_btn_favorite_primary)
                )
                favoriteSeparator.visibility = View.VISIBLE
            }
        }

        private fun ImageView.updateBackgroundColorFilter(colorFrom: Int, colorTo: Int) {
            val anim = ValueAnimator.ofArgb(colorFrom, colorTo)
            anim.addUpdateListener { this.setBackgroundColorFilter(it.animatedValue as Int) }
            anim.duration = 300
            anim.start()
        }

        private fun ImageView.setBackgroundColorFilter(newColor: Int) {
            this.background.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    newColor,
                    BlendModeCompat.DST_ATOP
                )
        }
    }
}
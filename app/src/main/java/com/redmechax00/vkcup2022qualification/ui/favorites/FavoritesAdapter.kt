package com.redmechax00.vkcup2022qualification.ui.favorites

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.redmechax00.vkcup2022qualification.App
import com.redmechax00.vkcup2022qualification.R
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteModel
import com.redmechax00.vkcup2022qualification.utils.MyDiffUtilCallback


class FavoritesAdapter(private val onItemClicked: (item: FavoriteModel) -> Unit) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder>() {

    private val data: AsyncListDiffer<FavoriteModel> =
        AsyncListDiffer(this, MyDiffUtilCallback())

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

    fun updateData(newData: ArrayList<FavoriteModel>) {
        data.submitList(newData)
    }

    class FavoritesHolder(itemView: View, private val onItemClicked: (item: FavoriteModel) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var item: FavoriteModel

        private val itemBackground = itemView.findViewById<ImageView>(R.id.favorite_item_background)
        private val itemBtnAdd: LottieAnimationView =
            itemView.findViewById(R.id.favorite_item_btn_add)
        private val itemTitle = itemView.findViewById<TextView>(R.id.favorite_item_text_title)
        private val itemSeparator = itemView.findViewById<View>(R.id.favorite_item_separator)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            item.itemIsChecked = !item.itemIsChecked
            onItemClicked(item)

            item.updateItemIsChecked()
        }

        fun bind(bindItem: FavoriteModel) {
            item = bindItem
            itemTitle.text = item.itemText

            item.setItemIsChecked()
        }

        private fun FavoriteModel.setItemIsChecked() {
            if (this.itemIsChecked) {
                itemBtnAdd.progress = 0.5f
                itemBackground.setBackgroundColorFilter(App.Colors.get(R.color.color_btn_favorite_secondary))
                itemSeparator.visibility = View.INVISIBLE
            } else {
                itemBtnAdd.progress = 0f
                itemBackground.setBackgroundColorFilter(App.Colors.get(R.color.color_btn_favorite_primary))
                itemSeparator.visibility = View.VISIBLE
            }
        }

        private fun FavoriteModel.updateItemIsChecked() {
            if (this.itemIsChecked) {
                itemBtnAdd.progress = 0f
                itemBtnAdd.setMinAndMaxProgress(0f, 0.5f)
                itemBtnAdd.speed = 1.5f
                itemBtnAdd.playAnimation()
                itemBackground.updateBackgroundColorFilter(
                    App.Colors.get(R.color.color_btn_favorite_primary),
                    App.Colors.get(R.color.color_btn_favorite_secondary)
                )
                itemSeparator.visibility = View.INVISIBLE
            } else {
                itemBtnAdd.progress = 0.5f
                itemBtnAdd.setMinAndMaxProgress(0.5f, 1f)
                itemBtnAdd.speed = 1.5f
                itemBtnAdd.playAnimation()
                itemBackground.updateBackgroundColorFilter(
                    App.Colors.get(R.color.color_btn_favorite_secondary),
                    App.Colors.get(R.color.color_btn_favorite_primary)
                )
                itemSeparator.visibility = View.VISIBLE
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
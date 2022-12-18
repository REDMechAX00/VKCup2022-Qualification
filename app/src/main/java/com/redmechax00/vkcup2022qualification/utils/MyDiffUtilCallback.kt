package com.redmechax00.vkcup2022qualification.utils

import androidx.recyclerview.widget.DiffUtil
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteModel

class MyDiffUtilCallback : DiffUtil.ItemCallback<FavoriteModel>() {

    override fun areItemsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
        return oldItem == newItem
    }

}
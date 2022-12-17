package com.redmechax00.vkcup2022qualification.utils

import androidx.recyclerview.widget.DiffUtil
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteItem

class MyDiffUtilCallback : DiffUtil.ItemCallback<FavoriteItem>() {

    override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
        return oldItem == newItem
    }

}
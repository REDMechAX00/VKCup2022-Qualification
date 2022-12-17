package com.redmechax00.vkcup2022qualification.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteItem
import com.redmechax00.vkcup2022qualification.data.favorites.FavoritesObject

class FavoritesViewModel : ViewModel(), Contract.FavouritesModel {

    private val favoritesLiveMutable: MutableLiveData<ArrayList<FavoriteItem>> = MutableLiveData()
    val favoritesLive: LiveData<ArrayList<FavoriteItem>> = favoritesLiveMutable
    private var scrollPosition: Int

    init {
        favoritesLiveMutable.value = FavoritesObject.getItems()
        scrollPosition = 0
    }

    override fun updateLiveData(newData: ArrayList<FavoriteItem>) {
        favoritesLiveMutable.value = newData
    }

    override fun setScrollPosition(lastScrollPosition: Int) {
        scrollPosition = lastScrollPosition
    }

    override fun getScrollState(): Int = scrollPosition

}

package com.redmechax00.vkcup2022qualification.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteModel
import com.redmechax00.vkcup2022qualification.data.favorites.FavoritesObject

class FavoritesViewModel : ViewModel(), Contract.FavoritesModel {

    private val favoritesLiveMutable: MutableLiveData<ArrayList<FavoriteModel>> = MutableLiveData()
    val favoritesLive: LiveData<ArrayList<FavoriteModel>> = favoritesLiveMutable
    private var scrollPosition: Int

    init {
        favoritesLiveMutable.value = FavoritesObject.getItems()
        scrollPosition = 0
    }

    override fun updateLiveData(newData: ArrayList<FavoriteModel>) {
        favoritesLiveMutable.value = newData
    }

    override fun setScrollPosition(lastScrollPosition: Int) {
        scrollPosition = lastScrollPosition
    }

    override fun getScrollState(): Int = scrollPosition

}

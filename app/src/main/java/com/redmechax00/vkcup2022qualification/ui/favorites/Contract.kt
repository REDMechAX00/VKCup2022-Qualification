package com.redmechax00.vkcup2022qualification.ui.favorites

import androidx.lifecycle.LifecycleOwner
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteItem

interface Contract {
    interface FavouritesView {
        fun initView(lastScrollPosition: Int)
        fun getFavoritesLifecycleOwner(): LifecycleOwner
        fun updateAdapter(newData: ArrayList<FavoriteItem>)
        fun showFabContinue(show:Boolean)
        fun showToast(text: String)
    }

    interface FavouritesPresenter {
        fun onResume()
        fun onPause(lastScrollPosition: Int)
        fun onItemClick(item: FavoriteItem)
        fun onDestroy()
    }

    interface FavouritesModel {
        fun updateLiveData(newData: ArrayList<FavoriteItem>)
        fun setScrollPosition(lastScrollPosition: Int)
        fun getScrollState(): Int
    }
}
package com.redmechax00.vkcup2022qualification.ui.favorites

import androidx.lifecycle.LifecycleOwner
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteModel

interface Contract {
    interface FavoritesView {
        fun initView(lastScrollPosition: Int)
        fun getFavoritesLifecycleOwner(): LifecycleOwner
        fun updateAdapter(newData: ArrayList<FavoriteModel>)
        fun startNextFragment(data: ArrayList<String>?)
        fun showFabContinue(show:Boolean)
        fun showToast(text: String)
    }

    interface FavoritesPresenter {
        fun onResume()
        fun onPause(lastScrollPosition: Int)
        fun onBtnSkipClick()
        fun onBtnContinueClick()
        fun onItemClick(item: FavoriteModel)
        fun onDestroy()
    }

    interface FavoritesModel {
        fun updateLiveData(newData: ArrayList<FavoriteModel>)
        fun setScrollPosition(lastScrollPosition: Int)
        fun getScrollState(): Int
    }
}
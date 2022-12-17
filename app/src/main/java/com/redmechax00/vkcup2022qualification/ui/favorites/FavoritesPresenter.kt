package com.redmechax00.vkcup2022qualification.ui.favorites

import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteItem

class FavoritesPresenter(
    _view: Contract.FavouritesView,
    _model: FavoritesViewModel
) : Contract.FavouritesPresenter {

    private var view: Contract.FavouritesView = _view
    private var model: FavoritesViewModel = _model

    init {
        view.initView(model.getScrollState())

        model.favoritesLive.observe(view.getFavoritesLifecycleOwner()) { data ->
            onUpdateData(data)
            view.showFabContinue(checkOneItemIsSelected(data))
        }
    }

    override fun onResume() {}

    override fun onPause(lastScrollPosition: Int) {
        model.setScrollPosition(lastScrollPosition)
    }

    override fun onItemClick(item: FavoriteItem) {
        setItemChecked(item)
    }

    private fun setItemChecked(item: FavoriteItem) {
        val data: ArrayList<FavoriteItem> = model.favoritesLive.value!!
        val clickedItem = data.find { it.itemId == item.itemId }
        clickedItem?.itemIsChecked = item.itemIsChecked
        model.updateLiveData(data)
    }

    private fun checkOneItemIsSelected(data: ArrayList<FavoriteItem>): Boolean {
        return data.find { it.itemIsChecked } != null
    }

    private fun onUpdateData(data: ArrayList<FavoriteItem>) {
        view.updateAdapter(data)
    }

    override fun onDestroy() {}
}

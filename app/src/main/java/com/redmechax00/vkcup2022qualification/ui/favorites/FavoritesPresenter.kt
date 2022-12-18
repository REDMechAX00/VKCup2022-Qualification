package com.redmechax00.vkcup2022qualification.ui.favorites

import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteModel

class FavoritesPresenter(
    _view: Contract.FavoritesView,
    _model: FavoritesViewModel
) : Contract.FavoritesPresenter {

    private var view: Contract.FavoritesView = _view
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

    override fun onItemClick(item: FavoriteModel) {
        setItemChecked(item)
    }

    override fun onBtnSkipClick() {
        view.startNextFragment(null)
    }

    override fun onBtnContinueClick() {
        val checkedData = model.favoritesLive.value?.filter { it.itemIsChecked }
        val checkedTextData = arrayListOf<String>()
        checkedData?.forEach {
            checkedTextData.add(it.toString())
        }
        view.startNextFragment(checkedTextData)
    }

    private fun setItemChecked(item: FavoriteModel) {
        val data: ArrayList<FavoriteModel> = model.favoritesLive.value!!
        val clickedItem = data.find { it.itemId == item.itemId }
        clickedItem?.itemIsChecked = item.itemIsChecked
        model.updateLiveData(data)
    }

    private fun checkOneItemIsSelected(data: ArrayList<FavoriteModel>): Boolean {
        return data.find { it.itemIsChecked } != null
    }

    private fun onUpdateData(data: ArrayList<FavoriteModel>) {
        view.updateAdapter(data)
    }

    override fun onDestroy() {}
}

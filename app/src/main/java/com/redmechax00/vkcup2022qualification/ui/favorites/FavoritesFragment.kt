package com.redmechax00.vkcup2022qualification.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteItem
import com.redmechax00.vkcup2022qualification.databinding.FavoritesFragmentBinding


class FavoritesFragment : Fragment(), Contract.FavouritesView {

    private lateinit var _binding: FavoritesFragmentBinding
    private val binding get() = _binding

    private lateinit var presenter: FavoritesPresenter
    private lateinit var viewModel: FavoritesViewModel

    //RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerViewFiller: View

    //Fab
    private lateinit var fabContinue: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        val v = binding.root

        viewModel =
            ViewModelProvider(requireActivity())[FavoritesViewModel::class.java]
        presenter = FavoritesPresenter(this, viewModel)

        return v
    }

    override fun onPause() {
        val lastScrollPosition =
            (recyclerView.layoutManager as FlexboxLayoutManager).findFirstCompletelyVisibleItemPosition()
        presenter.onPause(lastScrollPosition)
        super.onPause()
    }

    override fun getFavoritesLifecycleOwner(): LifecycleOwner = viewLifecycleOwner

    override fun initView(lastScrollPosition: Int) {
        //RecyclerView
        recyclerView = binding.favoritesRecyclerView
        recyclerViewFiller = binding.favoritesRecyclerViewFiller
        adapter = FavoritesAdapter { item -> onItemClick(item) }
        tuneRecyclerView(lastScrollPosition)

        //Floating Button
        fabContinue = binding.favoritesBtnContinue
    }

    private fun tuneRecyclerView(lastScrollPosition: Int) {
        //Flexible settings
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        (recyclerView.layoutManager as FlexboxLayoutManager).scrollToPosition(lastScrollPosition)
    }

    override fun updateAdapter(newData: ArrayList<FavoriteItem>) {
        adapter.updateData(newData)
    }

    private fun onItemClick(item: FavoriteItem) {
        presenter.onItemClick(item)
    }

    override fun showFabContinue(show: Boolean) {
        if(show){
            recyclerViewFiller.visibility = View.VISIBLE
            fabContinue.show()
        }
        else{
            recyclerViewFiller.visibility = View.GONE
            fabContinue.hide()
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}
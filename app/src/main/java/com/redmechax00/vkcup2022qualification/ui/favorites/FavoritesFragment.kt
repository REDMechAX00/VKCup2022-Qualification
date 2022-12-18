package com.redmechax00.vkcup2022qualification.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.redmechax00.vkcup2022qualification.data.favorites.FavoriteModel
import com.redmechax00.vkcup2022qualification.databinding.FavoritesFragmentBinding
import com.redmechax00.vkcup2022qualification.ui.temporary.TemporaryFinishFragment
import com.redmechax00.vkcup2022qualification.utils.startFragment


class FavoritesFragment : Fragment(), Contract.FavoritesView {

    private lateinit var _binding: FavoritesFragmentBinding
    private val binding get() = _binding

    private lateinit var presenter: FavoritesPresenter
    private lateinit var viewModel: FavoritesViewModel

    //RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerViewFiller: View

    //Button
    private lateinit var btnSkip: Button

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

    override fun onStart() {
        super.onStart()
        setOnClickListeners()
    }

    override fun onPause() {
        super.onPause()
        val lastScrollPosition =
            (recyclerView.layoutManager as FlexboxLayoutManager).findFirstCompletelyVisibleItemPosition()
        presenter.onPause(lastScrollPosition)
        removeOnClickListeners()
    }

    override fun getFavoritesLifecycleOwner(): LifecycleOwner = viewLifecycleOwner

    override fun initView(lastScrollPosition: Int) {
        //RecyclerView
        recyclerView = binding.favoritesRecyclerView
        recyclerViewFiller = binding.favoritesRecyclerViewFiller
        adapter = FavoritesAdapter { item -> onItemClick(item) }
        tuneRecyclerView(lastScrollPosition)

        //Button
        btnSkip = binding.favoritesBtnSkip

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

    private fun setOnClickListeners() {
        btnSkip.setOnClickListener { presenter.onBtnSkipClick() }
        fabContinue.setOnClickListener { presenter.onBtnContinueClick() }
    }

    override fun updateAdapter(newData: ArrayList<FavoriteModel>) {
        adapter.updateData(newData)
    }

    override fun startNextFragment(data: ArrayList<String>?) {
        startFragment(requireActivity() as AppCompatActivity, TemporaryFinishFragment(), data, true)
    }

    private fun onItemClick(item: FavoriteModel) {
        presenter.onItemClick(item)
    }

    override fun showFabContinue(show: Boolean) {
        if (show) {
            recyclerViewFiller.visibility = View.VISIBLE
            fabContinue.show()
        } else {
            recyclerViewFiller.visibility = View.GONE
            fabContinue.hide()
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun removeOnClickListeners() {
        btnSkip.setOnClickListener(null)
        fabContinue.setOnClickListener(null)
    }
}
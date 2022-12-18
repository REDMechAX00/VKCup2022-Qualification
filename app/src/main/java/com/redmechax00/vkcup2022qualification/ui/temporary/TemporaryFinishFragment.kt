package com.redmechax00.vkcup2022qualification.ui.temporary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.redmechax00.vkcup2022qualification.R
import com.redmechax00.vkcup2022qualification.databinding.TemporaryFinishFragmentBinding
import com.redmechax00.vkcup2022qualification.utils.KEY_FAVORITES_ARRAY_LIST

class TemporaryFinishFragment : Fragment() {

    private lateinit var _binding: TemporaryFinishFragmentBinding
    private val binding get() = _binding

    //Lottie Animation
    private lateinit var animView: LottieAnimationView

    //RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TemporaryFinishAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TemporaryFinishFragmentBinding.inflate(inflater, container, false)
        val v = binding.root

        initView()

        return v
    }

    private fun initView() {
        //Lottie Animation
        animView = binding.temporaryFinishAnim

        //RecyclerView
        recyclerView = binding.temporaryFinishRecyclerView
        adapter = TemporaryFinishAdapter()
        tuneRecyclerView()
    }

    private fun tuneRecyclerView() {
        //Flexible settings
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        val data = getFavoritesData()
        if(data.isEmpty()){
            animView.setAnimation(R.raw.favorites_skip)
            animView.repeatCount = LottieDrawable.INFINITE
            animView.playAnimation()
        } else {
            animView.setAnimation(R.raw.favorites_complete)
            animView.repeatCount = LottieDrawable.INFINITE
            animView.repeatMode = LottieDrawable.REVERSE
            animView.playAnimation()
            adapter.updateData(data)
        }

    }

    private fun getFavoritesData(): ArrayList<String> {
        return arguments?.getStringArrayList(KEY_FAVORITES_ARRAY_LIST) ?: arrayListOf()
    }
}
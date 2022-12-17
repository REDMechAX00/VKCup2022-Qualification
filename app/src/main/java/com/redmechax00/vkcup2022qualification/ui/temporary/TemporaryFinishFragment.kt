package com.redmechax00.vkcup2022qualification.ui.temporary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.redmechax00.vkcup2022qualification.databinding.FavoritesFragmentBinding

class TemporaryFinishFragment : Fragment() {

    private lateinit var _binding: FavoritesFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        val v = binding.root

        return v
    }
}
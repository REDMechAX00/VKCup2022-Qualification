package com.redmechax00.vkcup2022qualification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redmechax00.vkcup2022qualification.databinding.ActivityMainBinding
import com.redmechax00.vkcup2022qualification.ui.favorites.FavoritesFragment
import com.redmechax00.vkcup2022qualification.utils.startFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startFragment(this, FavoritesFragment(), null, false)
    }
}
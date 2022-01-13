package com.akdogan.simpleclickcounter.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import com.akdogan.simpleclickcounter.ui.theme.SimpleClickCounterTheme


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onStop() {
        viewModel.doSaving()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        viewModel.update()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModelFactory = ClickViewModelFactory(requireContext())

        viewModel = ViewModelProvider(this, viewModelFactory).get(
            MainViewModel::class.java
        )

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SimpleClickCounterTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        ClickCounterScreen(viewModel = viewModel)
                    }
                }
            }

        }
    }


}
package com.akdogan.simpleclickcounter.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ClickCounterScreen(
    viewModel: MainViewModel
) {

    val text: String by viewModel.currentCounterValueAsString.observeAsState("0")
    ClickCounterView(
        countUpListener = viewModel::onCountUp,
        countDownListener = viewModel::onCountDown,
        currentValue = text
    )
}
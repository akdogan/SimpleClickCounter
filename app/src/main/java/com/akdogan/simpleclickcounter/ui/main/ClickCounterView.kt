package com.akdogan.simpleclickcounter.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akdogan.simpleclickcounter.R

@Composable
fun ClickCounterView(
    countUpListener: () -> Unit,
    countDownListener: () -> Unit,
    currentValue: String
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = Color.Red.copy(alpha = 0.7f),
        modifier = Modifier.padding(all = 20.dp)
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 32.dp, horizontal = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.clickable { countDownListener() }
            ) {
                Image(
                    modifier = Modifier
                        .size(56.dp),
                    contentDescription = "Plus Button",
                    painter = painterResource(id = R.drawable.ic_baseline_remove_circle_outline_24)
                )
            }
            Column{
                Text(
                    fontSize = 34.sp,
                    text = currentValue,
                    color = Color.White
                )
            }
            Column(
                modifier = Modifier.clickable { countUpListener() }
            ){
                Image(
                    modifier = Modifier
                        .size(56.dp),
                    contentDescription = "Plus Button",
                    painter = painterResource(id = R.drawable.ic_baseline_add_circle_outline_24)
                )
            }

        }
    }
}

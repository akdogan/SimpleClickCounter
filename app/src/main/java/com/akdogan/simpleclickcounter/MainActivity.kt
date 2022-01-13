package com.akdogan.simpleclickcounter

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akdogan.simpleclickcounter.ui.chart.ChartFragment
import com.akdogan.simpleclickcounter.ui.main.*
import com.akdogan.simpleclickcounter.ui.theme.SimpleClickCounterTheme

class MainActivity : AppCompatActivity() {

    //lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val viewModelFactory = ClickViewModelFactory(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(
            MainViewModel::class.java
        )
        setContent {
            SimpleClickCounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ClickCounterScreen(viewModel = viewModel)
                }
            }
        }*/
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, MainFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> navigateToChart()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToChart(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, ChartFragment())
            .addToBackStack("CHART_FRAGMENT")
            .commit()
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleClickCounterTheme {
        Greeting("Android")
    }
}
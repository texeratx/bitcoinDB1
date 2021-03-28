package br.com.texeratx.bitcoinprice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import br.com.texeratx.bitcoinprice.R
import br.com.texeratx.bitcoinprice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setNavGraph()
    }

    private fun setNavGraph() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
    }
}


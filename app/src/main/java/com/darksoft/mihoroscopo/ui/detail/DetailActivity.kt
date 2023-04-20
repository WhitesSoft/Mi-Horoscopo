package com.darksoft.mihoroscopo.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.darksoft.mihoroscopo.databinding.ActivityDetailBinding
import com.darksoft.mihoroscopo.ui.detail.model.DetailUIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    // Abrir activity, este companion object es como un metodo estatico
    companion object {
        fun abrirActivity(context: Context) = Intent(context, DetailActivity::class.java)
    }

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        // Llamando la informacion del viewModel
        viewModel.getHoroscope()

    }

    private fun initUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.uiState.collect { uiState ->
                    when(uiState){
                        is DetailUIState.Loading -> {
                            // Mostrar el loading
                            binding.loading.isVisible = true
                        }
                        is DetailUIState.Success -> {
                            // Mostrar la informacion
                            binding.loading.isVisible = false
                            //runOnUiThread {  }
                            Toast.makeText(this@DetailActivity, "Ha funcionado", Toast.LENGTH_SHORT).show()
                        }
                        is DetailUIState.Error -> {
                            // Mostrar el error
                            binding.loading.isVisible = false
                        }
                    }
                }
            }
        }
    }
}
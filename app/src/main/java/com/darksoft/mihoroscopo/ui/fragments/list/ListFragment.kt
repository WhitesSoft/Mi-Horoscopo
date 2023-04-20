package com.darksoft.mihoroscopo.ui.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.darksoft.mihoroscopo.R
import com.darksoft.mihoroscopo.databinding.FragmentListBinding
import com.darksoft.mihoroscopo.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    // Inyectamos el viewModel con dagger hilt
    private val viewModel by viewModels<ListViewModel>()

    private var _bindind: FragmentListBinding? = null
    private val binding get() = _bindind!!

    // Este metodo se llama cuando todos los componentes estan creado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVirgo.setOnClickListener {
            // Abrir detalle
        }

        binding.btnVirgo.setOnClickListener {
            // Abrir detalle
        }

        binding.btnVirgo.setOnClickListener {
            // Abrir detalle
        }
    }

    // Creamos la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindind = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

}
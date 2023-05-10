package com.darksoft.mihoroscopo.ui.fragments.love

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.darksoft.mihoroscopo.R
import com.darksoft.mihoroscopo.ui.fragments.list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoveFragment : Fragment() {

    // Inyectamos el viewModel con dagger hilt
    private val viewModel by viewModels<LoveViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_love, container, false)
    }

}
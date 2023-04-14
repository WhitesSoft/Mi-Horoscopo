package com.darksoft.mihoroscopo.ui.fragments.lucky

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.darksoft.mihoroscopo.R
import com.darksoft.mihoroscopo.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckyFragment : Fragment() {

    // Inyectamos el viewModel con dagger hilt
    private val viewModel by viewModels<LuckyViewModel>()

    private var _bindind: FragmentLuckyBinding? = null
    private val binding get() = _bindind!!

    // Este metodo se llama cuando todos los componentes estan creado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //binding.tvLuckyInfo.animate().alpha(1f).setDuration(1000)

        // Animacion
        binding.viewBackContainer.viewBack.setOnClickListener {
            prepareCard()
            flipCard()
        }
    }

    private fun flipCard() {
        try {
            // Visible
            binding.viewFrontContainer.viewFront.isVisible = true

            // Escala del efecto 3D (Densidad de la pantalla)
            val scale = requireContext().resources.displayMetrics.density
            val cameraDist = 8000 * scale
            binding.viewFrontContainer.viewFront.cameraDistance = cameraDist
            binding.viewBackContainer.viewBack.cameraDistance = cameraDist

            // Recuperamos la animacion FLIP_OUT y seteamos la animacion
            val flipOutAnimatorSet = AnimatorInflater.loadAnimator(
                requireContext(), R.animator.flip_out
            ) as AnimatorSet
            flipOutAnimatorSet.setTarget(binding.viewBackContainer.viewBack)

            // Recuperamos la animacion FLIP_IN y seteamos la animacion
            val flipInAnimatorSet = AnimatorInflater.loadAnimator(
                requireContext(), R.animator.flip_in
            ) as AnimatorSet
            flipInAnimatorSet.setTarget(binding.viewFrontContainer.viewFront)

            // Iniciamos las animaciones
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()

            // Invisible
            flipInAnimatorSet.doOnEnd {
                binding.tvLuckyInfo.animate().alpha(1f).duration = 1000
                binding.viewBackContainer.viewBack.isVisible = false
            }

        }catch (e: java.lang.Exception){
            Log.e("animacion", e.message.toString())
        }
    }

    private fun prepareCard() {
        val image = when(Random.nextInt(0, 5)){
            0 -> R.drawable.card_0_fool
            1 -> R.drawable.card_1_magician
            2 -> R.drawable.card_2_highpriestess
            3 -> R.drawable.card_3_empress
            4 -> R.drawable.card_4_emperor
            5 -> R.drawable.card_5_hierophant
            else -> R.drawable.card_back
        }
        binding.viewFrontContainer.ivLuckyCard.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                image
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindind = FragmentLuckyBinding.inflate(inflater, container, false)
        return binding.root
    }

}
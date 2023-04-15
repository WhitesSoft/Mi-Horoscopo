package com.darksoft.mihoroscopo.ui.fragments.lucky

import com.darksoft.mihoroscopo.R
import com.darksoft.mihoroscopo.ui.fragments.lucky.model.LuckyModel
import javax.inject.Inject
import kotlin.random.Random

class RandomCardProvider @Inject constructor() {

    fun getLucky(): LuckyModel = when (Random.nextInt(0, 21)) {
        1 -> LuckyModel(R.drawable.card_1_magician, R.string.app_name)
        0 -> LuckyModel(R.drawable.card_0_fool, R.string.app_name)
        2 -> LuckyModel(R.drawable.card_2_highpriestess, R.string.app_name)
        3 -> LuckyModel(R.drawable.card_3_empress, R.string.app_name)
        4 -> LuckyModel(R.drawable.card_4_emperor, R.string.app_name)
        5 -> LuckyModel(R.drawable.card_5_hierophant, R.string.app_name)
        6 -> LuckyModel(R.drawable.card_6_lovers, R.string.app_name)
        7 -> LuckyModel(R.drawable.card_7_chariot, R.string.app_name)
        8 -> LuckyModel(R.drawable.card_8_strength, R.string.app_name)
        9 -> LuckyModel(R.drawable.card_9_hermit, R.string.app_name)
        10 -> LuckyModel(R.drawable.card_10_wheelfortune, R.string.app_name)
        11 -> LuckyModel(R.drawable.card_11_justice, R.string.app_name)
        12 -> LuckyModel(R.drawable.card_12_hangedman, R.string.app_name)
        13 -> LuckyModel(R.drawable.card_13_death, R.string.app_name)
        14 -> LuckyModel(R.drawable.card_14_temperance, R.string.app_name)
        15 -> LuckyModel(R.drawable.card_15_devil, R.string.app_name)
        16 -> LuckyModel(R.drawable.card_16_tower, R.string.app_name)
        17 -> LuckyModel(R.drawable.card_17_star, R.string.app_name)
        18 -> LuckyModel(R.drawable.card_18_moon, R.string.app_name)
        19 -> LuckyModel(R.drawable.card_19_sun, R.string.app_name)
        20 -> LuckyModel(R.drawable.card_20_judgement, R.string.app_name)
        21 -> LuckyModel(R.drawable.card_21_world, R.string.app_name)
        else -> LuckyModel(R.drawable.card_back, R.string.app_name)
    }
}

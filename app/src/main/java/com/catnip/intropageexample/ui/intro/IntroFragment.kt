package com.catnip.intropageexample.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.catnip.intropageexample.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentIntroBinding

    //data
    private var title: String? = null
    private var desc: String? = null
    private var imgRes: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_PARAM_INTRO_TITLE)
            desc = it.getString(ARG_PARAM_INTRO_DESC)
            imgRes = it.getInt(ARG_PARAM_INTRO_IMG_RES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = title.orEmpty()
        binding.tvDesc.text = desc.orEmpty()
        context?.let {
            binding.ivIntro.setImageDrawable(ContextCompat.getDrawable(it, imgRes))
        }
    }

    companion object {
        private const val ARG_PARAM_INTRO_TITLE = "ARG_PARAM_INTRO_TITLE"
        private const val ARG_PARAM_INTRO_DESC = "ARG_PARAM_INTRO_DESC"
        private const val ARG_PARAM_INTRO_IMG_RES = "ARG_PARAM_INTRO_IMG_RES"


        @JvmStatic
        fun newInstance(title: String, desc: String, imgRes: Int) =
            IntroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_INTRO_TITLE, title)
                    putString(ARG_PARAM_INTRO_DESC, desc)
                    putInt(ARG_PARAM_INTRO_IMG_RES, imgRes)
                }
            }
    }
}
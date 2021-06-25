package com.catnip.intropageexample.ui.form

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.catnip.intropageexample.R
import com.catnip.intropageexample.databinding.FragmentFormActivityBinding

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormActivityBinding
    private lateinit var listener: FormFragmentListener
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFormActivityBinding.inflate(inflater,container,false)
        return binding.root
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            listener.onNameSubmitted(binding.etPlayerName.text.toString())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as FormFragmentListener)
    }

}
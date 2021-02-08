package com.example.cocktailapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.SearchFragmentBinding


class SearchFragment : Fragment() {
    lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        return inflater.inflate(R.layout.search_fragment, null)
    }
}



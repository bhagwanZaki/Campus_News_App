package com.example.aiktcnewapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aiktcnewapp.R
import com.example.aiktcnewapp.databinding.FragmentHomeScreenBinding
import com.example.aiktcnewapp.databinding.FragmentNewsDetailBinding
import com.example.aiktcnewapp.network.News
import kotlin.properties.Delegates


class NewsDetail : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private var idData by Delegates.notNull<Int>()
    private lateinit var titleData: String
    private lateinit var contentData: String
    private lateinit var imageData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailBinding.inflate(inflater,container,false)

        arguments?.let {
            idData = it.getInt("id")
            titleData = it.getString("title").toString()
            contentData = it.getString("description").toString()
            imageData = it.getString("image").toString()
        }

        val newObj = News(idData,titleData,contentData,imageData)


        binding.detailData=newObj
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
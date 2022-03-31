package com.vncmsk.godigitaltest.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.vncmsk.godigitaltest.databinding.FragmentDetailBinding
import com.vncmsk.godigitaltest.main.MainActivity

class DetailFragment : Fragment() {

  private lateinit var detailBinding: FragmentDetailBinding
  private val args: DetailFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //(activity as MainActivity?)?.showIcon()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
    return detailBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val movie = args.moviesargs

    with (detailBinding) {
      titleTextView.text = movie.name
      summaryTextView.text = movie.description
      com.squareup.picasso.Picasso.get().load(movie.urlPicture).into(movieImageView)
    }
  }
}
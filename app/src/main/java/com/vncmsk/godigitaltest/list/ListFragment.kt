package com.vncmsk.godigitaltest.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vncmsk.godigitaltest.databinding.FragmentListBinding
import com.vncmsk.godigitaltest.main.MainActivity
import com.vncmsk.godigitaltest.model.MovieApp
import com.vncmsk.godigitaltest.model.Movies

class ListFragment : Fragment() {

  private lateinit var listBinding: FragmentListBinding
  private lateinit var listViewModel: ListViewModel
  private lateinit var puntosAdapter: MoviesAdapter
  private var listPuntosGlobal: ArrayList<Movies> = arrayListOf()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    listBinding = FragmentListBinding.inflate(inflater, container, false)
    listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

    return listBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (activity as MainActivity?)

    listViewModel.getMoviesFromServer()
    listViewModel.onMoviesLoaded.observe(viewLifecycleOwner,{ result ->
      onPuntosLoadedSubscribed(result)
    })

    puntosAdapter = MoviesAdapter(listPuntosGlobal)

    listBinding.moviesRecyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = puntosAdapter
      setHasFixedSize(false)
    }
  }

  private fun onPuntosLoadedSubscribed(result: ArrayList<MovieApp>?) {
    result?.let { ListPuntosLocal->

      puntosAdapter.appenditems(ListPuntosLocal)


      /*
      // todo: revisar feedback
      this.listPuntosGlobal = ListPuntosLocal
      puntosAdapter.notifyDataSetChanged()
       */
    }
  }
}
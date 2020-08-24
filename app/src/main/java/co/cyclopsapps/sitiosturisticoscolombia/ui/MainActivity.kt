package co.cyclopsapps.sitiosturisticoscolombia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.cyclopsapps.sitiosturisticoscolombia.R
import co.cyclopsapps.sitiosturisticoscolombia.di.Injection
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSite
import co.cyclopsapps.sitiosturisticoscolombia.viewmodel.TouristicSiteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TouristicSiteViewModel
    private lateinit var adapter: TouristicSiteAdapter

    companion object {
        const val TAG = "CONSOLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        adapter = TouristicSiteAdapter(viewModel.touristicsites.value?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(TouristicSiteViewModel::class.java)
        viewModel.touristicsites.observe(this, renderTouristicSites)

        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    //observers
    private val renderTouristicSites = Observer<List<TouristicSite>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutError.visibility = View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTouristicSites()
    }



}
package co.cyclopsapps.sitiosturisticoscolombia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSiteDataSource

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */
class ViewModelFactory(private val repository: TouristicSiteDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TouristicSiteViewModel(repository) as T
    }
}
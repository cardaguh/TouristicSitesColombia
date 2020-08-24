package co.cyclopsapps.sitiosturisticoscolombia.di

import androidx.lifecycle.ViewModelProvider
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSiteDataSource
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSiteRepository
import co.cyclopsapps.sitiosturisticoscolombia.viewmodel.ViewModelFactory

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */

object Injection {

    private val touristicSiteDataSource : TouristicSiteDataSource = TouristicSiteRepository()
    private val touristicSiteViewModelFactory = ViewModelFactory(touristicSiteDataSource)

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return touristicSiteViewModelFactory
    }
}
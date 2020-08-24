package co.cyclopsapps.sitiosturisticoscolombia.model

import co.cyclopsapps.sitiosturisticoscolombia.data.OperationCallback

interface TouristicSiteDataSource {

    fun retrieveTouristicSites(callback: OperationCallback<TouristicSite>)
    fun cancel()
}

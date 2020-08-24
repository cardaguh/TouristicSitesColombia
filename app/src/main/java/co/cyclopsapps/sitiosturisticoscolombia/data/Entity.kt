package co.cyclopsapps.sitiosturisticoscolombia.data

import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSite

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */

data class TouristicSiteResponse(val status: Int?, val msg: String?, val data: List<TouristicSite>?) {
    fun isSuccess(): Boolean = (status == 200)
}
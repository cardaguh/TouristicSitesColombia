package co.cyclopsapps.sitiosturisticoscolombia.model

import android.util.Log
import co.cyclopsapps.sitiosturisticoscolombia.data.ApiClient
import co.cyclopsapps.sitiosturisticoscolombia.data.OperationCallback
import co.cyclopsapps.sitiosturisticoscolombia.data.TouristicSiteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */

const val TAG = "CONSOLE"

class TouristicSiteRepository : TouristicSiteDataSource {

    private var call: Call<TouristicSiteResponse>? = null

    override fun retrieveTouristicSites(callback: OperationCallback<TouristicSite>) {
        call = ApiClient.build()?.touristicSites()
        call?.enqueue(object : Callback<TouristicSiteResponse>{
            override fun onFailure(call: Call<TouristicSiteResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<TouristicSiteResponse>,
                response: Response<TouristicSiteResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful && (it.isSuccess())) {
                        Log.v(TAG, "data ${it.data}")
                        callback.onSuccess(it.data)
                    } else {
                        callback.onError(it.msg)
                    }
                }
            }

        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}
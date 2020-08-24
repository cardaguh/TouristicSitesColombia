package co.cyclopsapps.sitiosturisticoscolombia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.cyclopsapps.sitiosturisticoscolombia.data.OperationCallback
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSite
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSiteDataSource

class TouristicSiteViewModel(private val repository: TouristicSiteDataSource) : ViewModel() {

    private val _touristicsites = MutableLiveData<List<TouristicSite>>().apply { value = emptyList() }
    val touristicsites: LiveData<List<TouristicSite>> = _touristicsites

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading : LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError : LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList : LiveData<Boolean> = _isEmptyList

    fun loadTouristicSites() {
        _isViewLoading.postValue(true)
        repository.retrieveTouristicSites(object : OperationCallback<TouristicSite>{
            override fun onSuccess(data: List<TouristicSite>?) {
                _isViewLoading.postValue(false)

                if (data != null) {
                    if (data.isEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        _touristicsites.value = data
                    }
                }
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }

        })
    }
}

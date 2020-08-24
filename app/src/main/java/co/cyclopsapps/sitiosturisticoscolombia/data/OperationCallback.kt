package co.cyclopsapps.sitiosturisticoscolombia.data

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */
interface OperationCallback<T> {
    fun onSuccess(data: List<T>?)
    fun onError(error: String?)
}
package co.cyclopsapps.sitiosturisticoscolombia

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import co.cyclopsapps.sitiosturisticoscolombia.data.OperationCallback
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSite
import co.cyclopsapps.sitiosturisticoscolombia.model.TouristicSiteDataSource
import co.cyclopsapps.sitiosturisticoscolombia.viewmodel.TouristicSiteViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */
class MVVMUnitTest {

    @Mock
    private lateinit var repository: TouristicSiteDataSource
    @Mock
    private lateinit var context: Application

    @Captor
    private lateinit var operationCallbackCaptor: ArgumentCaptor<OperationCallback<TouristicSite>>

    private lateinit var viewModel: TouristicSiteViewModel

    private lateinit var isViewLoadingObserver: Observer<Boolean>
    private lateinit var onMessageErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>
    private lateinit var onRenderTouristicSiteObserver: Observer<List<TouristicSite>>

    private lateinit var touristicSiteEmptyList: List<TouristicSite>
    private lateinit var touristicSiteList: List<TouristicSite>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)

        viewModel = TouristicSiteViewModel(repository)

        mockData()
        setupObservers()
    }

    @Test
    fun `retrieve touristic sites with ViewModel and Repository returns empty data`() {
        with(viewModel) {
            loadTouristicSites()
            isViewLoading.observeForever(isViewLoadingObserver)
            isEmptyList.observeForever(emptyListObserver)
            touristicsites.observeForever(onRenderTouristicSiteObserver)
        }

        verify(repository, times(1)).retrieveTouristicSites(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(touristicSiteEmptyList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.isEmptyList.value == true)
        Assert.assertTrue(viewModel.touristicsites.value?.size == 0)
    }

    @Test
    fun `retrieve touristic sites with ViewModel and Repository returns full data`() {
        with(viewModel) {
            loadTouristicSites()
            isViewLoading.observeForever(isViewLoadingObserver)
            touristicsites.observeForever(onRenderTouristicSiteObserver)
        }

        verify(repository, times(1)).retrieveTouristicSites(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(touristicSiteList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.touristicsites.value?.size == 3)
    }

    @Test
    fun `retrieve touristic sites with ViewModel and Repository returns an error`() {
        with(viewModel) {
            loadTouristicSites()
            isViewLoading.observeForever(isViewLoadingObserver)
            onMessageError.observeForever(onMessageErrorObserver)
        }
        verify(repository, times(1)).retrieveTouristicSites(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onError("An error ocurred")
        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertNotNull(viewModel.onMessageError.value)
    }

    private fun setupObservers() {
        isViewLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver = mock(Observer::class.java) as Observer<Any>
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
        onRenderTouristicSiteObserver = mock(Observer::class.java) as Observer<List<TouristicSite>>
    }

    private fun mockData() {
        touristicSiteEmptyList = emptyList()
        val mockList: MutableList<TouristicSite> = mutableListOf()
        mockList.add(TouristicSite(0, "Minas de sal de Nemocón, Cundinamarca", ""))
        mockList.add(TouristicSite(1, "Parque Nacional Natural El Tuparro, Vichada", ""))
        mockList.add(TouristicSite(2, "Punta Gallinas, La Guajira", ""))

        touristicSiteList = mockList.toList()
    }
}
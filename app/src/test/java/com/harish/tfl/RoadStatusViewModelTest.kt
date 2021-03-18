package com.harish.tfl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.harish.tfl.data.RoadStatusModel
import com.harish.tfl.repository.RoadStatusRepository
import com.harish.tfl.viewmodel.RoadStatusViewModel
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class RoadStatusViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RoadStatusViewModel

    @Mock
    lateinit var roadStatusRepository: RoadStatusRepository

    @Mock
    private lateinit var roadStatusModelObserver: Observer<List<RoadStatusModel>>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Before
    fun setup() {
        viewModel = RoadStatusViewModel(roadStatusRepository)
        viewModel.roadStatusObservable().observeForever(roadStatusModelObserver)
        viewModel.errorObservable().observeForever(errorObserver)
    }

    private fun getResponseJsonObject(message: String): String {
        val jsonObject = JsonObject()
        jsonObject.addProperty("message", message)
        return jsonObject.toString()
    }

    @Test
    fun `Success RoadStatus response invokes the correct Success observable`() {

        val roadStatusResponse = listOf(RoadStatusModel("Tfl.Api.Presentation.Entities.RoadCorridor",
            "a1",
            "A1",
            "Good",
            "No Exceptional Delays",
            "[[-0.25616,51.5319],[-0.10234,51.6562]]",
            "[[-0.25616,51.5319],[-0.25616,51.6562],[-0.10234,51.6562],[-0.10234,51.5319],[-0.25616,51.5319]]",
            "/Road/a1"))
        `when`(roadStatusRepository.getRoadStatus("A1")).thenReturn(Single.just(roadStatusResponse))

        viewModel.getRoadStatus(true, "A1")

        verify(roadStatusModelObserver, times(1)).onChanged(roadStatusResponse)
    }

    @Test
    fun `Error RoadStatus response invokes the correct Error observable`() {
        val errorMessage = "Exception"
        `when`(roadStatusRepository.getRoadStatus("A99")).thenReturn(Single.error(java.lang.RuntimeException(errorMessage)))

        viewModel.getRoadStatus(true, "A99")

        verify(errorObserver, times(1)).onChanged(errorMessage)
    }

    @Test
    fun `Http Error RoadStatus response invokes the correct Error observable`() {
        val errorMessage = "The following road id is not recognised: A99"
        val jsonResponse: Response<Void> = Response.error(404,
            getResponseJsonObject(errorMessage).toResponseBody("application/json".toMediaTypeOrNull())
        )
        `when`(roadStatusRepository.getRoadStatus("A99")).thenReturn(
            Single.error(HttpException(jsonResponse)))

        viewModel.getRoadStatus(true, "A99")

        verify(errorObserver, times(1)).onChanged(errorMessage)
    }

}


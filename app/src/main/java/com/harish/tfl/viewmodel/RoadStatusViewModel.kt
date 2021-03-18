package com.harish.tfl.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harish.tfl.data.ErrorStatusModel
import com.harish.tfl.data.RoadStatusModel
import com.harish.tfl.repository.RoadStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RoadStatusViewModel @Inject constructor(
    private val roadStatusRepository: RoadStatusRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val roadStatusStatusObservable: MutableLiveData<List<RoadStatusModel>> = MutableLiveData()
    private val errorObservable: MutableLiveData<String> = MutableLiveData()
    private val loadingObservable: MutableLiveData<Boolean> = MutableLiveData()

    fun getRoadStatus(isConnected: Boolean, roadName: String) {
        if (isConnected && roadName.isNotEmpty()) {
            loadingObservable.value = true
            compositeDisposable.add(
                roadStatusRepository.getRoadStatus(roadName)
                    .subscribe({
                            loadingObservable.value = false
                            roadStatusStatusObservable.value = it
                            errorObservable.value = "" },
                        {
                            loadingObservable.value = false
                            if (it is HttpException) {
                                val errorResponse: ErrorStatusModel? = Gson().fromJson(
                                    it.response()?.errorBody()?.charStream(),
                                    object : TypeToken<ErrorStatusModel>() {}.type
                                )
                                errorObservable.value = errorResponse?.message
                            } else {
                                errorObservable.value = it.message
                        } })
            )
        }
    }

    fun roadStatusObservable(): LiveData<List<RoadStatusModel>> = roadStatusStatusObservable

    fun errorObservable(): LiveData<String> = errorObservable

    fun loadingObservable(): LiveData<Boolean> = loadingObservable

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
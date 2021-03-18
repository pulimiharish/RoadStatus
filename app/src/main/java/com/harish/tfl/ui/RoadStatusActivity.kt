package com.harish.tfl.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.harish.tfl.R
import com.harish.tfl.common.Util
import com.harish.tfl.data.RoadStatusModel
import com.harish.tfl.ui.composables.ErrorStatusView
import com.harish.tfl.ui.composables.RoadNameInputView
import com.harish.tfl.ui.composables.SuccessStatusView
import com.harish.tfl.viewmodel.RoadStatusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoadStatusActivity : AppCompatActivity() {

    @VisibleForTesting
    val roadStatusActivityViewModel: RoadStatusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LiveDataComponent(
                    roadStatusActivityViewModel.roadStatusObservable(),
                    roadStatusActivityViewModel.errorObservable(),
                    roadStatusActivityViewModel.loadingObservable()
                )
            }
        }
    }

    @Composable
    fun LiveDataComponent(
        roadStatusLiveData: LiveData<List<RoadStatusModel>>,
        statusLiveData: LiveData<String>,
        progressLiveData: LiveData<Boolean>
    ) {
        val roadStatusList by roadStatusLiveData.observeAsState(initial = emptyList())
        val statusMessage by statusLiveData.observeAsState(initial = "")
        val showProgressBar by progressLiveData.observeAsState(initial = false)
        LiveDataComponentList(roadStatusList, statusMessage, showProgressBar)
    }

    @Composable
    fun LiveDataComponentList(
        roadStatusList: List<RoadStatusModel>,
        statusMessage: String,
        showProgressBar: Boolean
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            RoadNameInputView(getString(R.string.enter_text),
                getString(R.string.road_label),
                ::getRoadStatus,
                showProgressBar)

            if (statusMessage.isEmpty() && roadStatusList.isNotEmpty()) {
                SuccessStatusView(getString(R.string.status), roadStatusList[0], Modifier.padding(16.dp))
            } else {
                ErrorStatusView(getString(R.string.status), statusMessage, Modifier.padding(16.dp))
            }
        }
    }

    private fun getRoadStatus(roadName: String) {
        roadStatusActivityViewModel.getRoadStatus(Util.isConnected(this), roadName)
    }

}

package edu.com.trailslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.com.trailslist.compose.appcomponents.components.TrailApp
import edu.com.trailslist.database.implementation.TrailDatabase
import edu.com.trailslist.viewmodels.TrailViewModel

class MainActivity : ComponentActivity() {

    private val trailDaoProvider = TrailDatabase.TrailDatabaseProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trailDaoProvider.initialize(applicationContext)
        setContent {
            val viewModel = viewModel<TrailViewModel>(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return TrailViewModel(trailDaoProvider = trailDaoProvider) as T
                    }
                }
            )

            viewModel.insertTrailsIfEmpty {
                recreate()
            }
            TrailApp(viewModel = viewModel)
        }
    }
}
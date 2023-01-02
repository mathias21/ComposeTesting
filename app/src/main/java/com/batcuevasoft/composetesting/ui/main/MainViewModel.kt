//package com.batcuevasoft.toddley.ui.main
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.batcuevasoft.composetesting.core.DispatcherProvider
//import com.batcuevasoft.common.core.extensions.stateIn
//import com.batcuevasoft.toddley.core.toddler.Toddler
//import com.batcuevasoft.toddley.core.toddler.ToddlerRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class MainViewModel @Inject constructor(
//    private val dispatcherProvider: DispatcherProvider,
//    private val toddlerRepository: ToddlerRepository
//) : ViewModel() {
//
//    val mainUiState: StateFlow<MainUiState> = toddlerRepository.toddlerFlow
//        .map {
//            MainUiState.DataLoaded(it)
//        }
//        .stateIn(viewModelScope, MainUiState.Loading)
//
//    fun onInit() {
//        viewModelScope.launch(dispatcherProvider.io) {
//            toddlerRepository.toddlerFlow
//        }
//    }
//
//
//    sealed interface MainUiState {
//        object Loading : MainUiState
//        data class DataLoaded(val toddlerList: List<Toddler>) : MainUiState
//        object Error : MainUiState
//    }
//}
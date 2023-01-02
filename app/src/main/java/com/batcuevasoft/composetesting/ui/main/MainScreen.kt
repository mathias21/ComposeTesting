//package com.batcuevasoft.composetesting.ui.main
//
//import android.annotation.SuppressLint
//import androidx.activity.compose.BackHandler
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.BottomSheetScaffold
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.rememberBottomSheetScaffoldState
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import com.batcuevasoft.composetesting.R
//import com.batcuevasoft.composetesting.ui.component.LoadingComponent
//import com.batcuevasoft.toddley.ui.main.MainViewModel
//import kotlinx.coroutines.launch
//
//@OptIn(
//    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
//    ExperimentalLifecycleComposeApi::class
//)
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun MainScreen(
//    viewModel: MainViewModel = hiltViewModel()
//) {
//
//    val uiState by viewModel.mainUiState.collectAsStateWithLifecycle()
//    val scaffoldState = rememberBottomSheetScaffoldState()
//    val scope = rememberCoroutineScope()
//
//    BackHandler(enabled = true) {
//        if (scaffoldState.bottomSheetState.isExpanded) {
//            scope.launch { scaffoldState.bottomSheetState.collapse() }
//        }
//    }
//
//    LaunchedEffect(key1 = viewModel) {
//        viewModel.onInit()
//    }
//
//    BottomSheetScaffold(
//        modifier = Modifier
//            .statusBarsPadding()
//            .background(MaterialTheme.colorScheme.surface),
//        sheetContent = { MainSheetContent() },
//        sheetPeekHeight = 0.dp,
//        scaffoldState = scaffoldState,
//        sheetShape = RoundedCornerShape(16.dp),
//        backgroundColor = MaterialTheme.colorScheme.background
//    ) {
//        when (val currentState = uiState) {
//            is MainViewModel.MainUiState.Loading -> LoadingComponent()
//            is MainViewModel.MainUiState.DataLoaded -> MainContent(toddlerList = currentState.toddlerList) {
//                scope.launch { scaffoldState.bottomSheetState.expand() }
//            }
//            is MainViewModel.MainUiState.Error -> TODO()
//        }
//    }
//}
//
//@Composable
//private fun MainContent(
//    toddlerList: List<Toddler>,
//    onPlusButtonPressed: () -> Unit
//) {
//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val (plusButton, list) = createRefs()
//        LazyColumn(
//            Modifier
//                .fillMaxSize()
//                .constrainAs(list) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//        ) {
//            item {
//                Spacer(modifier = Modifier.height(48.dp))
//            }
//            item {
//                Text(
//                    modifier = Modifier.padding(start = 32.dp),
//                    text = "${stringResource(id = R.string.welcome_to_title)} ${stringResource(id = R.string.app_name)}",
//                    style = MaterialTheme.typography.headlineLarge,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
//            }
//
//            items(toddlerList) {
//                when (it) {
//                    is Toddler.Born -> Text(text = it.name)
//                    is Toddler.NotBorn -> Text(
//                        text = (it.name ?: "Thinking name: ") + it.inception.formatToDateString()
//                    )
//                }
//            }
//        }
//        PlusButton(
//            Modifier
//                .constrainAs(plusButton) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    bottom.linkTo(parent.bottom)
//                }
//                .padding(bottom = 72.dp),
//            onPlusButtonPressed
//        )
//    }
//}
//
//@Composable
//private fun MainSheetContent() {
//    Column(
//        Modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.secondaryContainer)
//            .padding(horizontal = 16.dp)
//    ) {
//        var extraSpace by remember { mutableStateOf(false) }
//
//        Spacer(modifier = Modifier.height(32.dp))
//        Text(
//            modifier = Modifier.padding(start = 4.dp),
//            text = stringResource(id = R.string.choose_an_event),
//            style = MaterialTheme.typography.headlineSmall,
//            color = MaterialTheme.colorScheme.onSecondaryContainer
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        EventHorizontalList(
//            listOf(
//                EventType.MomEvent.MedicalAppointment,
//                EventType.MomEvent.MomWeightMeasurement,
//            ),
//            { extraSpace = !extraSpace }
//        )
//
//        Spacer(modifier = Modifier.height(80.dp))
//        if (extraSpace) {
//            Spacer(modifier = Modifier.height(400.dp))
//        }
//    }
//}
//
//@Preview
//@Composable
//private fun MainSheetContentPreview() {
//    AppTheme() {
//        MainSheetContent()
//    }
//}
//
//@Preview
//@Composable
//private fun MainScreenPreview() {
//    AppTheme() {
//        MainScreen()
//    }
//}
package com.example.nobellaureates.presentation.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nobellaureates.domain.model.Laureate
import com.example.nobellaureates.presentation.common.categories
import com.example.nobellaureates.presentation.common.years

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaureateListScreen(
    navController: NavController,
    viewModel: LaureateListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val filterState by viewModel.filterState.collectAsState()

    var expandedYear by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Нобелевские премии") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ExposedDropdownMenuBox(
                        expanded = expandedYear,
                        onExpandedChange = { expandedYear = it }
                    ) {
                        TextField(
                            value = filterState.selectedYear?.toString() ?: "Все годы",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedYear) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expandedYear,
                            onDismissRequest = { expandedYear = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Все годы") },
                                onClick = {
                                    viewModel.updateFilter(null, filterState.selectedCategory)
                                    expandedYear = false
                                }
                            )
                            years.forEach { year ->
                                DropdownMenuItem(
                                    text = { Text(year.toString()) },
                                    onClick = {
                                        viewModel.updateFilter(year, filterState.selectedCategory)
                                        expandedYear = false
                                    }
                                )
                            }
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    ExposedDropdownMenuBox(
                        expanded = expandedCategory,
                        onExpandedChange = { expandedCategory = it }
                    ) {
                        TextField(
                            value = categories.find { it.second == filterState.selectedCategory }?.first ?: "Все категории",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expandedCategory,
                            onDismissRequest = { expandedCategory = false }
                        ) {
                            categories.forEach { (displayName, categoryValue) ->
                                DropdownMenuItem(
                                    text = { Text(displayName) },
                                    onClick = {
                                        viewModel.updateFilter(filterState.selectedYear, categoryValue)
                                        expandedCategory = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            when (val state = uiState) {
                is LaureateListViewModel.UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is LaureateListViewModel.UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.laureates) { laureate ->
                            LaureateCard(
                                laureate = laureate,
                                onClick = {
                                    navController.navigate("detail/${laureate.id}")
                                }
                            )
                        }
                    }
                }

                is LaureateListViewModel.UiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = state.message, color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.loadLaureates() }) {
                                Text("Повторить")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LaureateCard(
    laureate: Laureate,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "${laureate.awardYear} – ${laureate.category.uppercase()}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = laureate.fullName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = laureate.shortMotivation,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )
        }
    }
}
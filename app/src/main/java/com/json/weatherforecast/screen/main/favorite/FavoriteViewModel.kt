package com.json.weatherforecast.screen.main.favorite

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.json.weatherforecast.model.Favorite
import com.json.weatherforecast.repository.WeatherDbRepository
import com.json.weatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: WeatherDbRepository
) : ViewModel() {

    private val _favList = MutableStateFlow<List<Favorite>>(listOf())
    val favList: StateFlow<List<Favorite>> = _favList

    /*init {
        viewModelScope.launch(Dispatchers.IO) {

            *//*repository.getFavorites().distinctUntilChanged().collect {listOfFavs ->
                if(listOfFavs.isNullOrEmpty()){
                    Log.i("TAG", "Empty favs")
                }else{
                    _favList.value = listOfFavs
                    Log.i("TAG", "${favList.value}")
                }
            }*//*
        }
    }*/

    suspend fun getFavoriteList(){
        repository.getFavorites().collectLatest {
            _favList.emit(it)
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {repository.insertFavorite(favorite = favorite) }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {repository.updateFavorite(favorite = favorite) }

    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch { repository.deleteFavorite(favorite = favorite) }
}
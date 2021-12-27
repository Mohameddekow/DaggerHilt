package com.example.daggerhilt.ui

import androidx.lifecycle.*
import com.example.daggerhilt.model.Blog
import com.example.daggerhilt.repository.MainRepository
import com.example.daggerhilt.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository
) : ViewModel() {

   private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

   val dataState: LiveData<DataState<List<Blog>>>
      get() = _dataState




    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){

                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlog()
                        .onEach {
                            _dataState.value = it
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    //nothing happens
                }

            }
        }
    }



    //looking 4 another way to get the data
  fun getBlogs(){
      viewModelScope.launch (Dispatchers.IO){
          mainRepository.getBlog()
              .onEach {
                  _dataState.value = it
              }

      }
  }



}

sealed class MainStateEvent{

    //state all event that can be fired
    object GetBlogEvents: MainStateEvent()

    object None: MainStateEvent()
}
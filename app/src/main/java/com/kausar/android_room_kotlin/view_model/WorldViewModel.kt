package com.kausar.android_room_kotlin

import androidx.lifecycle.*
import com.kausar.android_room_kotlin.repository.WordRepository
import com.kausar.android_room_kotlin.room_utils.Word
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()
//    LiveData<List<Word>>: Makes possible the automatic updates in the UI components.

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

}

/*
WordViewModel: provides methods for accessing the data layer
 and it returns LiveData so that MainActivity can set up the observer relationship.
 */


class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/*
By using viewModels and ViewModelProvider.Factory,the framework will
take care of the lifecycle of the ViewModel.
It will survive configuration changes and even if the Activity is recreated,
you'll always get the right instance of the WordViewModel class.

The ViewModel does not need to know what that Repository interacts with.
It just needs to know how to interact with the Repository
 */





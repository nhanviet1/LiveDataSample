package com.example.livedatatest

import androidx.lifecycle.*

class viewModel : ViewModel() {

    private val _liveData1 = MutableLiveData<String>()
    val liveData1: LiveData<String> get() = _liveData1

    val liveData = MutableLiveData<Int>()

    private val _liveData2 = MutableLiveData<Int>()
    val liveData2: LiveData<Int> get() = _liveData2

    private val _mediatorLiveData = MediatorLiveData<String>()
    val mediatorLiveData: LiveData<String> get() = _mediatorLiveData

    private val _checkSwitch = MutableLiveData<Boolean>()
    val checkSwitch: LiveData<Boolean> get() = _checkSwitch

    val map = Transformations.map(_liveData2) {
        "A: $it"
    }

    val switchMap = Transformations.switchMap(_checkSwitch) {
            switch -> getLiveData12(switch)
    }


    init {
        _mediatorLiveData.addSource(map) {
            _mediatorLiveData.value = it
        }
        _mediatorLiveData.addSource(liveData2) {
            _mediatorLiveData.value = it.toString()
        }
    }

    fun randomNumber1() {
        _liveData1.value = (1..10).random().toString()
    }

    fun randomNumber2() {
        _liveData2.value = (20..40).random()
    }

    fun getLiveData12(switch: Boolean): LiveData<String>{
        if (switch){
            return map
        }
        return liveData1
    }

    fun changeSwitch(switch: Boolean){
        _checkSwitch.postValue(switch)
    }
}
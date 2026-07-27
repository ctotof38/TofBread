package com.totof.bread.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.totof.bread.data.Input

class BreadViewModel : ViewModel() {
    private val _input = MutableLiveData<Input>()
    val input: LiveData<Input> = _input

    fun setInput(newInput: Input) {
        _input.value = newInput
    }

    fun notifyDataChanged() {
        _input.value = _input.value
    }
}

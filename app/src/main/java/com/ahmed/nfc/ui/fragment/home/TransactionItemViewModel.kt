package com.jai.blueprint.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import com.jai.blueprint.data.model.Transaction

class TransactionItemViewModel(var trans: Transaction, var mListener: TransactionItemViewModelListener) {

    var imageUrl = MutableLiveData<String>()

    init {
        //imageUrl.value = trans.image_path
    }

    fun onItemClick() {
        mListener.onItemClick(trans)
    }


    interface TransactionItemViewModelListener {
        fun onItemClick(trans: Transaction)
    }
}
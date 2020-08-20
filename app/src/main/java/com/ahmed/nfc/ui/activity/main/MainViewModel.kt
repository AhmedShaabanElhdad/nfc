package com.ahmed.nfc.ui.activity.main

import com.ahmed.nfc.data.datamanager.DataManager
import com.ahmed.nfc.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(val dataManager: DataManager) : BaseViewModel(dataManager)

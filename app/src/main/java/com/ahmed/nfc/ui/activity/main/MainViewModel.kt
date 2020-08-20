package com.jai.blueprint.ui.activity.main

import com.jai.blueprint.data.datamanager.DataManager
import com.jai.blueprint.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(val dataManager: DataManager) : BaseViewModel(dataManager)

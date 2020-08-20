package com.jai.blueprint.ui.fragment.addTransaction

import android.app.Activity
import android.nfc.NfcAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.jai.blueprint.BR
import com.jai.blueprint.R
import com.jai.blueprint.data.model.Transaction
import com.jai.blueprint.databinding.FragmentAddTransactionBinding
import com.jai.blueprint.ui.base.BaseFragment
import com.jai.blueprint.ui.fragment.home.HomeViewModel
import com.jai.blueprint.utils.AppConstant
import kotlinx.coroutines.launch
import javax.inject.Inject


class AddTransactionFragment :
    BaseFragment<FragmentAddTransactionBinding, AddTransactionViewModel>(),
    LoyaltyCardReader.AccountCallback {


    // Recommend NfcAdapter flags for reading from other Android devices. Indicates that this
    // activity is interested in NFC-A devices (including other Android devices), and that the
    // system should not check for the presence of NDEF-formatted data (e.g. Android Beam).
    var READER_FLAGS = NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK
    var mLoyaltyCardReader: LoyaltyCardReader? = null

    @Inject
    internal lateinit var transaction: Transaction

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var addViewModel: AddTransactionViewModel


    lateinit var fragmentAddTransactionBinding: FragmentAddTransactionBinding

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_add_transaction

    override fun getViewModel(): AddTransactionViewModel =
        ViewModelProviders.of(this, mViewModelFactory).get(AddTransactionViewModel::class.java)

    override fun getLifeCycleOwner(): LifecycleOwner = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentAddTransactionBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
        fragmentAddTransactionBinding.cardAccountField.text = "Waiting..."
        mLoyaltyCardReader = LoyaltyCardReader(this)
        fragmentAddTransactionBinding.transaction = transaction

        fragmentAddTransactionBinding.editAmount.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty())
                    transaction.value = Integer.parseInt(s.toString())
            }
        })

        // Disable Android Beam and register our card reader callback
        enableReaderMode()
        initClickHandler()
    }

    override fun onPause() {
        super.onPause()
        disableReaderMode()
    }

    override fun onResume() {
        super.onResume()
        setEmptyView(false)
        enableReaderMode()
    }

    private fun enableReaderMode() {
        Log.i(AppConstant.DEBUG_TAG, "Enabling reader mode")
        val activity: Activity? = activity
        val nfc = NfcAdapter.getDefaultAdapter(activity)
        nfc?.enableReaderMode(
            activity,
            mLoyaltyCardReader,
            READER_FLAGS,
            null
        )
    }

    private fun disableReaderMode() {
        Log.i(AppConstant.DEBUG_TAG, "Disabling reader mode")
        val activity: Activity? = activity
        val nfc = NfcAdapter.getDefaultAdapter(activity)
        nfc?.disableReaderMode(activity)
    }


    override fun onAccountReceived(account: String?) {
        // This callback is run on a background thread, but updates to UI elements must be performed
        // on the UI thread.
        activity!!.runOnUiThread { fragmentAddTransactionBinding.cardAccountField.setText(account) }
    }

    fun initClickHandler() {
        fragmentAddTransactionBinding.btnAdd.setOnClickListener {
            addViewModel.viewModelScope.launch {
                showLoading()
                val data = addViewModel.addTransactionToRemote()
                if (data.first == 1) {
                    showMessage(data.second)
                }
                hideLoading()


            }
        }
    }


}
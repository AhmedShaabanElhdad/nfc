package com.jai.blueprint.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jai.blueprint.BR
import com.jai.blueprint.data.model.Transaction
import com.jai.blueprint.databinding.FragmentHomeBinding
import com.jai.blueprint.ui.base.BaseFragment
import com.jai.blueprint.utils.GridSpacingItemDecoration
import com.jai.dadday.util.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var homeViewModel: HomeViewModel
    lateinit var mLayoutManager: GridLayoutManager

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mTransactionAdapter: TransactionAdapter

    @Inject
    lateinit var lisTransactionData: List<Transaction>

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = com.jai.blueprint.R.layout.fragment_home

    override fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, mViewModelFactory).get(HomeViewModel::class.java)
    }

    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHomeBinding = getViewDataBinding()
        setUp()
    }

    override fun onResume() {
        super.onResume()
        setEmptyView(false)
    }

    private fun setUp() {
        mLayoutManager = GridLayoutManager(activity!!, 1)
        mLayoutManager.isItemPrefetchEnabled = false
        rvTransaction.setHasFixedSize(true)
        rvTransaction.layoutManager = mLayoutManager
        rvTransaction.addItemDecoration(mGridSpacingItemDecoration)
        rvTransaction.itemAnimator = DefaultItemAnimator()


        // call api for data
        homeViewModel.viewModelScope.launch(Dispatchers.Main) {
            if (lisTransactionData.isEmpty()) {

                showLoading()
                val data = homeViewModel.fetchTransactionFromRemote()
                if (data.first == 1) {
                    showMessage(data.second)
                }
                hideLoading()
            }

        }

        // load data from local
        homeViewModel.viewModelScope.launch {
            homeViewModel.fetchDataFromDatabase().observe(this@HomeFragment, Observer {
                mLayoutManager.reverseLayout = false
                lisTransactionData = it
                if (lisTransactionData.isEmpty()) setEmptyView(true) else setEmptyView()
                mTransactionAdapter = TransactionAdapter(lisTransactionData)
                rvTransaction.adapter = mTransactionAdapter

            })
        }


        //recycler view click handle
        rvTransaction.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                rvTransaction,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        var bundle = bundleOf(
                            "id" to lisTransactionData[position].id,
                            "type" to lisTransactionData[position].type
                        )

//                        findNavController().navigate(
//                            com.jai.blueprint.R.id.detailTransactionFragment,
//                            bundle
//                        )
                    }

                    override fun onLongItemClick(view: View, position: Int) {
                    }

                })
        )


    }
}

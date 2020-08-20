package com.jai.blueprint.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jai.blueprint.data.model.Transaction
import com.jai.blueprint.databinding.ItemTransactionViewBinding
import com.jai.blueprint.ui.base.BaseViewHolder

class TransactionAdapter(var transactionList: List<Transaction>) : RecyclerView.Adapter<BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val mTransactionViewBinding = ItemTransactionViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TransactionViewHolder(mTransactionViewBinding)

    }

    override fun getItemCount(): Int = transactionList.size


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }


    inner class TransactionViewHolder(val transactionViewBinding: ItemTransactionViewBinding?) :
        BaseViewHolder(transactionViewBinding!!.root), TransactionItemViewModel.TransactionItemViewModelListener {
        override fun onItemClick(transaction: Transaction) {

        }

        private lateinit var transactionItemViewModel: TransactionItemViewModel
        override fun onBind(position: Int) {
            val transaction = transactionList[position]
            transactionItemViewModel = TransactionItemViewModel(transaction, this)
            transactionViewBinding!!.viewModel = transactionItemViewModel
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            transactionViewBinding!!.executePendingBindings()
        }
    }
}



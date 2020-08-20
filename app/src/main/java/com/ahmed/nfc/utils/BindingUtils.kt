package com.jai.blueprint.utils


import android.view.View
import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jai.blueprint.data.model.Transaction

//todo remove image url

//@BindingAdapter("adapter")
//fun addTeamItems(recyclerView: RecyclerView, movies: List<Team>?) {
//    val adapter = recyclerView.adapter as? TeamAdapter
////    adapter?.clearItems()
////    adapter?.addItems(movies!!)
//}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val context = imageView.context
    if (url != null)
        Glide.with(context).load(url).into(imageView)
}

@BindingAdapter("uiState")
fun changeLoadingState(loadingProgressBar: ContentLoadingProgressBar, uiState: UIState) {
    loadingProgressBar.visibility = if (uiState is UIState.Loading) View.VISIBLE else View.GONE
}


@BindingAdapter("onClick")
fun onClick(view: View,onClick: () -> Any) {
    view.setOnClickListener {
        onClick()
    }
}

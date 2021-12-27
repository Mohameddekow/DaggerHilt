package com.example.daggerhilt.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(
    message: String,
    action: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

    action?.let {
        snackbar.setAction("Retry") {
            it
        }
    }

    snackbar.show()
}

//
//fun Fragment.handleNetworkError(
//    error: DataState.Error,
//    retry: (() -> Unit)? = null
//) {
//    when {
//        error.isNetworkError -> requireView()
//            .showSnackbar(
//                "Please check your internet connection",
//                retry
//            )
//    }
//
//}
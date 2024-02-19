package com.example.modelviewer.ui.modelviewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.modelviewer.R
import com.example.modelviewer.databinding.FragmentModelViewerBinding
import com.example.modelviewer.utils.viewbinding.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ModelViewerFragment : Fragment(R.layout.fragment_model_viewer) {

    private val binding by viewBinding(FragmentModelViewerBinding::bind)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.modelWebView) {
            loadUrl(getString(R.string.model_viewer_location))
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
            }
            CoroutineScope(Dispatchers.Main).launch {
                delay(7000) // Espera 2 segundos antes de cambiar la animación
                evaluateJavascript("document.querySelector('#paused-change-demo').animationName = 'anima2';", null)
            }
        }
    }
}

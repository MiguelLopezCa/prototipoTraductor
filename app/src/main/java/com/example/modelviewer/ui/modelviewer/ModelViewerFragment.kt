package com.example.modelviewer.ui.modelviewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.modelviewer.R
import com.example.modelviewer.databinding.FragmentModelViewerBinding
import com.example.modelviewer.utils.viewbinding.viewBinding

class ModelViewerFragment : Fragment(R.layout.fragment_model_viewer) {

    private val binding by viewBinding(FragmentModelViewerBinding::bind)
    private val webView: WebView = binding.modelWebView
    private val animationSequence = arrayOf("anima1", "anima2")
    private var currentAnimationIndex = 0

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.modelWebView) {
            loadUrl(getString(R.string.model_viewer_location))
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
            }
        }

        setAnimation(animationSequence[currentAnimationIndex])
    }

    private fun setAnimation(animationName: String) {
        webView.evaluateJavascript("document.querySelector('#paused-change-demo').animationName = '$animationName';", null)
    }

    override fun onResume() {
        super.onResume()
        webView.postDelayed({
            currentAnimationIndex = (currentAnimationIndex + 1) % animationSequence.size
            setAnimation(animationSequence[currentAnimationIndex])
        }, 5000)
    }
}
package com.example.studyandroid.view.Compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.Util.getStatusBarHeight

@ExperimentalMaterial3Api
class FragmentComposeSideEffect : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

                TestSideEffect()
            }
        }
    }

    @Composable
    fun TestSideEffect() {
        // LaunchedEffect는 Composable에서 컴포지션이 일어날 때 suspend fun 을 실행해 주는 Composable이다.
        // LaunchedEffect는 key 가 바뀔 때만 LaunchedEffect의 suspend fun 을 취소하고 재실행한다.
        var textForLaunchedEffect by rememberSaveable { mutableStateOf("") }
        val snackBarHostState = remember { SnackbarHostState() }
        LaunchedEffect(key1 = textForLaunchedEffect, block = {
            if (textForLaunchedEffect.isNotEmpty())
                snackBarHostState.showSnackbar(message = "$textForLaunchedEffect")
        })

        Scaffold(
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    Text(text = "ComposeSideEffect")
                    TestLaunchedEffect(textForLaunchedEffect) { textForLaunchedEffect = it }
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },

        )
    }

    @Composable
    fun TestLaunchedEffect(textForLaunchedEffect: String, onTextForLaunchedEffectChange: (String) -> Unit) {
        OutlinedTextField(
            value = textForLaunchedEffect, onValueChange = onTextForLaunchedEffectChange, label = { Text(text = "LaunchedEffect") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
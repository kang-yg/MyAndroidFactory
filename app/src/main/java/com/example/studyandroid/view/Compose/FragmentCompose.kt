package com.example.studyandroid.view.Compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.R
import com.example.studyandroid.Util.getStatusBarHeight

class FragmentCompose : Fragment() {
    private lateinit var navController: NavController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

                TestCompose()
            }
        }
    }

    @Composable
    fun TestCompose() {
        Column(modifier = Modifier.padding(8.dp)) {
            Button(
                onClick = { navController.navigate(R.id.action_fragmentCompose_to_fragmentComposeSignIn) },
                modifier = Modifier.fillMaxWidth(),

            ) {
                Text(text = "SignIn")
            }

            Button(
                onClick = { navController.navigate(R.id.action_fragmentCompose_to_fragmentComposeLazyColumn) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "LazyColumn")
            }

            Button(
                onClick = { navController.navigate(R.id.action_fragmentCompose_to_fragmentComposeSideEffect) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "SideEffects")
            }
        }
    }
}
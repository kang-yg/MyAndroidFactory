package com.example.studyandroid.view.Compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.R
import com.example.studyandroid.Util.getStatusBarHeight


@ExperimentalMaterial3Api
class FragmentComposeSignIn : Fragment() {
    private val viewModel: FragmentComposeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())
                setBackgroundColor(context.getColor(R.color.Gray))
                SignIn()
            }
        }
    }

    @Preview
    @Composable
    fun SignIn() {
        var id by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            LogoImage()
            SignInFields(id = id, onIdChange = { id = it }, password = password, onPasswordChange = { password = it })
            ButtonDoSignIn {

            }
        }
    }

    @Composable
    fun LogoImage() {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
    }

    @Composable
    fun SignInFields(id: String, password: String, onIdChange: (String) -> Unit, onPasswordChange: (String) -> Unit) {
        Column {
            IdTextField(id, onIdChange)
            PasswordTextField(password, onPasswordChange)
        }
    }

    @Composable
    fun IdTextField(id: String, onIdChange: (String) -> Unit) {
        OutlinedTextField(
            value = id,
            onValueChange = onIdChange,
            label = { Text(text = "ID") },
            colors = getOutlinedTextFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Password") },
            colors = getOutlinedTextFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun ButtonDoSignIn(onClick: () -> Unit) {
        Button(onClick = onClick, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(0.dp)) {
            Text(text = "Sign In")
        }
    }

    @Composable
    fun getOutlinedTextFieldColors(): TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.Black
    )
}
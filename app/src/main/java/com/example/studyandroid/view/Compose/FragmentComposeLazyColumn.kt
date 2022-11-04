package com.example.studyandroid.view.Compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyandroid.R
import com.example.studyandroid.Util.getStatusBarHeight

@ExperimentalUnitApi
@ExperimentalMaterial3Api
class FragmentComposeLazyColumn : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

                TestLazyColumn()
            }
        }
    }

    @Preview
    @Composable
    fun TestLazyColumn() {
        Scaffold(
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    Text(text = "TestLazyColumn")
                    UserList(userList = getUserList())
                }
            }
        )
    }

    @Composable
    fun UserList(userList: List<UserInfo>) {
        LazyColumn() {
            itemsIndexed(items = userList) { index, item ->
                UserCard(userInfo = item)
            }
        }
    }

    @Composable
    fun UserCard(userInfo: UserInfo) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
        ) {
            Text(text = userInfo.id, fontSize = TextUnit(value = 14f, type = TextUnitType.Sp), modifier = Modifier.padding(10.dp))
        }
    }

    private fun getUserList(): List<UserInfo> = arrayListOf(
        UserInfo("A", "a"),
        UserInfo("B", "b"),
        UserInfo("C", "c"),
        UserInfo("D", "d"),
        UserInfo("E", "e"),
        UserInfo("F", "f"),
        UserInfo("G", "g"),
        UserInfo("H", "h"),
        UserInfo("I", "i"),
        UserInfo("J", "j")
    )
}
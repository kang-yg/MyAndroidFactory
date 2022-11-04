package com.example.studyandroid.view.Compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
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
                    MessageLazyColumn(messageList = getMessages())
                }
            }
        )
    }

    @Composable
    fun MessageLazyColumn(messageList: List<Message>) {
        LazyColumn() {
            itemsIndexed(items = messageList) { index, item ->
                LazyColumnItem(message = item)
            }
        }
    }

    @Composable
    fun LazyColumnItem(message: Message) {
        var isExpanded by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 4.dp)
        ) {
            Row() {
                AsyncImage(
                    model = message.profileImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.clickable {
                    isExpanded = !isExpanded
                }) {
                    Text(text = message.sender)
                    Text(text = message.content, maxLines = if (isExpanded) Int.MAX_VALUE else 1)
                }
            }
        }
    }

    private fun getMessages() = listOf<Message>(
        Message(
            sender = "헐크",
            content = "뿌셔뿌셔 좋앙",
            profileImageUrl = "https://img.extmovie.com/files/attach/images/135/765/472/069/fc7e4e03bc84fcfd661890ab5414acb1.jpg"
        ),
        Message(sender = "아이언맨", content = "나 돈 많아", profileImageUrl = "https://i.ytimg.com/vi/9J67amvesFg/maxresdefault.jpg"),
        Message(
            sender = "짱구",
            content = "부리부리부리부리부리부리부리부리부리부리부리부리부리부리부리부리부리부리",
            profileImageUrl = "https://item.kakaocdn.net/do/cf29150096df7023563020fa9aafb3abd0bbab1214a29e381afae56101ded106"
        ),
        Message(
            sender = "해리포터",
            content = "닥쳐 말포이",
            profileImageUrl = "https://d2k6w3n3qf94c4.cloudfront.net/media/test/main_image/%E1%84%92%E1%85%A2%E1%84%85%E1%85%B5%E1%84%91%E1%85%A9%E1%84%90%E1%85%A5_%E1%84%87%E1%85%B5%E1%84%92%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%83%E1%85%B3_%E1%84%89%E1%85%B3%E1%84%90%E1%85%A9%E1%84%85%E1%85%B5__%E1%84%89%E1%85%B3%E1%86%AF%E1%84%91%E1%85%B3%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%AB%E1%84%89%E1%85%B5%E1%86%AF.jpeg"
        ),
        Message(
            sender = "코코",
            content = "리멤버 미~",
            profileImageUrl = "https://play-lh.googleusercontent.com/uu3l1YdVK6uv4BGZhSxykDMoqYgoAmWLtTgy4J4nLwHtoi1e4NOPKUJ1E-p0NvKGXji_UA"
        ),
        Message(sender = "설리반", content = "크아아아앙", profileImageUrl = "https://pbs.twimg.com/profile_images/731548506184286208/XtsD3VxB_400x400.jpg"),
        Message(
            sender = "뽀로로",
            content = "노는 게 제일 좋아",
            profileImageUrl = "https://w.namu.la/s/c3a5d1bb7b5db1a071901053fbfbe1f34b4e115d0a3253c3e990d04206ad0507f14eccc59ee630313337407b855d38cf551da5fb4ea3bd45c23491ba425452d73225e5a801c3aa490c3fef0590c39a4b7bd8b87e3d36c66541d8e00301f0e5bb"
        ),
    )
}
package com.example.studyandroid.view.HandlerThread

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import android.view.View
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentHandlerthreadBinding
import com.example.studyandroid.view.BaseFragment

class FragmentHandlerThread :
    BaseFragment<FragmentHandlerthreadBinding>(FragmentHandlerthreadBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragmentHandlerThread.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().getStatusBarHeight())

            // 방법 1
            val myHandlerThread1 = HandlerThread("MyHandlerThread1").also { it.start() }
            val myHandler1 = MyHandler(myHandlerThread1)
            myHandler1.sendMessage(Message.obtain().apply {
                data = Bundle().also { bundle -> bundle.putString("text", "Hello World!") }
            })

            // 방법 2
            val myHandlerThread2 = HandlerThread("MyHandlerThread2").also { it.start() }
            val myHandler2 = Handler(myHandlerThread2.looper)
            myHandler2.post {
                Log.d("HandlerThread", "Here is not main thread.")
            }
        }
    }

    class MyHandler(myHandlerThread: HandlerThread) : Handler(myHandlerThread.looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val data = msg.data
            Log.d("Handler message", "message is $data")
        }
    }
}
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

            // MessageQueue에 Runnable 전달
            val myHandlerThread1 = HandlerThread("MyHandlerThread1").also { it.start() }
            val myHandler1 = Handler(myHandlerThread1.looper)
            myHandler1.post {
                Log.d("HandlerThread", "Here is not main thread.")
            }

            // MessageQueue에 Message 전달
            val myHandlerThread2 = HandlerThread("MyHandlerThread2").also { it.start() }
            val myHandler2 = object : Handler(myHandlerThread2.looper) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    val data = msg.data
                    Log.d("Handler message", "message is $data")
                }
            }
            myHandler2.sendMessage(Message.obtain().apply {
                data = Bundle().also { bundle -> bundle.putString("text", "Hello World!") }
            })
        }
    }
}
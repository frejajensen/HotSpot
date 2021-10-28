package com.example.hotspot

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation


class MessageOverview : Fragment(), View.OnClickListener {

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
      view.findViewById<CardView>(R.id.chatOnClick).setOnClickListener(this)

    }

    override fun onClick(chatsView: View?){
        when(chatsView!!.id){
            R.id.chatOnClick -> navController.navigate(R.id.action_messageOverview_to_chat)
        }
    }
}
package com.example.hotspot.view

import android.content.ContentValues
import android.util.Log
import android.view.View
import com.example.hotspot.R
import com.example.hotspot.model.HotSpot
import com.example.hotspot.model.User
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.after_checked_in_recycler_view_item.view.*

class UserItem(val user: User, val hotSpot: HotSpot): Item() {

    override fun bind(
        viewHolder: com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder,
        position: Int
    ) {

        val item = viewHolder.itemView

        item.after_checked_in_person_item_user_name.text = user.name
        item.after_checked_in_person_item_user_pic.setImageBitmap(user.bitmapImg)
        item.after_checked_in_person_item_user_gender.text = "Gender: ${user.gender}"
        item.after_checked_in_person_item_user_age.text = "Age ${user.age}"

        viewHolder.itemView.setOnClickListener {
            Log.i(ContentValues.TAG, "Click listener $user")
        }


        hotSpot.checkedIn?.forEach {
            if (it.id == user.uid) {
                Log.i(ContentValues.TAG, "recycler view ${it.id} and ${user.uid}")

                Log.i(ContentValues.TAG, "recycler view2 ${it.isInterested}")
                if (it.isInterested == true) {

                    Log.i(ContentValues.TAG, "recycler view2")

                    item.interested_img_green.visibility = View.GONE
                    item.interested_img_red.visibility = View.VISIBLE
                   // notifyChanged()



                } else {
                    Log.i(ContentValues.TAG, "recycler view3")

                    item.interested_img_green.visibility = View.VISIBLE
                    item.interested_img_red.visibility = View.GONE
                    //notifyChanged()
                }
            }
        }

    }



    override fun getLayout(): Int {
        return R.layout.after_checked_in_recycler_view_item
    }
}



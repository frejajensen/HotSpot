package com.example.hotspot.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import com.example.hotspot.model.HotSpot
import com.example.hotspot.model.User
import com.example.hotspot.repository.Repository
import com.google.firebase.firestore.ListenerRegistration


/*typealias users = MutableList<User>
typealias ids = MutableList<String>

typealias usersAndIds = MutableList<String>*/

class AfterCheckInVM {

    companion object {
        var checkedInListenerRig: ListenerRegistration? = null

        fun setListenerToCheckedInListDB(hotSpot: HotSpot) {
            if (hotSpot.id != null) {
                checkedInListenerRig = Repository.getAndListenCheckedInIds(hotSpot.id!!
                ) { checkedIn -> onSuccessSnapShotIds(checkedIn) }
            }

        }


        private fun onSuccessSnapShotIds(checkedInIds: ArrayList<String>) {

            val ids = UsersAndIds.getIds()
            if (ids.containsAll(checkedInIds)) {
                Log.i(TAG, "Same ids")
                return
            }

            for (id in checkedInIds) {
                if (!ids.contains(id)) {
                    Repository.getCheckedInUserFromDB(id) { user -> onnSuccessSnapshotUser(user) }
                }
            }


            val toRemove = ArrayList<String>()
            for (id in ids) {
                if (!checkedInIds.contains(id)) {
                    toRemove.add(id)
                }
            }

            UsersAndIds.removeUser(toRemove)

        }



        private fun onnSuccessSnapshotUser(user: User) {
            UsersAndIds.addUser(user)
        }

    }


}











// var checkedInIds = ArrayList<String>()

/*
   fun getCheckedInUserFromDB(usersId: ArrayList<String>) {
       usersId.forEach {
           Repository.getCheckedInUserFromDB(it, {user -> addToCheckedInUsersList(user)})
       }
   }

   private fun addToCheckedInUsersList(user: User) {
       checkedInUsers.add(user)
   }



   fun getCheckedInUserFromDB(
       usersId: ArrayList<String>,
       onSuccess: (user: User) -> Unit
       ) {

       checkedInUsers.forEach {
           onSuccess(it)
       }

       subOnSuccess = onSuccess

       usersId.forEach {
           Repository.getCheckedInUserFromDB(it, {user -> subOnSuccess(user)})
       }

   }


   private fun subOnSuccess(user: User) {
       subOnSuccess?.let { it(user) }
       checkedInUsers.add(user)
   }*/


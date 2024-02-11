package com.example.resturantrowviews.datalevel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MyRepository {
    private val database = FirebaseDatabase.getInstance()
    private var resturantRef = database.getReference("restaurants")

    fun getResturants(): Flow<List<restItem>>{
        return callbackFlow {
            val listener = resturantRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val resturants = snapshot.children.mapNotNull { it.getValue(restItem::class.java) }
                    trySend(resturants).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
            awaitClose { resturantRef.removeEventListener(listener) }
        }
    }
}
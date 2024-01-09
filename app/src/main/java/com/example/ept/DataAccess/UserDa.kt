package com.example.ept.DataAccess

import com.example.ept.ObjectInfor.UserInfo
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


class UserDa {
    suspend fun GetByUserName(user_name: String): UserInfo? {
        try {
            var _user: UserInfo? = null

            var ref = FirebaseDatabase.getInstance().reference.child("Auth").child("Ept_User")
            for (dataSnapshot in ref.get().await().children) {
                val model = dataSnapshot.getValue<UserInfo>()
                if (model != null && model.user_Name == user_name) {
                    _user = model
                }
            }

            return _user
        } catch (e: Exception) {
            println("error: " + e.message)
            return null
        }
    }

    suspend fun GetByUserLogin(user_name: String, password: String): UserInfo? {
        try {
            var _user: UserInfo? = null

            var ref = FirebaseDatabase.getInstance().reference.child("Auth").child("Ept_User")
            for (dataSnapshot in ref.get().await().children) {
                val model = dataSnapshot.getValue<UserInfo>()
                if (model != null && model.user_Name == user_name && model.password == password) {
                    _user = model
                }
            }

            return _user
        } catch (e: Exception) {
            println("error: " + e.message)
            return null
        }
    }

    suspend fun GetMaxId(): Int {
        try {
            var _result: Int = 0

            var ref = FirebaseDatabase.getInstance().reference.child("Auth").child("Ept_User")
            for (dataSnapshot in ref.get().await().children) {
                val model = dataSnapshot.getValue<UserInfo>()
                if (model != null && model.user_Id!! > _result) {
                    _result = model.user_Id!!
                }
            }

            return _result
        } catch (e: Exception) {
            println("error: " + e.message)
            return 0
        }
    }

    suspend fun GetCountUser(): Int {
        var _result: Int = -1
        try {
            val ref = FirebaseDatabase.getInstance().reference.child("Auth").child("Ept_User")
            _result = ref.get().await().childrenCount.toInt()
        } catch (e: Exception) {
            println("error: " + e.message)
            return -1
        }
        return _result
    }

    fun SignUp(user: UserInfo): Int {
        var _result = -1
        try {
            runBlocking {
                var userExist = user.user_Name?.let { GetByUserName(it) }
                if (userExist != null && userExist?.user_Name != "") {
                    _result = -2
                } else {
                    user.user_Id = GetMaxId() + 1

//                    user.user_Id = GetCountUser() + 1
                    var ref =
                        FirebaseDatabase.getInstance().reference.child("Auth").child("Ept_User")

                    val newRef = ref.push()
                    newRef.setValue(user)

                    _result = 1
                }
            }
        } catch (e: Exception) {
            println("error: " + e.message)
            _result = -1
        }
        return _result
    }

    fun Login(user_name: String, password: String): UserInfo? {
        try {
            var _user: UserInfo? = null
            runBlocking {
                _user = GetByUserLogin(user_name, password)

                var ref = FirebaseDatabase.getInstance().reference.child("Auth").child("Ept_User")
                for (dataSnapshot in ref.get().await().children) {
                    val model = dataSnapshot.getValue<UserInfo>()
                    if (model != null && model.user_Name == user_name && model.password == password) {
                        _user = model
                    }
                }

            }
            return _user
        } catch (e: Exception) {
            println("error: " + e.message)
            return null
        }
    }
}
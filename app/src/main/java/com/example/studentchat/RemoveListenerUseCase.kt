package com.example.studentchat

import com.example.data.remote.api.FirebaseApi

class RemoveListenerUseCase {
    operator fun invoke(firebaseApi: FirebaseApi){
        firebaseApi.removeListener()
    }
}
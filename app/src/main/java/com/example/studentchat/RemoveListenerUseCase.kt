package com.example.studentchat

class RemoveListenerUseCase {
    operator fun invoke(firebaseApi: FirebaseApi){
        firebaseApi.removeListener()
    }
}
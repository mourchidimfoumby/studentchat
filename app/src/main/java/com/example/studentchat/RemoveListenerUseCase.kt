package com.example.studentchat

class RemoveListenerUseCase {
    operator fun invoke(repository: Repository){
        repository.removeListener()
    }
}
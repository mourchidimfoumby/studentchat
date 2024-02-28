package com.example.studenchat

import com.example.studenchat.Repository

class RemoveListenerUseCase {
    operator fun invoke(repository: Repository){
        repository.closeListener()
    }
}
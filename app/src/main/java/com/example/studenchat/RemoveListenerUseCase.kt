package com.example.studenchat

class RemoveListenerUseCase {
    operator fun invoke(repository: Repository){
        repository.removeListener()
    }
}
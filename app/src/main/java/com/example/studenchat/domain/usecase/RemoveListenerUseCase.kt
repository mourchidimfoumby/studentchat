package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.Repository

class RemoveListenerUseCase {
    operator fun invoke(repository: Repository){
        repository.closeListener()
    }
}
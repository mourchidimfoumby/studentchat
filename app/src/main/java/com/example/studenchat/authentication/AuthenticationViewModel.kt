class AuthenticationViewModel: ViewModel() {
    private val _logged = MutableLiveData<Boolean>()
    val logged : LiveData<Boolean> = _logged
    private val logInWithEmailPasswordUseCase = LogInWithEmailPasswordUseCase()
    private val isLoggedIn = IsLoggedInUseCase()
    private val signUp = SignUpUseCase()
    init {
        _logged.value =  isLoggedIn()
    }

    suspend fun logInWithEmailPassword(mail: String, password: String){
        logInWithEmailPasswordUseCase(mail, password)
        _logged.value = true
    }
    suspend fun signUpWithEmailPassword(user: User){
        signUp(user)
        _logged.value = true
    }
}

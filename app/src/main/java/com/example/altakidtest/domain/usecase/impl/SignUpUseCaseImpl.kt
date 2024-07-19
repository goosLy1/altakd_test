package com.example.altakidtest.domain.usecase.impl

//
//class SignUpUseCaseImpl @Inject constructor(
//    private val authenticationRepository: AuthenticationRepository
//): SignUpUseCase {
//    override suspend fun execute(input: SignUpUseCase.Input): SignUpUseCase.Output {
//        return withContext(Dispatchers.IO) {
//            val result = authenticationRepository.signUp(input.email, input.password)
//            if (result) {
//                SignUpUseCase.Output.Success
//            } else {
//                SignUpUseCase.Output.Failure
//            }
//        }
//    }
//}
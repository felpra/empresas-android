package com.example.empresas_android.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.empresas_android.Util.NetworkStatusUtil
import com.example.empresas_android.session.SessionManager
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LoginActivityViewModelTest{
    private lateinit var application: Application
    private lateinit var context: Context
    private lateinit var viewModelMock: LoginActivityViewModel
    private lateinit var networkConnectionMock: NetworkStatusUtil
    private lateinit var sessionManagerMock: SessionManager
    private val networkErrorMessage = "Você está offline. Verifique sua rede"

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        application = mockk()
        context = mockk()
        viewModelMock = mockk()
        networkConnectionMock = mockNetworkConnectionClass()
        sessionManagerMock = mockSessionManager()

    }

    @After
    fun unMockAll() {
        unmockkAll()
    }

    @Test
    fun onCLickLogin_networkIsOff_networkNotAvailableErrorIsSHow_isLoadingGone() {
        every { networkConnectionMock.getNetworkStatus() } returns false
        val loginActivityViewModel = createLoginActivityViewModelObject()
        justRun { sessionManagerMock.saveAuthClient("client") }
        justRun { sessionManagerMock.saveAuthUid("uid") }
        justRun { sessionManagerMock.saveAuthToken("token") }
        loginActivityViewModel.onClicklogin()
        loginActivityViewModel.signInError.observeForever {  }
        Truth.assertThat(loginActivityViewModel.signInError.value).isEqualTo(networkErrorMessage)
        Truth.assertThat(loginActivityViewModel.isLoading.value).isEqualTo(View.GONE)
    }

    @ExperimentalCoroutinesApi
    private fun createLoginActivityViewModelObject(): LoginActivityViewModel {
        every { application.applicationContext } returns context
        val loginActivityViewModel = LoginActivityViewModel(application)
        loginActivityViewModel.dispatcher = TestCoroutineDispatcher()
        return loginActivityViewModel
    }

    private fun mockNetworkConnectionClass(): NetworkStatusUtil {
        val connectivityManagerMock: ConnectivityManager = mockk()
        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManagerMock
        return NetworkStatusUtil(context)
    }

    private fun mockSessionManager(): SessionManager {
        val sharedPrefsMock: SharedPreferences = mockk()
        every { context.getSharedPreferences("app_teste", Context.MODE_PRIVATE) } returns sharedPrefsMock
        return SessionManager(context)
    }


}
package com.fitspeo_assignment.albumData

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fitspeo_assignment.models.Album
import com.fitspeo_assignment.repository.AlbumRepository
import com.fitspeo_assignment.retrofit.CodeApi
import com.fitspeo_assignment.util.CoroutineTestRule
import com.fitspeo_assignment.viewmodels.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
class MainViewModelTest {

    private val serviceUtil : CodeApi = mock()


    private  lateinit var mainViewModel :MainViewModel

    @Mock
    private lateinit  var albumList: List<Album>

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coRoutineTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockObserverForStates = mock<Observer<List<Album>>>()

    @Before
    fun before() {
        mainViewModel = MainViewModel(AlbumRepository(serviceUtil)).apply {
            albumsLiveData.observeForever(mockObserverForStates)
        }
    }

    @Test
    fun test_For_FetchListFromServer_ShowSuccess() {
        runTest {
            Mockito.`when`(serviceUtil.getAlbum()).thenReturn(Response.success(albumList))
            mainViewModel.albumsLiveData
            Mockito.verifyNoMoreInteractions(mockObserverForStates)
        }
    }

    @Test
    fun testThrowErrorOnListFetchFailed() {
        runTest {
            val error = IllegalStateException()

            Mockito.`when`(serviceUtil.getAlbum())
                .thenThrow(
                    error
                )
            mainViewModel.albumsLiveData
            Mockito.verifyNoMoreInteractions(mockObserverForStates)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

    private inline fun <reified T> mock(): T = mock(T::class.java)
}
package com.rxretrofit

import com.rxretrofit.viewmodel.RetroRXViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.easymock.PowerMock
import org.powermock.api.easymock.PowerMock.createPartialMock
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(RetroRXViewModel::class)
class RetroRXViewModelPrivateMethodTest {

    private var someInstance: RetroRXViewModel? = null


    @Test
    fun `test private method`(){
        val mock: RetroRXViewModel = createPartialMock(RetroRXViewModel::class.java, "onError")
      //  PowerMock.expectPrivate(RetroRXViewModelclass,"onError","")
    }
}
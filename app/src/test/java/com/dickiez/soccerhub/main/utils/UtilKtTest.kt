package com.dickiez.soccerhub.main.utils

import com.dickiez.soccerhub.utils.convertStringToLong
import com.dickiez.soccerhub.utils.getFormated
import org.junit.Test

import org.junit.Assert.*

class UtilKtTest {

    @Test
    fun getFormattedTest() {
        val date = convertStringToLong("2019-01-03", "yyyy-MM-dd")
        val formattedDate = getFormated("dd-MM-yyyy", date)
        assertEquals("03-01-2019", formattedDate)
    }
}
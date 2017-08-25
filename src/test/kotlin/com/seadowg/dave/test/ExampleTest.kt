package com.seadowg.dave.test

import com.jayway.jsonpath.JsonPath
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okio.Okio.buffer
import okio.Okio.source
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ExampleTest {

    @Test
    fun testMarcoPolo() {
        main(emptyArray())

        val requestStream = this.javaClass.getResourceAsStream("/fixtures/marco_request.json")
        val requestJSON = buffer(source(requestStream)).readUtf8()

        val request = Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), requestJSON))
                .url("http://localhost:8080")
                .build()

        val rawResponse = OkHttpClient().newCall(request).execute()

        assertThat(rawResponse.code()).isEqualTo(200)
        assertThat(rawResponse.header("Content-Type")).isEqualTo("application/json")

        val responseString = rawResponse.body()!!.string()
        val response = JsonPath.parse(responseString)

        assertThat(response.read<String>("$.speech")).isEqualTo("Polo!")
    }
}
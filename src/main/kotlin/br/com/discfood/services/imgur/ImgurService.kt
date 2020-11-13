package br.com.discfood.services.imgur

import br.com.discfood.exceptions.ImgurResponseException
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths


@Service
class ImgurService(
        @Value("\${imgur.clientId}")
        private val clientId: String? = null,
        private val mapper: ObjectMapper
) {

    private val client = OkHttpClient()

    fun upload(multipartFile: MultipartFile): ImgurResponse {
        val file = convertMultiPartToFile(multipartFile)
        val mediaType = "image/png".toMediaType();

        val requestBody: RequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(
                                mediaType,
                                file
                        )
                )
                .build()

        val request = Request.Builder()
                .header("Authorization", "Client-ID $clientId")
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build()

        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            print("error!")
            println(response.message)
            println(response.code)
            println(response.body?.string())
            throw ImgurResponseException(response.message)
        }

        return mapper.readValue(response.body?.string(), ImgurResponse::class.java)
    }


    fun convertMultiPartToFile(file: MultipartFile): File {
        val dir = "${System.getProperty("java.io.tmpdir")}/"
        val filepath: Path = Paths.get(dir, file.originalFilename)
        file.transferTo(filepath)
        return filepath.toFile()
    }

    class ImgurResponse(
            val data: ImgurImageData,
            val status: Int,
            val success: Boolean
    )

    class ImgurImageData(
            val link: String
    )


}
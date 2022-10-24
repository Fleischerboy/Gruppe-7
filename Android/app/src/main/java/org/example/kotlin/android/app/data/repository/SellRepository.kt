package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.requestsBody.SellProduct
import org.example.kotlin.android.app.data.restapi.SellApi
import org.example.kotlin.android.app.data.s3bucket.S3constants
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class SellRepository(
    private val api: SellApi
) : BaseRepository()  {

    suspend fun sellProduct(userId: String, sellProduct: SellProduct) = safeApiCall {
        api.sellProduct(userId, sellProduct)
    }



}
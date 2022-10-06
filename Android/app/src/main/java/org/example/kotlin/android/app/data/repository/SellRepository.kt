package org.example.kotlin.android.app.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.services.s3.model.ObjectMetadata
import org.example.kotlin.android.app.R
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
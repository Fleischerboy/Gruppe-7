package org.example.kotlin.android.app.ui.home.sell

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.services.s3.model.ObjectMetadata
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.data.repository.SellRepository
import org.example.kotlin.android.app.data.requestsBody.SellProduct
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.data.restapi.SellApi
import org.example.kotlin.android.app.data.s3bucket.AwsS3bucket
import org.example.kotlin.android.app.data.s3bucket.S3constants
import org.example.kotlin.android.app.databinding.FragmentSellBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class SellFragment : BaseFragment<SellViewModel, FragmentSellBinding, SellRepository>() {

    private val s3Client = AwsS3bucket().getS3Client(
        S3constants.Constants.ACCESS_ID,
        S3constants.Constants.BUCKET_NAME
    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedImage.observe(viewLifecycleOwner, Observer {uri ->
            binding.imageView.setImageURI(uri);
        })

        viewModel.productResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {

                }

                is Resource.Failure -> {
                    handleApiError(it);
                }
            }
        })

        binding.cameraBtn.setOnClickListener() {
            // TODO

        }

        binding.galleryBtn.setOnClickListener() {
            selectImageFormGallery()
        }



        binding.sellbtn.setOnClickListener() {
            val userId = runBlocking { userPreferences.getUserId.first() }
            val productTitle = binding.edProductTitle.text.toString()
            val productDescription = binding.edDescription.text.toString()
            val productPrice = binding.edProductPrice.text.toString();
            val imageKey = "user$userId$productTitle" // TODO LAGE EN UNIQUE ID VIKTIG!
            println(imageKey)
            viewModel.selectedImage.observe(viewLifecycleOwner, Observer {uri ->
               val uploadImg = uploadImageToAwsS3Bucket(uri, imageKey)
                println(uploadImg)
            })
            if (userId != null) {
                val imageUrl = s3Client.getResourceUrl("gruppe7s3bucketforandroidapp",
                    "$imageKey.png"
                )
                println("bilde url: $imageUrl")
                val sellProductInfo = SellProduct(
                    ownerId = userId.toString(),
                    title = productTitle,
                    imageUrl = imageUrl,
                    productPrice = productPrice,
                    description = productDescription,
                    address = "Oslo 1167"
                )
                viewModel.sellProduct(userId, sellProductInfo)
            }

        }
    }


    private fun selectImageFormGallery() {
        selectImageFromGalleryResult.launch("image/*")
    }


    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            println("the result after finishing picking a photo from camera roll!")
            uri?.let {
                binding.imageView.setImageURI(uri);
                viewModel.selectedImageUri(uri)


            }


        }


    private fun uploadImageToAwsS3Bucket(uri: Uri?, imageKey: String) {
        // TransferUtility provides a simple API for uploading and downloading content from Amazon S3
        val metadata = ObjectMetadata()
        metadata.setHeader("Content-Type", "image/png")
        metadata.setHeader("Content-Disposition", "inline; filename=$imageKey.png")

        val trans =
            TransferUtility.builder().context(activity?.applicationContext).s3Client(s3Client)
                .build()

        //  TransferObserver class that will notify when progress or state changes
        val observer: TransferObserver = trans.upload(
            S3constants.Constants.BUCKET_NAME,
            "$imageKey.png", uri?.let { readFileAndCreateTempFile(it) }, metadata
        )//manual storage permission
        // To keep track of the upload status, you have to set a listener
        observer.setTransferListener(object : TransferListener {
            // The onStatechanged() callback of the observer is used to notify whether the transfer was successful or failed
            override fun onStateChanged(id: Int, state: TransferState?) {
                if (state == TransferState.COMPLETED) {
                    state.toString()
                    Log.d("msg", "success")
                } else if (state == TransferState.FAILED) {
                    Log.d("msg", "fail")
                }
            }

            // while the onProgressChanged() callback is used to keep track of the number of bytes that have been transferred.
            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                if (bytesCurrent != bytesTotal) {
                    binding.imageView.setImageResource(R.drawable.ic_baseline_cloud_upload_24)
                }

            }

            override fun onError(id: Int, ex: Exception?) {
                Log.d("error", ex.toString())
            }

        })
    }


    private fun readFileAndCreateTempFile(uri: Uri): File {
        // contentResolver is used to read the file stream using the content URI.
        // contentResolver here is to get you access to the image based on the given URI
        val inputStream: InputStream? =
            activity?.applicationContext?.contentResolver?.openInputStream(uri);

        //write the stream of bytes to a file
        //File.createTempFile(prefix,suffix) creates an empty file in the default temporary-file directory,
        // using the given prefix and suffix to generate its name.
        val file = File.createTempFile("image", uri.lastPathSegment)

        // write the stream of bytes to that file.
        val outStream: OutputStream = FileOutputStream(file)
        outStream.write(inputStream?.readBytes())

        return file
    }


    override fun getViewModel(): Class<SellViewModel> = SellViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSellBinding = FragmentSellBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): SellRepository {
        val token = runBlocking { userPreferences.getAccessToken.first() }
        val api = remoteDataSource.buildServiceApi(SellApi::class.java, token)
        return SellRepository(api);
    }


}
package org.example.kotlin.android.app.ui.home.productOverview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.databinding.ActivityProductBinding
import kotlin.properties.Delegates

class ProductActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT_ID = "product_id"
    }

    private lateinit var binding: ActivityProductBinding
    private val TAG = "ProductActivity"
    var productId by Delegates.notNull<Int>()

    /*
    private val TAG = "MyActivity"

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productId = intent.getIntExtra(PRODUCT_ID, 1)
        Log.d(TAG, "sjekk verdi av productId: $productId")




        /*
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productId = intent.getIntExtra("EXTRA_PRODUCT_ID",1)
        Log.d(TAG, "sjekk verdi av productId: $productId")

         */

    }





}
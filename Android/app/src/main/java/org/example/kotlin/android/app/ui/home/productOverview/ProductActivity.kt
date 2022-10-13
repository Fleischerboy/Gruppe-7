package org.example.kotlin.android.app.ui.home.productOverview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.example.kotlin.android.app.databinding.ActivityProductBinding
import kotlin.properties.Delegates

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    var productId by Delegates.notNull<Int>()
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productId = intent.getIntExtra("EXTRA_PRODUCT_ID",1)
        Log.d(TAG, "sjekk verdi av productId: $productId")
    }





}
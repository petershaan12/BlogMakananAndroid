package com.example.bangkitfinalprojectandroid

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bangkitfinalprojectandroid.databinding.ActivityDetailActivityBinding
import com.example.bangkitfinalprojectandroid.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_BLOG = "key_blog"
    }
    private lateinit var binding: ActivityDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar

        val dataBlog = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Blog>(EXTRA_BLOG, Blog::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Blog>(EXTRA_BLOG)
        }
        if (dataBlog != null) {
            binding.tvSetName.text = dataBlog.name.toString()
            actionbar!!.title = dataBlog.name.toString()
            actionbar.setDisplayHomeAsUpEnabled(true)
            binding.tvSetDetail.text = dataBlog.description
            binding.imgItemPhoto.setImageResource(dataBlog.photo)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
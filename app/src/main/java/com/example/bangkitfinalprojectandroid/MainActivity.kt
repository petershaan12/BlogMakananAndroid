package com.example.bangkitfinalprojectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitfinalprojectandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvBlog: RecyclerView
    private val list = ArrayList<Blog>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvBlog = binding.rvBlog
        rvBlog.setHasFixedSize(true)

        list.addAll(getListBlog())
        showRecyclerList()
    }

    private fun getListBlog(): ArrayList<Blog> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listBlog = ArrayList<Blog>()
        for (i in dataName.indices) {
            val hero = Blog(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listBlog.add(hero)
        }
        return listBlog
    }

    private fun showRecyclerList() {
        rvBlog.layoutManager = LinearLayoutManager(this)
        val listBlogAdapter = ListBlogAdapter(list)
        rvBlog.adapter = listBlogAdapter

        listBlogAdapter.setOnItemClickCallback(object : ListBlogAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Blog) {
                showSelectedBlog(data)
            }
        })
    }

    private fun showSelectedBlog(blog: Blog) {
        Toast.makeText(this, "Kamu memilih " + blog.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miCompose -> {
                val about = Intent(this@MainActivity, About::class.java)
                startActivity(about)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
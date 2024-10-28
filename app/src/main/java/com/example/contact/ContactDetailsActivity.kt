package com.example.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.contact.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity: AppCompatActivity() {
    lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActivityViews()
        navigateBack()
    }

    private fun navigateBack() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean{
        onBackPressed()
        return true
    }

    private fun setActivityViews() {
        val contact = IntentCompat.getParcelableExtra(intent, Constants.CONTACT, Contact::class.java)
        contact?.let { contact ->
            binding.nameValue.text = contact.name
            binding.phoneValue.text = contact.phone
            binding.descriptionValue.text = contact.description
        }
    }
}
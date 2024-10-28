package com.example.contact

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contact.databinding.ActivityContactsBinding
import com.example.contact.databinding.ActivityMainBinding

class ContactsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private lateinit var name: String
    private lateinit var phone: String
    private lateinit var description: String
    private val contactsList = mutableListOf<Contact>()
    private var adapter = ContactsAdapter(contactsList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSaveBtnClick()
        initRV()
    }

    private fun initRV() {
        binding.rvContacts.adapter = adapter
        adapter.onItemClicked = OnItemClicked { contact -> navigateToContactDActivity(contact) }
    }

    private fun navigateToContactDActivity(contact: Contact) {
        val intent = Intent(this, ContactDetailsActivity::class.java)
        intent.putExtra(Constants.CONTACT, contact)
        startActivity(intent)
    }

    private fun onSaveBtnClick() {
        binding.saveBtn.setOnClickListener {
            if (!validateTextFields()) {
                return@setOnClickListener
            }

            name = binding.nameEt.text?.trim().toString()
            phone = binding.phoneEt.text?.trim().toString()
            description = binding.desEt.text?.trim().toString()

            val contact = Contact(name, description = description, phone = phone)
            contactsList.add(contact)
            adapter.notifyItemInserted(contactsList.size-1)
            binding.nameEt.setText(" ")
            binding.phoneEt.setText(" ")
            binding.desEt.setText(" ")

        }
    }

    private fun validateTextFields(): Boolean {
        name = binding.nameEt.text?.trim().toString()
        phone = binding.phoneEt.text?.trim().toString()
        binding.nameInp.error = validateName(name)
        binding.phoneInp.error = validatePhone(phone)

        return validateName(name) == null && validatePhone(phone) == null

    }

    fun validateName(name: String): String? {
        if (name.isEmpty()) {
            return "Required"
        }
        if (name.length < 3) {
            return "Name can not be less than 3 characters"
        }

        val namePattern = "^[a-zA-Z]+$"
        if (!name.matches(namePattern.toRegex())) {
            return "Name should only contain letters"
        }
        return null
    }

    private fun validatePhone(phone: String): String? {
        if (phone.isEmpty()) {
            return "Required"
        }
        if (phone.length < 11) {
            return "Phone can not be less than 11 digits"
        }

        val phonePattern = "^[0-9]+$"
        if (!phone.matches(phonePattern.toRegex())) {
            return "Phone should contain digits only"
        }

        return null
    }
}
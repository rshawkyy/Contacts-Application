package com.example.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.databinding.ItemContactsBinding

class ContactsAdapter(private val contactsList: MutableList<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(val itemContactsBinding: ItemContactsBinding): RecyclerView.ViewHolder(itemContactsBinding.root){
        fun bind(contact: Contact) {
            itemContactsBinding.nameTv.text = contact.name
            itemContactsBinding.phoneTv.text = contact.phone
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactsBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contactsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactsList[position]
        holder.bind(contact)
        holder.itemContactsBinding.root.setOnClickListener {
            onItemClicked?.onClick(contact)
        }
    }
    var onItemClicked:OnItemClicked?=null
}

fun interface OnItemClicked {
    fun onClick(contact: Contact)

}
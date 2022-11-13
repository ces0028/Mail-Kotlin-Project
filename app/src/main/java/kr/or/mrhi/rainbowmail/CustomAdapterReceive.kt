package kr.or.mrhi.rainbowmail

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.or.mrhi.rainbowmail.databinding.ItemEmailBinding
import kr.or.mrhi.rainbowmail.databinding.ShowEmailBinding

class CustomAdapterReceive(val dataList: MutableList<DataReceive>) : RecyclerView.Adapter<CustomAdapterReceive.CustomViewHolder>() {
    lateinit var customDialog : AlertDialog

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemEmailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)

        customViewHolder.itemView.setOnClickListener {
            val showEmailBinding = ShowEmailBinding.inflate(LayoutInflater.from(parent.context))
            val builder = AlertDialog.Builder(parent.context)
            val itemPostion = customViewHolder.adapterPosition
            val dataReceive = dataList.get(itemPostion)
            showEmailBinding.tvFromAddress.setText(dataReceive.address)
            showEmailBinding.tvToAddress.setText("ceshin0028@gmail.com")
            showEmailBinding.tvSubject.setText(dataReceive.subject)
            showEmailBinding.tvContent.setText(dataReceive.content)
            showEmailBinding.tvDateWhen.setText("2022년 ${dataReceive.date}")
            builder.setView(showEmailBinding.root)
            customDialog = builder.create()
            customDialog.show()

            showEmailBinding.btnClose.setOnClickListener {
                customDialog.dismiss()
            }
        }

        customViewHolder.itemView.setOnLongClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataReceive = dataList.get(position)
            (parent.context as MainActivity).fragmentReceive.refreshRecyclerViewDrop(dataReceive)
            true
        }
        return customViewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val dataReceive = dataList.get(position)
        binding.ivImage.setImageResource(dataReceive.image)
        binding.tvAddressFrom.setText(dataReceive.address)
        binding.tvSubject.setText(dataReceive.subject)
        binding.tvContent.setText(dataReceive.content)
        binding.tvDate.setText(dataReceive.date)
    }

    class CustomViewHolder(val binding: ItemEmailBinding) : RecyclerView.ViewHolder(binding.root)
}
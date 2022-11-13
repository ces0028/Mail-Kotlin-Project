package kr.or.mrhi.rainbowmail

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.or.mrhi.rainbowmail.databinding.ItemEmailBinding
import kr.or.mrhi.rainbowmail.databinding.ShowEmailBinding

class CustomAdapterSend(val dataList: MutableList<DataSend>) : RecyclerView.Adapter<CustomAdapterSend.CustomViewHolder>() {
    lateinit var customDialog : AlertDialog

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemEmailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)

        customViewHolder.itemView.setOnClickListener{
            val showEmailBinding = ShowEmailBinding.inflate(LayoutInflater.from(parent.context))
            val builder = AlertDialog.Builder(parent.context)
            val itemPostion = customViewHolder.adapterPosition
            val dataSend = dataList.get(itemPostion)
            showEmailBinding.tvFromAddress.setText("ceshin0028@gmail.com")
            showEmailBinding.tvToAddress.setText(dataSend.address)
            showEmailBinding.tvSubject.setText(dataSend.subject)
            showEmailBinding.tvContent.setText(dataSend.content)
            showEmailBinding.tvDateWhen.setText("2022년 ${dataSend.date}")
            builder.setView(showEmailBinding.root)
            customDialog = builder.create()
            customDialog.show()

            showEmailBinding.btnClose.setOnClickListener {
                customDialog.dismiss()
            }
        }

        customViewHolder.itemView.setOnLongClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataSend = dataList.get(position)
            (parent.context as MainActivity).fragmentSend.refreshRecyclerViewDrop(dataSend)
            true
        }
        return customViewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val dataSend = dataList.get(position)
        binding.ivImage.setImageResource(dataSend.image)
        binding.tvAddressFrom.setText(dataSend.address)
        binding.tvSubject.setText(dataSend.subject)
        binding.tvContent.setText(dataSend.content)
        binding.tvDate.setText(dataSend.date)
    }

    class CustomViewHolder(val binding: ItemEmailBinding) : RecyclerView.ViewHolder(binding.root)
}
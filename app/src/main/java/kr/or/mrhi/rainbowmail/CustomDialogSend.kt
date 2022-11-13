package kr.or.mrhi.rainbowmail

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import kr.or.mrhi.rainbowmail.databinding.CustomDialogSendBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomDialogSend(val context: Context) {
    val dialog = Dialog(context)

    @RequiresApi(Build.VERSION_CODES.O)
    fun showDialog() {
        val binding = CustomDialogSendBinding.inflate(LayoutInflater.from(context))
        binding.tvAddressFrom2.setText("ceshin0028@gmail.com")
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        binding.btnCancle.setOnClickListener {
            dialog.dismiss()
        }

        binding.btnSend.setOnClickListener {
            val address = binding.edtAddressTo.text.toString()
            val subject = binding.edtSubject.text.toString()
            val content = binding.edtContent.text.toString()
            if (address != "" && subject != "" && content != "") {
                val today = LocalDate.now()
                val date = today.format(DateTimeFormatter.ofPattern("MM월 dd일"))
                var image = R.drawable.profile_image0
                var randomNumber = (Math.random() * ((8 - 0 + 1) + 1)).toInt().toString()
                when (randomNumber) {
                    "0" -> image = R.drawable.profile_image0
                    "1" -> image = R.drawable.profile_image1
                    "2" -> image = R.drawable.profile_image2
                    "3" -> image = R.drawable.profile_image3
                    "4" -> image = R.drawable.profile_image4
                    "5" -> image = R.drawable.profile_image5
                    "6" -> image = R.drawable.profile_image6
                    "7" -> image = R.drawable.profile_image7
                    "8" -> image = R.drawable.profile_image8
                }
                var dataSend: DataSend
                dataSend = DataSend(address, subject, content, date, image)
                (context as MainActivity).fragmentSend.refreshRecyclerViewAdd(dataSend)
                dialog.dismiss()
            } else {
                Toast.makeText(context, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
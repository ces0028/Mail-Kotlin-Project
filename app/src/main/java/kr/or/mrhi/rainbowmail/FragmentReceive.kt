package kr.or.mrhi.rainbowmail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kr.or.mrhi.rainbowmail.databinding.FragmentReceiveBinding
import kr.or.mrhi.rainbowmail.databinding.FragmentSendBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FragmentReceive : Fragment() {
    lateinit var binding : FragmentReceiveBinding
    lateinit var customAdapterReceive : CustomAdapterReceive
    var dataList = mutableListOf<DataReceive>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReceiveBinding.inflate(inflater, container, false)
        val sendBinding = FragmentSendBinding.inflate(inflater, container, false)
        var today = LocalDate.now()
        dataList.add(DataReceive("apple@gmail.com", "subjectApple", "contentApple","${today.minusDays(7).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image1))
        dataList.add(DataReceive("banana@gmail.com", "sujectBanana", "contentBanana","${today.minusDays(6).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image2))
        dataList.add(DataReceive("cacao@gmail.com", "subjectCacao", "contentCacao","${today.minusDays(5).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image3))
        dataList.add(DataReceive("dewberry@gmail.com", "subjectDewberry", "contentDewberry","${today.minusDays(4).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image4))
        dataList.add(DataReceive("eggplant@gmail.com", "subejectEggplant", "contentEggplant","${today.minusDays(3).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image5))
        dataList.add(DataReceive("fennel@gmail.com", "subjectFennel", "contentFennel","${today.minusDays(2).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image6))
        dataList.add(DataReceive("grape@gmail.com", "subjectGrape", "contentGrape","${today.minusDays(1).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image7))
        dataList.add(DataReceive("huckleberry@gmail.com", "subjectHuckleberry", "contentHuckleberry","${today.minusDays(0).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image8))

        val linearLayoutManager = LinearLayoutManager(container?.context)
        linearLayoutManager.reverseLayout = true
        customAdapterReceive = CustomAdapterReceive(dataList)
        binding.receiveRecyclerView.layoutManager = linearLayoutManager
        binding.receiveRecyclerView.adapter = customAdapterReceive
        binding.receiveRecyclerView.addItemDecoration(MyDecoration(binding.root.context))

        binding.receiveFab.setOnClickListener {
            val dialog = CustomDialogSend(binding.root.context)
            dialog.showDialog()
        }
        return binding.root
    }
    fun refreshRecyclerViewDrop(dataReceive: DataReceive) {
        Toast.makeText(binding.root.context, "${dataReceive.subject} 삭제하였습니다.", Toast.LENGTH_SHORT)
            .show()
        dataList.remove(dataReceive)
        customAdapterReceive.notifyDataSetChanged()
    }
}
package kr.or.mrhi.rainbowmail

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kr.or.mrhi.rainbowmail.databinding.FragmentSendBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FragmentSend : Fragment() {
    lateinit var binding: FragmentSendBinding
    lateinit var mainActivity: MainActivity
    var dataList = mutableListOf<DataSend>()
    var customAdapterSend = CustomAdapterSend(dataList)

    override fun onAttach(context: Context) {
        mainActivity = MainActivity()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSendBinding.inflate(inflater, container, false)
        val today = LocalDate.now()
        dataList.add(DataSend("iris@gmail.com", "subjectIris", "contentIris","${today.minusDays(30).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image8))
        dataList.add(DataSend("jaborosa@gmail.com", "sujectJaborosa", "contentJaborosa","${today.minusDays(29).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image7))
        dataList.add(DataSend("kalanchoe@gmail.com", "subjectKalanchoe", "contentKalanchoe","${today.minusDays(28).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image6))
        dataList.add(DataSend("lavender@gmail.com", "subjectLavender", "contentLavender","${today.minusDays(27).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image5))
        dataList.add(DataSend("magnolia@gmail.com", "subejectMagnolia", "contentMagnolia","${today.minusDays(26).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image4))
        dataList.add(DataSend("narcissus@gmail.com", "subjectNarcissus", "contentNarcissus","${today.minusDays(25).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image3))
        dataList.add(DataSend("orchid@gmail.com", "subjectOrchid", "contentOrchid","${today.minusDays(24).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image2))
        dataList.add(DataSend("petunia@gmail.com", "subjectPetunia", "contentPetunia","${today.minusDays(23).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image1))

        val linearLayoutManager = LinearLayoutManager(container?.context)
        linearLayoutManager.reverseLayout = true
        binding.sendRecyclerView.layoutManager = linearLayoutManager
        binding.sendRecyclerView.adapter = customAdapterSend
        binding.sendRecyclerView.addItemDecoration(MyDecoration(binding.root.context))

        binding.sendFab.setOnClickListener {
            val dialog = CustomDialogSend(binding.root.context)
            dialog.showDialog()
        }
        return binding.root
    }

    fun refreshRecyclerViewAdd(dataSend: DataSend) {
        dataList.add(dataSend)
        customAdapterSend.notifyDataSetChanged()
    }

    fun refreshRecyclerViewDrop(dataSend: DataSend) {
        Toast.makeText(binding.root.context, "${dataSend.subject} 삭제하였습니다.", Toast.LENGTH_SHORT)
            .show()
        dataList.remove(dataSend)
        customAdapterSend.notifyDataSetChanged()
    }
}
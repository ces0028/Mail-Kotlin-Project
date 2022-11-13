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
import kr.or.mrhi.rainbowmail.databinding.FragmentSpamBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FragmentSpam : Fragment() {
    lateinit var binding : FragmentSpamBinding
    lateinit var customAdapterSpam: CustomAdapterSpam
    lateinit var mainActivity: MainActivity
    var dataList = mutableListOf<DataSpam>()

    override fun onAttach(context: Context) {
        mainActivity = MainActivity()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSpamBinding.inflate(inflater, container, false)
        val today = LocalDate.now()
        dataList.add(DataSpam("quesnelia@gmail.com", "[광고] subjectQuesnelia", "contentQuesnelia","${today.minusMonths(7).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image4))
        dataList.add(DataSpam("rose@gmail.com", "[광고] sujectRose", "contentRose","${today.minusMonths(6).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image5))
        dataList.add(DataSpam("saponaria@gmail.com", "[광고] subjectSaponaria", "contentSaponaria","${today.minusMonths(5).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image3))
        dataList.add(DataSpam("trillium@gmail.com", "[광고] subjectcTrillium", "contentTrillium","${today.minusMonths(4).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image6))
        dataList.add(DataSpam("ulex@gmail.com", "[광고] subejectUlex", "contentUlex","${today.minusMonths(3).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image2))
        dataList.add(DataSpam("varcissus@gmail.com", "[광고] subjectNarcissus", "contentNarcissus","${today.minusMonths(2).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image7))
        dataList.add(DataSpam("wallflower@gmail.com", "[광고] subjectWallflower", "contentWallflower","${today.minusMonths(1).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image1))
        dataList.add(DataSpam("xanthisma@gmail.com", "[광고] subjectXanthisma", "contentXanthisma","${today.minusMonths(0).format(DateTimeFormatter.ofPattern("MM월 dd일"))}", R.drawable.profile_image8))

        val linearLayoutManager = LinearLayoutManager(container?.context)
        linearLayoutManager.reverseLayout = true
        customAdapterSpam = CustomAdapterSpam(dataList)
        binding.spamRecyclerView.layoutManager = linearLayoutManager
        binding.spamRecyclerView.adapter = customAdapterSpam
        binding.spamRecyclerView.addItemDecoration(MyDecoration(binding.root.context))

        binding.receiveFab.setOnClickListener {
            val dialog = CustomDialogSend(binding.root.context)
            dialog.showDialog()
        }
        return binding.root
    }
    fun refreshRecyclerViewDrop(dataSpam: DataSpam) {
        Toast.makeText(binding.root.context, "${dataSpam.subject} 삭제하였습니다.", Toast.LENGTH_SHORT)
            .show()
        dataList.remove(dataSpam)
        customAdapterSpam.notifyDataSetChanged()
    }
}
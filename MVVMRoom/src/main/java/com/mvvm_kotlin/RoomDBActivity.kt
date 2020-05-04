package com.mvvm_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.mvvm.common.modalbottomsheetdialog.ModalCustomBottomSheet
import com.mvvm_kotlin.view.RoomDataBaseFragment
import kotlinx.android.synthetic.main.confirmation_bottom_sheet.view.*


class RoomDBActivity : AppCompatActivity(),ModalCustomBottomSheet.ModalBottomSheetListener {
    var roomDataBaseFragment : RoomDataBaseFragment  = RoomDataBaseFragment()
    lateinit var onBottomSheetItemSelected: OnBottomSheetItemSelected


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"In MVVM Room Activity",Toast.LENGTH_LONG).show()
        replaceFragmentWithNoHistory(roomDataBaseFragment, R.id.container_fragment)

    }

    fun setListener(onBottomSheetItemSelected: OnBottomSheetItemSelected){
        this.onBottomSheetItemSelected = onBottomSheetItemSelected
    }

    override fun providesBottomSheetView(view: View) {

        view.ok_tv.setOnClickListener {
            val intent = Intent("KEY")
            intent.putExtra("itemName",view.ok_tv.text.toString())
            sendBroadcast(intent)

        }
        view.cancel_tv.setOnClickListener {
            val intent = Intent("KEY")
            intent.putExtra("itemName",view.cancel_tv.text.toString())
            sendBroadcast(intent)

        }

    }




}
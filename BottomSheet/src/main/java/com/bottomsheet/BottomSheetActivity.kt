package com.bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mvvm.common.modalbottomsheetdialog.ModalBottomSheet
import com.mvvm.common.modalbottomsheetdialog.ModalCustomBottomSheet
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.bottomsheet_layout.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog.view.*

class BottomSheetActivity : AppCompatActivity(),ModalCustomBottomSheet.ModalBottomSheetListener {
    private var modalDismissWithAnimation = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet)
        display_bottom_sheet.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        display_bottom_sheet_dialog.setOnClickListener {
            if(!modalDismissWithAnimation)
            {
               // showModalBottomSheet()
                showCustomModalBottomSheet()
            }else{
                modalDismissWithAnimation = !modalDismissWithAnimation
            }
        }
        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                display_bottom_sheet.text = when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> "STATE_EXPANDED"
                    BottomSheetBehavior.STATE_COLLAPSED -> "STATE_COLLAPSED"
                    BottomSheetBehavior.STATE_DRAGGING -> "STATE_DRAGGING"
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> "STATE_HALF_EXPANDED"
                    BottomSheetBehavior.STATE_HIDDEN -> "STATE_HIDDEN"
                    BottomSheetBehavior.STATE_SETTLING -> "STATE_SETTLING"
                    else -> null
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset
            }
        }
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
    }

    private fun showModalBottomSheet() {
        val modalBottomSheet = ModalBottomSheet.newInstance(modalDismissWithAnimation)
        modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
    }

    private fun showCustomModalBottomSheet(){
        val modalBottomSheet = ModalCustomBottomSheet.newInstance(modalDismissWithAnimation,R.layout.layout_bottom_sheet_dialog)
        modalBottomSheet.show(supportFragmentManager, ModalCustomBottomSheet.TAG)
    }

    override fun providesBottomSheetView(view: View) {
        view.textView2.setOnClickListener { view.textView2.text = "Clicked" }
    }

}
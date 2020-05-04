package com.mvvm.common.modalbottomsheetdialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mvvm.common.R

class ModalCustomBottomSheet : BottomSheetDialogFragment() {

    private var dismissWithAnimation = false
    private  var layoutresource : Int?  = -1
    lateinit var modalBottomSheetView:View
    private var mListener: ModalBottomSheetListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutresource = arguments?.getInt(ARG_LAYOUT_RESOURCE, 0);
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutresource?.let{
            modalBottomSheetView = inflater.inflate(layoutresource!!, container, false)
        }

        mListener?.providesBottomSheetView(modalBottomSheetView)
        return  modalBottomSheetView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dismissWithAnimation = arguments?.getBoolean(ARG_DISMISS_WITH_ANIMATION) ?: false
        (requireDialog() as BottomSheetDialog).dismissWithAnimation = dismissWithAnimation
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        private const val ARG_DISMISS_WITH_ANIMATION = "dismiss_with_animation"
        private const val ARG_LAYOUT_RESOURCE = "layout_resource"
        fun newInstance(dismissWithAnimation: Boolean,layoutresource:Int): ModalCustomBottomSheet {
          var layout = layoutresource
            val modalBottomSheet = ModalCustomBottomSheet()
            modalBottomSheet.arguments = bundleOf(ARG_DISMISS_WITH_ANIMATION to dismissWithAnimation)
          //  modalBottomSheet.arguments =  bundleOf(ARG_LAYOUT_RESOURCE to layoutresource )
           var bundle = Bundle()
            bundle.putInt(ARG_LAYOUT_RESOURCE,layoutresource)
            bundle.putBoolean(ARG_DISMISS_WITH_ANIMATION,dismissWithAnimation)
            modalBottomSheet.arguments = bundle
            return modalBottomSheet
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is ModalBottomSheetListener) {
            context as ModalBottomSheetListener
        } else {
            throw RuntimeException(
                "$context must implement ModalBottomSheetListener"
            )
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialog.dismiss()
    }
    interface  ModalBottomSheetListener {
            fun providesBottomSheetView(view:View)
    }
}
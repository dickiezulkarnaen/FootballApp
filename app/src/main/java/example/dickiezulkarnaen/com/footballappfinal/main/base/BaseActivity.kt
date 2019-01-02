package example.dickiezulkarnaen.com.footballappfinal.main.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import example.dickiezulkarnaen.com.footballappfinal.R

open class BaseActivity : AppCompatActivity(), BaseView {

    private var progressDialog: Dialog? = null

    override fun showDialog() {
        progressDialog = Dialog(this)

        if (!progressDialog!!.isShowing) {
            progressDialog?.setContentView(R.layout.progress_dialog)
            progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog?.setCancelable(true)
            progressDialog?.setCanceledOnTouchOutside(false)
            progressDialog?.show()
        } else {
            progressDialog?.dismiss()
        }
    }

    override fun hideDialog() {
        progressDialog?.dismiss()
    }


}
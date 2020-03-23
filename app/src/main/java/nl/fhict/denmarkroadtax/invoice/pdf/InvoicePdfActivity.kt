package nl.fhict.denmarkroadtax.invoice.pdf

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.content.ContextCompat
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_invoice_pdf.*
import kotlinx.android.synthetic.main.view_internet_error_state.*
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.invoice.InvoiceViewModel
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class InvoicePdfActivity : DaggerAppCompatActivity(), InvoicePdfContract.View {

    override val invoiceViewModel: InvoiceViewModel?
        get() = intent.getParcelableExtra(INVOICE_EXTRA_KEY) as InvoiceViewModel

    @Inject
    lateinit var presenter: InvoicePdfContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_pdf)
        invoicePdfFloatingBackButton.setOnClickListener { onBackPressed() }
        internetErrorStateTryAgainButton.setOnClickListener { presenter.onErrorStateButtonClicked() }
        test()
    }

    override fun onResume() {
        super.onResume()
        //presenter.startPresenting()
    }

    override fun onPause() {
        presenter.stopPresenting()
        super.onPause()
    }

    override fun showInvoicePdf(pdfFile: File) {
        invoicePdfLoadingIndicator.visibility = View.GONE
        invoicePdfContainer.visibility = View.VISIBLE
        invoicePdfContainer.fromFile(pdfFile)
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
    }

    override fun showLoadingIndicator() {
        invoicePdfLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        invoicePdfLoadingIndicator.visibility = View.GONE
    }

    override fun showErrorState() {
        invoicePdfContainer.visibility = View.GONE
        internetErrorStateView.visibility = View.VISIBLE
    }

    override fun hideErrorState() {
        internetErrorStateView.visibility = View.GONE
    }


    private fun test() {
        PRDownloader.download(
            "https://github.github.com/training-kit/downloads/github-git-cheat-sheet.pdf",
            getRootDirPath(this),
            "harry-porter.pdf"
        )
            .build()
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    Timber.i("test")
                }

                override fun onError(error: Error?) {
                   Timber.i("hallo")
                }
            }
            )
    }

    companion object {

        private const val INVOICE_EXTRA_KEY = "intentInvoiceExtraKey"

        fun createIntent(context: Context, invoiceViewModel: InvoiceViewModel): Intent {
            return Intent(context, InvoicePdfActivity::class.java).apply {
                putExtra(INVOICE_EXTRA_KEY, invoiceViewModel)
            }
        }

        fun getRootDirPath(context: Context): String? {
            return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
                val file: File = ContextCompat.getExternalFilesDirs(context.applicationContext, null)[0]
                file.absolutePath
            } else {
                context.applicationContext.filesDir.absolutePath
            }
        }
    }
}

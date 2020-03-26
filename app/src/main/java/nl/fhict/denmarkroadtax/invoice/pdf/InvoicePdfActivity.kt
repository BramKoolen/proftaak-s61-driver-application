package nl.fhict.denmarkroadtax.invoice.pdf

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_invoice_pdf.*
import nl.fhict.denarkroadtax.domain.invoice.model.InvoicePaymentStatus
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.invoice.InvoiceViewModel


class InvoicePdfActivity : DaggerAppCompatActivity() {

    private val invoiceViewModel: InvoiceViewModel?
        get() = intent.getParcelableExtra(INVOICE_EXTRA_KEY) as InvoiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_pdf)
        invoicePdfFloatingBackButton.setOnClickListener { onBackPressed() }
        initPdfViewer()
        initPaymentButton()
    }

    private fun initPdfViewer() {
        invoicePdfView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }
        invoicePdfView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                
            }
        }
        invoicePdfView.loadUrl("https://docs.google.com/gview?embedded=true&url=${invoiceViewModel?.url}")
    }

    private fun initPaymentButton(){
        if(invoiceViewModel?.invoicePaymentStatus != InvoicePaymentStatus.PAID){
            invoicePdfFloatingPaymentButton.visibility = View.VISIBLE
        }
    }

    companion object {

        private const val INVOICE_EXTRA_KEY = "intentInvoiceExtraKey"

        fun createIntent(context: Context, invoiceViewModel: InvoiceViewModel): Intent {
            return Intent(context, InvoicePdfActivity::class.java).apply {
                putExtra(INVOICE_EXTRA_KEY, invoiceViewModel)
            }
        }
    }
}

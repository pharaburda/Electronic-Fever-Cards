package com.example.e_kartygorczkowe

import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import timber.log.Timber
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlin.experimental.and


class IdReaderActivity : AppCompatActivity() {
    private var nfcAdapter: NfcAdapter? = null
    var tagId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id_reader)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        nfcAdapter.let { nfcAdapter ->
            if (nfcAdapter == null) {
                Toast.makeText(
                    this,
                    "NFC NOT supported on this devices!",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            } else if (!nfcAdapter.isEnabled) {
                Toast.makeText(
                    this,
                    "NFC NOT Enabled!",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }

        val buttton: Button = findViewById(R.id.btn_confirm)
        buttton.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            i.putExtra("tagId",tagId)
            startActivity(i)
        }

    }

    override fun onResume() {
        super.onResume()

        val intent = intent
        val action = intent.action

        if (NfcAdapter.ACTION_TECH_DISCOVERED == action) {
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            tag?.let {
                Timber.d(tag.id.toHexString())
                tagId = tag.id.toHexString()
            }
        }
    }

    private fun ByteArray.toHexString() : String = this.joinToString("") {
        java.lang.String.format("%02x", it)
    }
}

/*
 * MIT License
 *
 * Copyright (c) 2015 Douglas Nassif Roma Junior
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.douglasjunior.bluetoothsamplekotlin

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService.OnBluetoothEventCallback
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothWriter
import kotlinx.android.synthetic.main.activity_device.*
import java.nio.ByteBuffer          // kawa
import kotlin.experimental.and

/**
 * Created by douglas on 10/04/2017.
 */
class DeviceActivity : AppCompatActivity(), OnBluetoothEventCallback, View.OnClickListener {
    private var mFab: FloatingActionButton? = null
    private var mEdRead: EditText? = null
    private var mEdWrite: EditText? = null
    private var mService: BluetoothService? = null
    private var mWriter: BluetoothWriter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        mFab = findViewById<View>(R.id.fab) as FloatingActionButton
        mFab!!.setOnClickListener(this)
        mEdRead = findViewById<View>(R.id.ed_read) as EditText
     //   ed_write.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS      // ????????????????????????????????????
        mEdWrite = findViewById<View>(R.id.ed_write) as EditText
       // ed_write.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        mService = BluetoothService.getDefaultInstance()
        mWriter = BluetoothWriter(mService)

        /* ----------------------------------------------------------
            ????????????????????????????????????????????????
            android:id="@+id/ver_btn" ??? ver_btn ?????????????????????????????????
            setOnClickListener()???????????????
        ------------------------------------------------------------- */

        // ?????????????????????
        ver_btn.setOnClickListener() {
            mWriter!!.writeln("A1C13/0")
            mWriter!!.writeln("A1C13/1")
        }

        // ???????????????
        srvon_btn.setOnClickListener() {
            mWriter!!.writeln("A1C4/1")
        }

        // ????????????
        orgn_btn.setOnClickListener() {
            mWriter!!.writeln("A1C2")
        }

        // 3?????????????????????
        step_btn.setOnClickListener() {
         //   mWriter!!.writeln("A1C20/34133")
            mWriter!!.writeln("A1C20/102399")       // 3???
            mWriter!!.writeln("A1C8")
        }

        // -3?????????????????????
        mstep_btn.setOnClickListener() {
            mWriter!!.writeln("A1C20/-102399")       // 3???
            mWriter!!.writeln("A1C8")
        }

        // ????????????????????????
        pos_btn.setOnClickListener() {
            mWriter!!.writeln("A1C12")
        }

        // ???????????????
        srvoff_btn.setOnClickListener() {
            mWriter!!.writeln("A1C4/0")
        }

        // ?????????????????????
        almclr_btn.setOnClickListener() {
            mWriter!!.writeln("A1C1")
        }

        // ????????????
        reset_btn.setOnClickListener() {
            mWriter!!.writeln("A1C0/1")
        }

        // ????????????ON
        scanon_btn.setOnClickListener() {
            mWriter!!.writeln("A1C23/3")
        }

        // ????????????OFF
        scanoff_btn.setOnClickListener() {
            mWriter!!.writeln("A1C23/0")
        }

        // ????????????
        stepmode_btn.setOnClickListener() {
            mWriter!!.writeln("A1C10/1")
        }

    }

    override fun onResume() {
        super.onResume()
        mService!!.setOnEventCallback(this)
    }

    override fun onDataRead(buffer: ByteArray, length: Int) {
      //  Log.d(TAG, "onDataRead: " + String(buffer, 0, length))

        /* -----------------------------------------------
            ???????????????????????????????????????????????????
        -------------------------------------------------- */
        mEdRead!!.append("""    < ${String(buffer, 0, length)}    """.trimIndent()) // org GC211????????????????????????????????????
        mEdRead!!.append("\r\n")        // ??????

       // mEdRead!!.setText("""    < ${String(buffer, 0, length)}    """.trimIndent())    // ?????????1??????????????????


        //  val buffer1 = byteArrayOfInts(0xA1, 0x2E, 0x38, 0xD4, 0x89, 0xC3)
     //GC   mEdRead!!.setText(buffer.contentToString(), TextView.BufferType.NORMAL)     // kawa ???????????????????????? ??????2bit OK??????10???

        //    mEdRead!!.setText(Integer.toHexString(ByteBuffer.wrap(buffer).getInt()), TextView.BufferType.NORMAL)        // ??????2bir OK

     // -------------------------------------------------------------------------------------------
     //   val len = buffer.size
     //   var stmp =""
       var stmp = buffer[1]
      //  Log.d(TAG, "onDataRead: " + stmp)
     //   mEdRead!!.setText(bytesToHex(buffer), TextView.BufferType.NORMAL)       // ????????????

        //   mEdRead!!.setText(bytesToHex(buffer), TextView.BufferType.NORMAL)       // ????????????

     //   mEdRead!!.append(bytesToHex(buffer))    // ????????????
     //   mEdRead!!.setText(bytesToHex(buffer))    // ????????????

        //    mEdRead!!.setText(Integer.toHexString(ByteBuffer.wrap(buffer).getInt()), TextView.BufferType.NORMAL)        // ??????2bir OK

     //   mEdRead!!.append(Integer.toHexString(ByteBuffer.wrap(buffer).getInt()))         // kawa ???????????????2bit???NG
     //   mEdRead!!.append(Integer.toHexString(ByteBuffer.wrap(buffer).getInt()), 0, 4)          // kawa ????????????


        //   mEdRead!!.setText(Integer.toHexString(buffer.contentToString()), TextView.BufferType.NORMAL)

        //    mEdRead!!.setText(buffer.toString(), TextView.BufferType.NORMAL)      // kawa ???????????????B@????????????
     //   mEdRead!!.append(buffer.toString())             // kawa ??????????????????B@????????????



    }



// kawa
    private val hexArray = "0123456789ABCDEF".toCharArray()
    fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = (bytes[j] and 0xFF.toByte()).toInt()

            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }






    override fun onDestroy() {
        super.onDestroy()
        mService!!.disconnect()
    }

    override fun onStatusChange(status: BluetoothStatus) {
        Log.d(TAG, "onStatusChange: $status")
    }

    override fun onDeviceName(deviceName: String) {
        Log.d(TAG, "onDeviceName: $deviceName")
    }

    override fun onToast(message: String) {
        Log.d(TAG, "onToast")
    }

    override fun onDataWrite(buffer: ByteArray) {
        Log.d(TAG, "onDataWrite")
        mEdRead!!.append("> " + String(buffer))
    }

    override fun onClick(v: View) {
        var stmp: String
        //mWriter!!.writeln(mEdWrite!!.text.toString())     // ??????????????????????????????????????????

     //   mWriter!!.writeln(mEdWrite!!.text.toString().toUpperCase())     // ???????????????????????????????????????????????????????????????

        stmp = mEdWrite!!.text.toString().toUpperCase()         // ???????????????????????????????????????
        mWriter!!.writeln(stmp)

        mEdWrite!!.setText("")            // ????????????????????????????????????
     //   mEdWrite!!.setText(stmp)
    }

    companion object {
        private const val TAG = "DeviceActivity"
    }
}
package com.example.lab2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var txtShow: TextView
    private lateinit var btnZero: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button
    private lateinit var btnStar: Button
    private lateinit var btnClear: Button

    private var phoneNumber: String = ""   // 儲存使用者輸入的數字
    private val prefix = "電話號碼:"        // 固定標籤

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 處理系統邊界
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 綁定 UI 元件
        txtShow = findViewById(R.id.txtShow)
        btnZero = findViewById(R.id.btnZero)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnStar = findViewById(R.id.btnStar)
        btnClear = findViewById(R.id.btnClear)

        // 初始顯示
        txtShow.text = prefix

        // 數字鍵與星號鍵共用監聽器
        val numberListener = View.OnClickListener { v ->
            val b = v as Button
            phoneNumber += b.text.toString()
            txtShow.text = prefix + phoneNumber
        }

        // 綁定數字與星號
        val buttons = listOf(btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnStar)
        buttons.forEach { it.setOnClickListener(numberListener) }

        // 清除鍵：短按刪掉最後一個字，長按清空
        btnClear.setOnClickListener {
            if (phoneNumber.isNotEmpty()) {
                phoneNumber = phoneNumber.dropLast(1)  // 刪掉最後一個
                txtShow.text = prefix + phoneNumber
            }
        }

        btnClear.setOnLongClickListener {
            phoneNumber = ""  // 長按直接清空數字，但保留前綴
            txtShow.text = prefix
            true
        }
    }
}

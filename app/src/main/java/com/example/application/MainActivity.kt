package com.example.application

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mResultTV: TextView
    private lateinit var mPoidsET: EditText
    private lateinit var mTailleET: EditText
    private lateinit var mCalculerBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initEvent()
    }

    private fun initView(){
        mCalculerBTN = findViewById(R.id.button)
        mPoidsET = findViewById(R.id.poids)
        mTailleET = findViewById(R.id.taille)
        mResultTV = findViewById(R.id.text2)
    }

    private fun initEvent(){
        mCalculerBTN.setOnClickListener {

            val pStr = mPoidsET.text.toString()
            val tStr = mTailleET.text.toString()

            if(pStr.isEmpty() || tStr.isEmpty()){
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else {
                val poids = pStr.toFloat()
                val taille = tStr.toFloat()

                if (taille > 0) {

                    // ✅ correction ici (cm → m)
                    val tailleMetre = taille / 100
                    val imc = poids / (tailleMetre * tailleMetre)

                    val categorie = when {
                        imc < 18.5 -> "Maigreur"
                        imc < 25 -> "Normal"
                        imc < 30 -> "Surpoids"
                        else -> "Obésité"
                    }

                    mResultTV.text = "IMC : %.2f\nStatut : %s".format(imc, categorie)

                } else {
                    Toast.makeText(this, "Taille invalide", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
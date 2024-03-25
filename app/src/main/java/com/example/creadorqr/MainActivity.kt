package com.example.creadorqr

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//Creamos nuestras variables
        var QR: ImageView     //Imagen donde se alojara el codigo QR
        var TextoQR: TextView //Texto ingresado, para lacreacion del QR
        var crear: Button     //Boton crear

        QR=findViewById(R.id.imageView)  //Referencia por id
        TextoQR=findViewById(R.id.texto) //Referencia por id
        TextoQR.setText("")              //Vaciamos nuestro campo de texto

        //Referenciamos boton y creamos evento Listener
        crear=findViewById(R.id.button)
        crear.setOnClickListener(View.OnClickListener {

            //Obtenemos el texto y eliminamos los espacios en blanco del inicio y el final
        val informacion= TextoQR.text.toString().trim()

        if (informacion.isEmpty()){                //se comprueba que no este vacio
                Toast.makeText( this,  "Inserta algun dato", Toast.LENGTH_SHORT).show()
        }else {
            val writer = QRCodeWriter()         //LLamamos a la libreria QR writer
            try {
                val bitMatrix = writer.encode(informacion, BarcodeFormat.QR_CODE, 512, 512) //Configuramos el formato y enviamos nuestro texto
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565) //creamos el mapa de bits
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE) //se pintan los pixeles uno por uno, en base al mapa de bits
                    }
                }
                QR.setImageBitmap(bmp)       //mostramos codigo QR
            } catch (e: WriterException) {
                e.printStackTrace() // Entregamos los trabajos a meses sin intereses y el proyecto hasta diciembre si bien nos va

            }
        }

        })
    }
}
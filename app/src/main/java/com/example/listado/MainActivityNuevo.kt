package com.example.listado

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listado.databinding.ActivityMainBinding
import com.example.listado.databinding.ActivityMainNuevoBinding

class MainActivityNuevo : AppCompatActivity() {
    private lateinit var binding: ActivityMainNuevoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //abrimos la conexion
        var dbconex= DBHelperAlumno(this)

        val intento2 = Intent(this,MainActivity::class.java)



        binding.btnGuardar.setOnClickListener {
            //abrimos la bd para escribir
            var db = dbconex.writableDatabase


            val txtNom = binding.txtNombre.text.toString()
            val txtCue = binding.txtCuenta.text.toString()
            val txtCorr = binding.txtCorreo.text.toString()
            val txtImg = binding.txtImage.text.toString()

            /*val sql="INSERT INTO alumnos (nombre, cuenta, correo, imagen) VALUES('$txtNom','$txtCue','$txtNom')"
        val status =db.execSQL(sql)*/

            //alternativa 2
            val newReg = ContentValues()
            newReg.put("nombre", txtNom) //nombre de la base de datos, de la interfaz
            newReg.put("cuenta", txtCue) //nombre de la base de datos, de la interfaz
            newReg.put("correo", txtCorr) //nombre de la base de datos, de la interfaz
            newReg.put("imagen", txtImg) //nombre de la base de datos, de la interfaz

            val res = db.insert("alumnos", null, newReg)

            if (res.toInt()==-1){
                Toast.makeText(this, "Error al insertar ",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Registro insertado con Exito",Toast.LENGTH_LONG).show()
                startActivity(intento2)
            }
            db.close()
        }

        dbconex.close()

    }
}
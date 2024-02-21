package com.example.recyclerview

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    //data class where we store the data for recycler view
    lateinit var Recycler_Contact_Adapter1 : Recycler_Contact_Adapter
    var name = ""
    var address = ""
    data class Mydata(val name: String,val address: String)
    //arraylist of the above data class
    val data_arrrayList = ArrayList<Mydata>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv_view)
        val floating_buttpn = findViewById<FloatingActionButton>(R.id.floating_button)


        //layoutManager is used to define the layout of a recyclerview(linearlayout,gridlayout and staggeredlayout)
        rv.layoutManager = LinearLayoutManager(this)
        data_arrrayList.add(Mydata("zakir","1111"))
        data_arrrayList.add(Mydata("sahil","22222"))
        data_arrrayList.add(Mydata("adnan","3333"))
        data_arrrayList.add(Mydata("aamar","44444"))
        data_arrrayList.add(Mydata("jamid","5555"))
        data_arrrayList.add(Mydata("danush","66666"))
        data_arrrayList.add(Mydata("kkdar","7777"))
        data_arrrayList.add(Mydata("basit","99999"))
        data_arrrayList.add(Mydata("kashmir","1212"))
        data_arrrayList.add(Mydata("ffff","76555"))
        data_arrrayList.add(Mydata("hloooo","8743"))

        Recycler_Contact_Adapter1 = Recycler_Contact_Adapter(this,data_arrrayList)
        rv.adapter = Recycler_Contact_Adapter1

        //OPPENING THE custom_layout AS DIALOG
        floating_buttpn.setOnClickListener {
            //OPPENING THE custom_layout AS DIALOG
          var dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_layout)

            val edittext_name = dialog.findViewById<EditText>(R.id.edittext_name)
            val editText_address = dialog.findViewById<EditText>(R.id.edittext_address)
            val add_deleat_button = dialog.findViewById<Button>(R.id.add_deleate_button)

            //ADDING DATA IN RECYCLERVIEW ON CLICKING ADD BUTTON
            add_deleat_button.setOnClickListener {

//             name = edittext_name.text.toString()
//                 address = editText_address.text.toString()

                if(!edittext_name.text.toString().equals("") && !editText_address.text.toString().equals("")){
                    name = edittext_name.text.toString()
                    address = editText_address.text.toString()
                    data_arrrayList.add(Mydata(name,address))
                    //WILL NOTIFY THE RECYCLERVIEW THA ITEM HAS BEEN INSERTED
                    Recycler_Contact_Adapter1.notifyItemInserted(data_arrrayList.size - 1)
                    //WILL SCROLL AT THIS POSITION
                    rv.scrollToPosition(data_arrrayList.size -1)

                }else{
                    Toast.makeText(this, "please give the name and address", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

        }


    }
}
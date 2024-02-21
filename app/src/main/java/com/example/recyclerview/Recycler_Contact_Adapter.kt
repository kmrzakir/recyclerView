package com.example.recyclerview

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class Recycler_Contact_Adapter(val contex : Context,val data : ArrayList<MainActivity.Mydata>) : RecyclerView.Adapter<Recycler_Contact_Adapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name_textView : TextView = itemView.findViewById(R.id.textview_name)
        val address_textview : TextView = itemView.findViewById(R.id.textview_address)
        val custom_rv_view : LinearLayout = itemView.findViewById(R.id.custom_rv_view)
    }
//THIS METHOD GENENRATED A VIEW =====================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(contex)
        val view = inflate.inflate(R.layout.contact_layout_for_recyclerview,parent,false)
        return MyViewHolder(view)
    }
//THIS METHOD IS USED FOR ATTACHING DATA
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      //holder has all the id find in MyViewHolder
        holder.name_textView.setText(data.get(position).name)
        holder.address_textview.setText(data.get(position).address)

    //SETTING CLICK LISTENNER ON A VIEW CLICKED BY USER
    holder.custom_rv_view.setOnClickListener {
        val dialog1 = Dialog(contex)
        dialog1.setContentView(R.layout.custom_layout)
        val edittext_name = dialog1.findViewById<EditText>(R.id.edittext_name)
        val editText_address = dialog1.findViewById<EditText>(R.id.edittext_address)
        val add_deleat_button = dialog1.findViewById<Button>(R.id.add_deleate_button)
        val text_view = dialog1.findViewById<TextView>(R.id.add_or_deleat_message)
        add_deleat_button.text = "Update"
        text_view.text = "Update context"
        edittext_name.setText(data.get(position).name)
        editText_address.setText(data.get(position).address)
        add_deleat_button.setOnClickListener {
            var name = ""
            var address = ""

            if(!edittext_name.text.toString().equals("") && !editText_address.text.toString().equals("")){
                name = edittext_name.text.toString()
                address = editText_address.text.toString()
            }else{
                Toast.makeText(contex, "please enter the name and address ", Toast.LENGTH_LONG).show()
            }
            data.set(position,MainActivity.Mydata(name,address))
            notifyItemChanged(position)
            dialog1.dismiss()
        }
     dialog1.show()
    }

    //DELEATING ITEM ON LONG PRESS
    holder.custom_rv_view.setOnLongClickListener {
        val aleartDialog = AlertDialog.Builder(contex)
        aleartDialog.setTitle("DELEART ITEM")
        aleartDialog.setMessage("Do you want to deleat this message")
        aleartDialog.setPositiveButton("ok"){dialog,which ->
            data.removeAt(position)
            notifyItemRemoved(position)

        }
        aleartDialog.setNegativeButton("NO"){dialogg,which ->
            dialogg.dismiss()

        }
     aleartDialog.show()
        true //RETURNS TRUE TO INDICATE THA LONGPREESONCLICK HAS BEEN USED
    }

    }
    //COUNTS THE SIZE OF A DATA
    override fun getItemCount(): Int {
       return data.size;
    }

}
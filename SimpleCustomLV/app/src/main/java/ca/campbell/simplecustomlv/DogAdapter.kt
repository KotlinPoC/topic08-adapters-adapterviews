package ca.campbell.simplecustomlv

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import android.content.ContentValues.TAG

/**
 * Custom adapter for an AdapterView
 *
 * @author PMCampbell
 * @version today
 */
class DogAdapter(private val context: Context, internal var listDogs: Array<String>, internal var listIdDogs: IntArray) : BaseAdapter() {
    private val TAG = "DOGADAPTR"
    internal var inflater: LayoutInflater

    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return listDogs.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder {
        internal var tv: TextView? = null
        internal var iv: ImageView? = null
    }

    // create a new layout for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var vh = ViewHolder()
        var row = convertView
        if (convertView == null) {
            row = inflater.inflate(R.layout.custom_item, null)

            vh.tv = row!!.findViewById<View>(R.id.itemTV) as TextView
            vh.iv = row.findViewById<View>(R.id.itemIV) as ImageView
            vh.tv!!.text = listDogs[position]
            vh.iv!!.setImageResource(listIdDogs[position])
            // use the View.tag to ccmmunicate the viewholder we used, when it
            // is no longer new (see else)
            row.tag = vh
            // can set the listener here if I want to
            Log.d(TAG, "Adapter getView() new view: position " + position + " dog " + listDogs[position])
        } else {
            vh = convertView.tag as ViewHolder
            // spotted by Brian Doherty 2017-03-06
            // we are re using the view but changing the content
            vh.tv!!.text = listDogs[position]
            vh.iv!!.setImageResource(listIdDogs[position])
            Log.d(TAG, "Adapter getView() reuse view: position " + position + " dog " + listDogs[position])

        }

        // note it is better to set a clicklistener on the AdapterView
        row!!.setOnClickListener {
            Toast.makeText(context, "Clicked " + listDogs[position], Toast.LENGTH_SHORT).show()
            val i = Intent(context, DogActivity::class.java)
            i.putExtra("dog_name", listDogs[position])
            i.putExtra("dog_image", listIdDogs[position])
            context.startActivity(i)
        }
        return row
    }

}  // DogAdapter

package ca.campbell.simplecustomlv

/**
 *
 * This code implements a simple CustomAdapter for a list view
 * in order to display an small image and a text in the list
 *
 * some code from http://developer.android.com/guide/topics/ui/layout/gridview.html
 *
 * @author P.M.Campbell
 * @version today
 */
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val names = resources.getStringArray(R.array.dog_names)
        val ids = intArrayOf(R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1)

        val lv = findViewById<View>(R.id.list) as ListView
        lv.adapter = DogAdapter(this, names, ids)
    }


}

package ca.campbell.adapterwcontextmenu

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
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    internal lateinit  var names: Array<String>
    internal var ids =
            intArrayOf(R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1, R.drawable.beeker)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = resources.getStringArray(R.array.dog_names)

        val lv = findViewById<View>(R.id.list) as ListView
        lv.adapter = DogAdapter(this, names, ids)

        registerForContextMenu(findViewById(R.id.list))
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, info: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, info)
        menuInflater.inflate(R.menu.context_menu, menu)
        // could use menu.add() instead of xml

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.activity_dog -> {
                startActivity(Intent(applicationContext, DogActivity::class.java)
                        .putExtra("dog_name", names[info.position]).putExtra("dog_image", ids[info.position]))
                return true
            }
            R.id.toast_dog -> {
                Toast.makeText(this@MainActivity, "Clicked " + names[info.position], Toast.LENGTH_SHORT).show()

                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }


    inner class DogAdapter(private val context: Context, internal var listDogs: Array<String>, internal var listIdDogs: IntArray) : BaseAdapter() {
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
                // use the tag to ccmmunicate the viewholder we used, when it
                // is no longer new (see else)
                row.tag = vh
                // can set the listener here if I want to
            } else {
                vh = convertView.tag as ViewHolder
                // spotted by Brian Doherty 2017-03-06
                // we are re using the view but changing the content
                vh.tv!!.text = listDogs[position]
                vh.iv!!.setImageResource(listIdDogs[position])
            }
            /*
            row.setOnClickListener(  new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Clicked " + listDogs[position], Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DogActivity.class);
                    i.putExtra("dog_name", listDogs[position]);
                    i.putExtra("dog_image", listIdDogs[position]);
                    context.startActivity(i);
                }
            });
  */
            return row as View
        }

    }  // DogAdapter

} // MainActivity
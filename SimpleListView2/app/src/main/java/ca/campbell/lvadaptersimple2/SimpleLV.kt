package ca.campbell.lvadaptersimple2

import java.util.ArrayList

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast

/*
 * Example 2 for ListView
 * ListView is an AbsListView, AbsListView is an AdapterView,  AdapterView is a ViewGroup, ViewGroup is a View
 * ArrayAdapter is a BaseAdapter
 *
 * Second  example of a ListView, populated by an array created in line,
 * the ListView responds to item click and long click events, we dynamically modify the data
 */
class SimpleLV : Activity() {
    private var data: MutableList<String>? = null
    private var counter = 0
    private var lv: ListView? = null
    private var addbutton1: Button? = null
    private var addbutton2: Button? = null
    private var aa: ArrayAdapter<String>? = null

    /*
	 * addUsingAdapter() this listener is attached to a button, we add data to
	 * the ListView using the adapter.
	 */
    private val addUsingAdapter = OnClickListener {
        counter++
        /*
			 * using the adapter we add an element we could also clear the list
			 * ArrayAdapter.clear() set it to a new Collecton
			 * ArrayAdapter.addAll(Collection)
			 */
        aa!!.add("via adapter $counter")
        /*
			 * ArrayAdapter.notifyDataSetChanged() refreshes the data by telling
			 * the observer of the adapter that the contents have changed, so
			 * that the observer (ListView in this case) can decide what to
			 * refresh.
			 *
			 * call after ArrayAdapter.add() .insert() .remove() .clear()
			 *
			 * Here we explicitly call notifyDataSetChanged() we could also have
			 * the adapter automatically call it
			 * ArrayAdapter.setNotifyOnChange()
			 */
        aa!!.notifyDataSetChanged()
        Toast.makeText(applicationContext,
                "List size post adapter add " + data!!.size,
                Toast.LENGTH_SHORT).show()
    } // addUsingAdapter()
    /*
	 * addUsingAdapter() this listener is attached to a button, we add data to
	 * the ListView using the list
	 */
    private val addUsingList = OnClickListener {
        counter++
        // using the list we add an element
        data!!.add("via list $counter")
        /*
			 * ListView.invalidateViews() rebuilds and redraws all child item
			 * views, whether or not they have changed, so it is more expensive
			 * note you can have 100 items in your list but fit only 10 views on
			 * your display.
			 */
        lv!!.invalidateViews()
        Toast.makeText(applicationContext,
                "List size post list  add " + data!!.size,
                Toast.LENGTH_SHORT).show()
    } // addUsingList

    /*
	 * delOnClick() this listener is attached to the ListView when an item is
	 * clicked we delete the item in the list
	 */
    private val delOnClick = OnItemClickListener { parent, view, position, id ->
        /*
			 * delete the list item, we could also use ArrayAdapter.remove(T
			 * object);
			 */
        data!!.removeAt(position)
        // notify the observer
        aa!!.notifyDataSetChanged()
    } // delOnClick
    /*
	 * longClick() this listener is attached to the ListView when an item is
	 * held on long click we do not modify the data we Toast the position
	 */

    private val longClick = OnItemLongClickListener { parent, view, position, id ->
        Toast.makeText(applicationContext,
                "Long Click position $position", Toast.LENGTH_SHORT)
                .show()
        /*
			 * data has not changed so no need for lv.invalidateViews(); or
			 * aa.notifyDataSetChanged();
			 */
        // Return true to consume the click event.
        true
    } // longClick

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
		 * setContentView(): inflate the main layout (UI) from activity_main.xml
		 * inflation: xml -> ojbect(s)
		 */
        setContentView(R.layout.activity_simple_lv)

        // create and populate our array
        data = ArrayList()
        data!!.add("ONE")
        data!!.add("two")
        data!!.add("three")
        data!!.add("biscuit")

        // get a reference to the listview object
        lv = findViewById<View>(R.id.list) as ListView

        // set the item click Listener for the ListView
        lv!!.onItemClickListener = delOnClick

        // set the item long click listener for the ListView
        lv!!.onItemLongClickListener = longClick

        /*
		 * ListView, GridView, GalleryView containers are types of ArrayAdapter
		 * controls ArrayAdapter is a BaseAdapter, Adapters bind data to View
		 * objects
		 *
		 * the layout xml template is builtin: R.Layout.simple_list_item_1
		 *
		 * documentation:
		 * http://developer.android.com/reference/android/R.layout.html
		 *
		 * source:
		 * https://github.com/android/platform_frameworks_base/blob/master
		 * /core/res/layout/simple_list_item_1.xml
		 *
		 * the data in this case is a String array that we just created
		 */
        aa = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, data!!)
        /*
		 * setAdapter() sets the data & layout behind the ListView
		 */
        lv!!.adapter = aa

        // add listeners for our two buttons
        addbutton1 = findViewById<View>(R.id.addUsingAdapter) as Button
        addbutton1!!.setOnClickListener(addUsingAdapter)

        addbutton2 = findViewById<View>(R.id.addUsingList) as Button
        addbutton2!!.setOnClickListener(addUsingList)

    } // onCreate()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_simple_lv, menu)
        return true
    }
    // you may swap to  the COUNTRIES array defined below it is a bigger array
    // than countries_array

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> {
                createShowDialogue()
                return true
            }
            else -> return false
        }
    } // onOptionsItemSelected

    /*
	 * Create and show an alert dialog for help information
	 */
    private fun createShowDialogue() {

        // 1. Instantiate an AlertDialog.Builder with its constructor
        val builder = AlertDialog.Builder(this)

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.help)
                .setCancelable(true)
        // 3. Get the AlertDialog from create()
        val dialog = builder.create()
        // 4.  set Buttons, not used here
        // 5.  Display the dialog
        dialog.show()
    } // createDialogue
} // SimpleLV class

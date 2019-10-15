package ca.campbell.recyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Toast

import java.util.ArrayList

/**
 * Simple example of using a RecyclerView and a Custom Adatper
 * original code from
 * https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
 *
 * @author P Campbell (some mods only)
 * @version 2018-10-15
 *
 * TODO:  add an image to the RecyclerView items
 */

class MainActivity : AppCompatActivity(), CustomRecyclerViewAdapter.ItemClickListener {

    protected var countNew = 0
    internal lateinit var adapter: CustomRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data to populate the RecyclerView with
        val animalNames = ArrayList<String>()
        animalNames.add("Horse")
        animalNames.add("Cow")
        animalNames.add("Camel")
        animalNames.add("Sheep")
        animalNames.add("Goat")
        animalNames.add("Elephant")

        // set up the RecyclerView
        val recyclerView = findViewById(R.id.rvAnimals) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomRecyclerViewAdapter(this, animalNames)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
    }

    override fun onItemClick(view: View, position: Int) {
        adapter.addItem(adapter.getItem(position) + " " + countNew++)
        //adapter.notifyItemInserted(position);
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "You clicked "
                + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show()
    }
}

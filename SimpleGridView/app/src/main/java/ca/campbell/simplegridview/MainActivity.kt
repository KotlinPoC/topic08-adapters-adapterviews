package ca.campbell.simplegridview

/**
 * Original Code from http://developer.android.com/guide/topics/ui/layout/gridview.html
 *
 * for ImageView scaling see
 * https://robots.thoughtbot.com/android-imageview-scaletype-a-visual-guide
 * and
 * https://developer.android.com/reference/android/widget/ImageView.ScaleType
 */
// TODO: the grid is  clunky looking you may want to improve it
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener

class MainActivity : Activity() {

    private val handleClick = OnItemClickListener { parent, v, position, id -> Toast.makeText(this@MainActivity, "" + position, Toast.LENGTH_SHORT).show() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gridview = findViewById<View>(R.id.gridview) as GridView
        gridview.adapter = ImageAdapter(this)
        gridview.onItemClickListener = handleClick

    }

    /*
 * First, this implements some required methods inherited from BaseAdapter.
 * The constructor and getCount() are self-explanatory.
 * Normally, getItem(int) should return the actual object at the specified position in the adapter,
 * but it's ignored for this example.
 * Likewise, getItemId(int) should return the row id of the item, but it's not needed here.
 *
 * The first method necessary is getView(). This method creates a new View for each image added to the ImageAdapter.
 *  When this is called, a View is passed in, which is normally a recycled object
 *  (at least after this has been called once), so there's a check to see if the object is null.
 *  If it is null, an ImageView is instantiated and configured with desired properties
 *  for the image presentation:
 *  -setLayoutParams(ViewGroup.LayoutParams) sets the height and width for the View
 *  	this ensures that, no matter the size of the drawable, each image is resized and
 *  	cropped to fit in these dimensions, as appropriate.
 *  -setScaleType(ImageView.ScaleType) declares that images should be cropped toward the center (if necessary).
 *  -setPadding(int, int, int, int) defines the padding for all sides.
 *  	(Note that, if the images have different aspect-ratios, then less padding will cause more cropping
 *  	of the image if it does not match the dimensions given to the ImageView.)
 *
 *  If the View passed to getView() is not null, then the local ImageView is initialized with
 *  the recycled View object.  At the end of the getView() method, the position integer passed into
 *  the method is used to select an image from the mThumbIds array, which is set as the image resource
 *  for the ImageView.  All that's left is to define the mThumbIds array of drawable resources.
 */
    inner class ImageAdapter(private val context: Context) : BaseAdapter() {

        // references to our images
        private val mThumbIds = arrayOf(R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7)

        override fun getCount(): Int {
            return mThumbIds.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        // create a new ImageView for each item referenced by the Adapter
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val imageView: ImageView
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = ImageView(context)
                // TODO: this is  clunky looking you may want to improve it
                imageView.setLayoutParams(GridView@AbsListView.LayoutParams(200,200))
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                imageView.setPadding(10, 10, 10, 10)
                imageView.setBackgroundColor(resources.getColor(android.R.color.holo_purple))
            } else {
                imageView = convertView as ImageView
            }

            imageView.setImageResource(mThumbIds[position])
            return imageView
        }
    }
}

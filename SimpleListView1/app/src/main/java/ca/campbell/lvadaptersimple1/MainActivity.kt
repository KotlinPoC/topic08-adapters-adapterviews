package ca.campbell.lvadaptersimple1

import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.widget.AdapterView.OnItemClickListener

/*
 * Example 1 for ListView
 * ListView is an AbsListView, AbsListView is an AdapterView,  AdapterView is a ViewGroup, ViewGroup is a View
 *
 * Simplest example of a ListView, populated by an array from resources res/values/strings.xml
 * the ListView responds to item click events.
 *
 * (commented code to populate  from an inline static array.)
 *  @ P Campbell
 *  @ 2017-03-01
 *
 */
class MainActivity : Activity() {
    private var lv: ListView? = null
    internal var aa: ArrayAdapter<String>? = null
    internal var aal: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
		 * setContentView(): inflate the main layout (UI) from activity_main.xml
		 * inflation: xml -> ojbect(s)
		 */
        setContentView(R.layout.activity_main)

        // the objects are now created, get a reference to the listview object
        lv = findViewById(R.id.list) as ListView

        // use the strings.xml array
        val countries = resources.getStringArray(
                R.array.countries_array)

        /* If I were going to manipulate this array I would use a List
		 * but since I am not I save a step
		List<String> countriesList = new ArrayList<String>(Arrays.asList(countries));
		 */

        /*
		 * ListView, GridView, GalleryView containers are types of ArrayAdapter
		 * controls ArrayAdapter is a BaseAdapter, Adapters bind data to View
		 * objects
		 *
		 * the layout xml is defined in res/layout/list_item.xml (template for
		 * filling each row) (used when instantiating views)
		 *
		 * the data in this case is a String array that was originally defined
		 * in xml
		 */

        aa = ArrayAdapter(this,
                R.layout.list_item, countries)
        /*
		 * setAdapter() sets the data & layout behind the ListView
		 */
        lv!!.adapter = aa


        /*
		 * Once the adapter is assigned you may want an item click to be an
		 * event that it responded to so you must set up a listener for the
		 * ListView
		 */
        lv!!.onItemClickListener = OnItemClickListener { _ , view, position, id ->
            // When clicked, show a toast with the current TextView text
            Toast.makeText(applicationContext,
                    (view as TextView).text, Toast.LENGTH_SHORT).show()
            Log.d(TAG, view.text as String)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    // you may swap to  the COUNTRIES array defined below it is a bigger array
    // than countries_array

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.swap -> {
                if (lv!!.adapter === aa) {
                    if (aal == null) {
                        aal = ArrayAdapter(this, R.layout.list_item,
                                COUNTRIES)
                    }
                    lv!!.adapter = aal
                } else
                    lv!!.adapter = aa
                return true
            }
            else -> return false
        }
    }

    companion object {
        internal val COUNTRIES = arrayOf("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland", "Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas", "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Virgin Islands", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe")
        private val TAG = "SIMPLELV"
    }
}

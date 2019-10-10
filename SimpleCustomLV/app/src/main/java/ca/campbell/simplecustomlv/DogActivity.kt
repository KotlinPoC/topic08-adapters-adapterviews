package ca.campbell.simplecustomlv

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DogActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)
        val name: String
        val id: Int

        val tv = findViewById(R.id.tv) as TextView
        val iv = findViewById(R.id.iv) as ImageView

        if (intent.hasExtra("dog_name")) {
            tv.text = intent.extras!!.getString("dog_name")
        } else {
            tv.text = "no name"
            tv.setTextColor(Color.CYAN)
        }

        if (intent.hasExtra("dog_image")) {
            iv.setImageResource(intent.extras!!.getInt("dog_image"))
        } else {
            iv.setImageResource(R.drawable.ic_launcher)

        }
    }
}

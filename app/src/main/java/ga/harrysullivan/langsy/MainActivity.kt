package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        textView4.text = unidecode("Здравствуйте")

        main_next_button.setOnClickListener {
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}

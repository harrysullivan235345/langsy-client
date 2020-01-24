package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.utils.Insults
import kotlinx.android.synthetic.main.activity_main.*
import net.gcardone.junidecode.Junidecode.unidecode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        textView4.text = unidecode("Здравствуйте")

        val insult = Insults(application).getInsult()
        nextButton.text = "begin you ${insult}"

        nextButton.setOnClickListener {
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}

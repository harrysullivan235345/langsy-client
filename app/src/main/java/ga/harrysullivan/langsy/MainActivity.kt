package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.data.Spontaneous
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.view_models.SpontaneousViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mSpontaneousViewModel: SpontaneousViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spontaneousFactory = InjectorUtils.provideSpontaneousViewModelFactory()
        mSpontaneousViewModel = ViewModelProviders.of(this, spontaneousFactory)
            .get(SpontaneousViewModel::class.java)

        mSpontaneousViewModel.setSpontaneous(Spontaneous("", false))


        main_next_button.setOnClickListener {
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}

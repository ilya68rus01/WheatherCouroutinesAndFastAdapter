package khrushchev.ilya.wheathercouroutinesandfastadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import khrushchev.ilya.wheathercouroutinesandfastadapter.databinding.ActivityMainBinding
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.ui.DayliWhetherFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, DayliWhetherFragment())
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
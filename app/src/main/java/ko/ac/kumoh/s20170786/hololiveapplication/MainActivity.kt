package ko.ac.kumoh.s20170786.hololiveapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ko.ac.kumoh.s20170786.hololiveapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
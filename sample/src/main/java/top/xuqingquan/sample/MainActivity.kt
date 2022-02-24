package top.xuqingquan.sample

import android.os.Bundle
import top.xuqingquan.mvvm.view.activity.BaseActivity
import top.xuqingquan.sample.databinding.ActivityMainBinding
import top.xuqingquan.utils.toast

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    private val TAG = "MainActivity"

    override fun getVM() = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel!!.test.observe(this) {
            toast("testhh--->${it}")
        }
    }
}

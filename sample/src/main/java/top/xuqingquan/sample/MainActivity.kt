package top.xuqingquan.sample

import android.os.Bundle
import androidx.lifecycle.Observer
import top.xuqingquan.mvvm.view.activity.BaseActivity
import top.xuqingquan.sample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getVM() = MainViewModel()

    override fun getLayoutId() = R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        viewModel!!.test.observe(this, Observer {
            binding.data = it
        })
    }
}

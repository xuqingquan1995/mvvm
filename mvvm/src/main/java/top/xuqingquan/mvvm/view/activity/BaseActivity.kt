package top.xuqingquan.mvvm.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import top.xuqingquan.base.view.activity.SimpleActivity
import top.xuqingquan.mvvm.viewModel.BaseViewModel

import javax.inject.Inject

/**
 * Created by 许清泉 on 2019-04-21 11:04
 */
abstract class BaseActivity<VM : BaseViewModel, VDB : ViewDataBinding> : SimpleActivity() {

    @Inject
    lateinit var viewModel: VM
    protected lateinit var binding: VDB

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
        binding.unbind()
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        setContentView(binding.root)
        initData(savedInstanceState)
    }
}

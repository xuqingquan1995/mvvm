package top.xuqingquan.mvvm.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import top.xuqingquan.base.view.activity.SimpleActivity
import top.xuqingquan.mvvm.viewModel.BaseViewModel

/**
 * Created by 许清泉 on 2019-04-21 11:04
 */
abstract class BaseActivity<VM : BaseViewModel<*>, VDB : ViewDataBinding> : SimpleActivity() {

    protected var viewModel: VM? = null
        get() {
            if (field == null) {
                field = getVM()
            }
            return field
        }
        private set

    protected lateinit var binding: VDB

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        setContentView(binding.root)
        initData(savedInstanceState)
    }

    protected abstract fun getVM(): VM?
}

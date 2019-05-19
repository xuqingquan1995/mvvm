package top.xuqingquan.mvvm.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import top.xuqingquan.base.view.fragment.SimpleFragment
import top.xuqingquan.mvvm.viewModel.BaseViewModel
import javax.inject.Inject

/**
 * Created by 许清泉 on 2019-04-21 00:40
 */
abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : SimpleFragment() {
    @Inject
    lateinit var viewModel: VM
    protected lateinit var binding: VDB


    final override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun initView(view: View) {}

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}
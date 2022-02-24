package top.xuqingquan.mvvm.view.fragment

import androidx.viewbinding.ViewBinding
import top.xuqingquan.mvvm.viewModel.BaseViewModel

/**
 * Created by 许清泉 on 2019-04-21 13:33
 * 子类覆写[BaseLazyLoadFragment]lazyLoadData可快速实现Fragment懒加载
 */
@Suppress("DEPRECATION")
@Deprecated("懒加载使用其他方式")
abstract class BaseLazyLoadFragment<VM : BaseViewModel<*>, VB : ViewBinding> : BaseFragment<VM, VB>() {
    private var isViewCreated: Boolean = false // 界面是否已创建完成
    private var isVisibleToUser: Boolean = false // 是否对用户可见
    private var isDataLoaded: Boolean = false // 数据是否已请求

    /**
     * ViewPager场景下，判断父fragment是否可见
     */
    private val isParentVisible: Boolean
        get() {
            val fragment = parentFragment
            return fragment == null || fragment is BaseLazyLoadFragment<*, *> && fragment.isVisibleToUser
        }

    /**
     * 第一次可见时触发调用,此处实现具体的数据请求逻辑
     */
    protected abstract fun lazyLoadData()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        tryLoadData()
    }

    /**
     * 保证在initData后触发
     */
    override fun onResume() {
        super.onResume()
        isViewCreated = true
        tryLoadData()
    }

    /**
     * ViewPager场景下，当前fragment可见时，如果其子fragment也可见，则让子fragment请求数据
     */
    private fun dispatchParentVisibleState() {
        val fragmentManager = childFragmentManager
        val fragments = fragmentManager.fragments
        if (fragments.isEmpty()) {
            return
        }
        for (child in fragments) {
            if (child is BaseLazyLoadFragment<*, *> && child.isVisibleToUser) {
                child.tryLoadData()
            }
        }
    }

    private fun tryLoadData() {
        if (isViewCreated && isVisibleToUser && isParentVisible && !isDataLoaded) {
            lazyLoadData()
            isDataLoaded = true
            //通知子Fragment请求数据
            dispatchParentVisibleState()
        }
    }

}

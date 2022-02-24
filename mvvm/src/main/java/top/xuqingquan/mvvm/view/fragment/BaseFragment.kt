package top.xuqingquan.mvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import top.xuqingquan.base.view.fragment.SimpleFragment
import top.xuqingquan.mvvm.viewModel.BaseViewModel
import top.xuqingquan.utils.ReflectUtils
import java.lang.reflect.ParameterizedType

/**
 * Created by 许清泉 on 2019-04-21 00:40
 */
abstract class BaseFragment<VM : BaseViewModel<*>, VB : ViewBinding> : SimpleFragment() {

    protected var viewModel: VM? = null
        get() {
            if (field == null) {
                field = getVM()
            }
            return field
        }
        private set

    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = this::class.java.genericSuperclass as ParameterizedType
        val viewBinding = type.actualTypeArguments[1] as Class<*>
        binding = ReflectUtils.reflect(viewBinding).method("inflate",layoutInflater).get()
        return binding.root
    }

    protected abstract fun getVM(): VM?
}
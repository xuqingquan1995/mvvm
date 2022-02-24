package top.xuqingquan.mvvm.view.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import top.xuqingquan.base.view.activity.SimpleActivity
import top.xuqingquan.mvvm.viewModel.BaseViewModel
import top.xuqingquan.utils.ReflectUtils
import java.lang.reflect.ParameterizedType

/**
 * Created by 许清泉 on 2019-04-21 11:04
 */
abstract class BaseActivity<VM : BaseViewModel<*>, VB : ViewBinding> : SimpleActivity() {


    protected var viewModel: VM? = null
        get() {
            if (field == null) {
                field = getVM()
            }
            return field
        }
        private set

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = this::class.java.genericSuperclass as ParameterizedType
        val viewBinding = type.actualTypeArguments[1] as Class<*>
        binding = ReflectUtils.reflect(viewBinding).method("inflate",layoutInflater).get()
        setContentView(binding.root)
    }

    protected abstract fun getVM(): VM?
}

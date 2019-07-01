package top.xuqingquan.sample

import androidx.lifecycle.MutableLiveData
import top.xuqingquan.mvvm.viewModel.BaseViewModel

/**
 * Created by 许清泉 on 2019-07-01 15:23
 */
class MainViewModel : BaseViewModel<MainRepository>() {
    override fun getIRepository() = MainRepository()

    val test = MutableLiveData<String>()

    init {
        repository?.test()
        test.postValue("hhhhhhh")
    }

}
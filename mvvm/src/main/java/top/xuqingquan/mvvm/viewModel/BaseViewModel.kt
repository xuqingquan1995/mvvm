package top.xuqingquan.mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import top.xuqingquan.mvvm.model.repository.BaseRepository

/**
 * Created by 许清泉 on 2019-04-20 22:40
 */
abstract class BaseViewModel<R : BaseRepository> : ViewModel() {

    protected var repository: R? = null
        get() {
            if (field == null) {
                field = getIRepository()
            }
            return field
        }
        private set

    override fun onCleared() {
        super.onCleared()
        repository?.onDestroy()
        viewModelScope.cancel()
    }

    protected abstract fun getIRepository(): R?

}
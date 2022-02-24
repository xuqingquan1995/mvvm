package top.xuqingquan.sample

import android.util.Log
import kotlinx.coroutines.Dispatchers
import top.xuqingquan.mvvm.extension.launch
import top.xuqingquan.mvvm.model.repository.BaseRepository
import top.xuqingquan.utils.Timber

/**
 * Created by 许清泉 on 2019-07-01 15:27
 */
class MainRepository : BaseRepository() {
    private val TAG = "MainRepository"
    fun test() {
        Timber.d("mRepositoryManager==null====>${mRepositoryManager == null}")
        launch(context = Dispatchers.Main, tryBlock = {
            val l=listOf("123","345")
            Log.e(TAG, "test: ${l[3]}")
        }, catchBlock = {e ->
            Log.e(TAG, "test: catchBlock")
            e.printStackTrace()
        }, finallyBlock = {
            Log.e(TAG, "test: finallyBlock")
        })
    }

}
package top.xuqingquan.mvvm.model.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import top.xuqingquan.app.ScaffoldConfig
import top.xuqingquan.integration.IRepositoryManager

/**
 * Created by 许清泉 on 2022-02-24 16:21
 */
open class BaseRepository {
    internal val scope =CoroutineScope(SupervisorJob() + Dispatchers.Default)

    protected var mRepositoryManager: IRepositoryManager? = ScaffoldConfig.getRepositoryManager()

    open fun onDestroy() {
        scope.cancel()
        mRepositoryManager = null
    }
}
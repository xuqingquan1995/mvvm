package top.xuqingquan.sample

import top.xuqingquan.base.model.repository.BaseRepository
import top.xuqingquan.utils.Timber

/**
 * Created by 许清泉 on 2019-07-01 15:27
 */
class MainRepository : BaseRepository() {

    fun test() {
        Timber.d("mRepositoryManager==null====>${mRepositoryManager == null}")
    }

}
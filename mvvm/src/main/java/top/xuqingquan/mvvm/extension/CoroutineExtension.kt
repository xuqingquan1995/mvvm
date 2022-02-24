package top.xuqingquan.mvvm.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import top.xuqingquan.app.ScaffoldConfig
import top.xuqingquan.mvvm.model.repository.BaseRepository
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Created by 许清泉 on 2022-02-24 16:28
 */

fun <T> ViewModel.launch(
    context: CoroutineContext = viewModelScope.coroutineContext,
    tryBlock: suspend CoroutineScope.() -> T
): Job {
    return launch(context, tryBlock, {}, {})
}

fun <T> ViewModel.launch(
    context: CoroutineContext = viewModelScope.coroutineContext,
    tryBlock: suspend CoroutineScope.() -> T,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit = {},
    finallyBlock: suspend CoroutineScope.() -> Unit = {}
): Job {
    return viewModelScope.launch(context) {
        try {
            tryBlock()
        } catch (e: Throwable) {
            if (ScaffoldConfig.debug()) {
                e.printStackTrace()
            }
            catchBlock(e)
        } finally {
            finallyBlock()
        }
    }
}

fun <T> BaseRepository.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    tryBlock: suspend CoroutineScope.() -> T
): Job {
    return launch(context, tryBlock, {}, {})
}

fun <T> BaseRepository.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    tryBlock: suspend CoroutineScope.() -> T,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit = {},
    finallyBlock: suspend CoroutineScope.() -> Unit = {}
): Job {
    return scope.launch(context) {
        try {
            tryBlock()
        } catch (e: Throwable) {
            if (ScaffoldConfig.debug()) {
                e.printStackTrace()
            }
            catchBlock(e)
        } finally {
            finallyBlock()
        }
    }
}

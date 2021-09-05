package ows.kotlinstudy.movierankapp.repository

// 0 Default
// 1 Success
// 2 Fail
sealed class ResponseResult<T>(
    val data : T? = null,
    val code : Int = 100
){
    class Success<T>(data: T?, code: Int) : ResponseResult<T>(data, code)
    class Fail<T>(data: T?, code: Int) : ResponseResult<T>(data,code)
}

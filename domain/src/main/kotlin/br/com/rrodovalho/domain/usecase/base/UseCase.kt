package br.com.rrodovalho.domain.usecase.base

import java.util.concurrent.CancellationException

abstract class UseCase<in RV : RequestValues, T> {

    private var requestValues: RV? = null

    open fun addRequestValues(requestValues: RV?): UseCase<RV, T> {
        this.requestValues = requestValues
        return this
    }

    open suspend fun run(): T {
        try{
            return executeUC(requestValues)
        }
        catch (e: CancellationException){
            throw e
        }
        catch (e: Throwable){
            throw e
        }
    }

    protected abstract suspend fun executeUC(requestValues: RV? = null): T

}
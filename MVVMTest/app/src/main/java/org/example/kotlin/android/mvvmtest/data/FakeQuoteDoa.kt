package org.example.kotlin.android.mvvmtest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
class FakeQuoteDoa {

    private val quoteList = mutableListOf<Quote>();

    // Live data can be observed for changes.
    // when this is updated it will trigger the observers for this quotes mutable live data in all of the classes
    // which are observing it.
    private val quotes = MutableLiveData<List<Quote>>();


    init {
        quotes.value = quoteList
    }

    fun addQuote(quote: Quote) {
        quoteList.add(quote)
        quotes.value = quoteList
    }

    // as Live data, bc we dont want to be able to change this value of the live data outside
    fun getQuotes() = quotes as LiveData<List<Quote>>
}
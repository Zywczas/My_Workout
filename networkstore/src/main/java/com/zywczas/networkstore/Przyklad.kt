package com.zywczas.networkstore

import com.zywczas.networkstore.przykladowe.BookFromProto
import com.zywczas.networkstore.przykladowe.BookServiceGrpc
import io.grpc.ManagedChannelBuilder

class Przyklad {

    private val ksiazka = BookFromProto.Book.newBuilder().apply {
        isbn = 1L
        title = "Harry Potter"
        author = "nie pamietam"
    }.build()

    private val bookRequest = BookFromProto.GetBookRequest.newBuilder().setIsbn(2L).build()

    private val jakisPort = 123;
    private val managedChannel = ManagedChannelBuilder.forAddress("adres servera", jakisPort).build()
    private val blockingStub = BookServiceGrpc.newBlockingStub(managedChannel)
        //todo dokonczyc

}
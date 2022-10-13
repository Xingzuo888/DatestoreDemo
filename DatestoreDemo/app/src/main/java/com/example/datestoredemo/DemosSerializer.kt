package com.example.datestoredemo

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object DemosSerializer:Serializer<Demo.Demos> {
    override val defaultValue: Demo.Demos
        get() = Demo.Demos.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Demo.Demos {
        try {
            return Demo.Demos.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.",e)
        }
    }

    override suspend fun writeTo(t: Demo.Demos, output: OutputStream) {
        return t.writeTo(output)
    }
}
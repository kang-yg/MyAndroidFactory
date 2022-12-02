package com.example.studyandroid.view.DataStore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.studyandroid.MyPersonProto
import java.io.InputStream
import java.io.OutputStream

object MyPersonProtoSerializer : Serializer<MyPersonProto> {
    override val defaultValue: MyPersonProto
        get() = MyPersonProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): MyPersonProto {
        try {
            return MyPersonProto.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: MyPersonProto, output: OutputStream) = t.writeTo(output)
}
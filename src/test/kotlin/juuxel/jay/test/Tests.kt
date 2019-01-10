/* This file is a part of the Jay project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/Jay
 */
package juuxel.jay.test

import juuxel.jay.Deserializer
import juuxel.jay.JsonObject
import juuxel.jay.JsonValue
import juuxel.jay.Parser
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isNotNull
import strikt.assertions.isTrue

class Tests {
    @Test fun obj() {
         val equal = Parser.parseObj(data) == JsonObject(mapOf(
             "a" to 10,
             "b" to false
         ))

        expectThat(equal).isTrue()
    }

    @Test fun customDeserializer() {
        val obj = Parser.parse(data2, ObjDeserializer)

        expectThat(obj)
            .isNotNull()
            .assert("test a") { it.a == 10 }
            .assert("test b") { !it.b }
            .assert("test c") { it.c == listOf(1, 2) }
            .assert("test d") { it.d == D(false) }
    }

    object ObjDeserializer : Deserializer<Obj> {
        override fun deserialize(value: JsonValue): Obj? {
            val obj = value.asObject ?: return null
            val a = obj.int("a") ?: return null
            val b = obj.boolean("b") ?: return null
            val c = obj.list("c") ?: return null
            val d = obj.other("d", DDeserializer) ?: return null

            return Obj(a, b, c, d)
        }
    }

    object DDeserializer : Deserializer<D> {
        override fun deserialize(value: JsonValue) = value.asObject?.boolean("a1")?.let(::D)
    }

    data class Obj(val a: Int, val b: Boolean, val c: List<Any>, val d: D)
    data class D(val a1: Boolean)

    companion object {
        private const val data = """{"a":10,"b":false}"""
        private const val data2 = """{"a":10,"b":false,"c":[1,2],"d":{"a1":false}}"""
    }
}

/* This file is a part of the Jay project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/Jay
 */
package juuxel.jay

interface JsonValue {
    val asObject: JsonObject? get() = null
    val asList: List<Any>? get() = null
    val asInt: Int? get() = null
    val asDouble: Double? get() = null
    val asBoolean: Boolean? get() = null
    val asString: String? get() = null

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun wrap(value: Any): JsonValue =
            if (value is Map<*, *>) JsonObject(value as Map<String, Any>)
            else JsonValueImpl(value)
    }
}

internal class JsonValueImpl(private val value: Any) : JsonValue {
    @Suppress("UNCHECKED_CAST")
    override val asList = this.value as? List<Any>
    override val asInt = this.value as? Int
    override val asDouble = this.value as? Double
    override val asBoolean = this.value as? Boolean
    override val asString = this.value as? String
}

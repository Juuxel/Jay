/* This file is a part of the Jay project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/Jay
 */
package juuxel.jay

inline class JsonObject(private val map: Map<String, Any>) : JsonValue {
    val asMap get() = map
    override val asObject get() = this

    fun boolean(key: String): Boolean? = map[key] as? Boolean
    fun int(key: String): Int? = map[key] as? Int
    fun double(key: String): Double? = map[key] as? Double
    fun string(key: String): String? = map[key]?.toString()
    @Suppress("UNCHECKED_CAST")
    fun list(key: String): List<Any>? = map[key] as? List<Any>
    fun value(key: String): JsonValue? = map[key]?.let(JsonValue.Companion::wrap)

    @Suppress("UNCHECKED_CAST")
    fun obj(key: String): JsonObject? = (map[key] as? Map<String, Any>)?.let(::JsonObject)

    fun <T> other(key: String, deserializer: Deserializer<T>): T? =
        value(key)?.let(deserializer::deserialize)
}

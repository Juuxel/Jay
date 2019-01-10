/* This file is a part of the Jay project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/Jay
 */
package juuxel.jay

import a2u.tn.utils.json.JsonParser

object Parser {
    fun parseObj(data: String): JsonObject = JsonObject(JsonParser.parse(data))
    fun <T> parse(data: String, deserializer: Deserializer<T>): T? =
        deserializer.deserialize(parseObj(data))
}

/* This file is a part of the Jay project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/Jay
 */
package juuxel.jay

interface Deserializer<out T> {
    fun deserialize(value: JsonValue): T?
}

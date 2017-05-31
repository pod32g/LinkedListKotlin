/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 */

data class Node<T> (var data:T, var next:Node<T>? = null, var previous:Node<T>? = null)

class LinkedList<T> {
    private var head:Node<T>? = null

    var isEmpty:Boolean = this.head == null

    fun getFirst():Node<T>? = this.head

    fun getLast():Node<T>? {
        var node = this.head
        if (node != null) {
            while (node?.next != null) {
                node = node?.next
            }
            return node
        }
        return null
    }

    fun size():Int {
        var node = this.head
        if (node != null) {
            var i:Int = 1
            while(node?.next != null) {
                node = node?.next
                i += 1
            }
            return i
        }
        return 0
    }

    fun atIndex(index:Int): Node<T>? {
        if (index >= 0) {
            var node = this.head
            var i = index
            while (node != null) {
                if (i == 0) return node
                i -= 1
                node = node.next
            }
        }
        return null
    }

    fun append(value:T) {
        var newNode = Node(data=value)
        var lastNode = this.getLast()

        if (lastNode != null) {
            newNode.previous = lastNode
            lastNode.next = newNode
        } else {
            this.head = newNode
        }
    }

    fun delete() {
        this.head = null
    }

    private fun removeNode(node: Node<T>):T {
        val prev = node.previous
        val next = node.next

        if (prev != null) {
            prev.next = next
        } else {
            head = next
        }
        next?.previous = prev

        node.previous = null
        node.next = null

        return node.data
    }

    fun deleteIndex(index: Int):T? {
        val node = this.atIndex(index)
        if (node != null) {
            return removeNode(node)
        } else {
            return null
        }
    }

    override fun toString(): String {
        var s = "["
        var node = head
        while (node != null) {
            s += "${node.data}"
            node = node.next
            if (node != null) { s += ", " }
        }
        return s + "]"
    }
}

fun main(args:Array<String>) {
    var ll = LinkedList<String>()

    ll.append("John")
    println(ll)
    ll.append("Carl")
    println(ll)
    ll.append("Zack")
    println(ll)
    ll.append("Tim")
    println(ll)
    ll.append("Steve")
    println(ll)
    ll.append("Peter")
    println(ll)

    print("\n\n")

    println("first item: ${ll.getFirst()?.data}")
    println("last item: ${ll.getLast()?.data}")
    println("second item: ${ll.getFirst()?.next?.data}")
    println("penultimate item: ${ll.getLast()?.previous?.data}")

    println("\n4th item: ${ll.atIndex(3)?.data}")

    println("\nthe list has ${ll.size()} items")
}

package com.redmechax00.vkcup2022qualification.data.favorites

data class FavoriteItem(
    val itemId: Int = 0,
    val itemText: String = "",
    var itemIsChecked: Boolean = false,
) {
    override fun equals(other: Any?): Boolean {
        other as FavoriteItem
        if (itemId != other.itemId) return false
        if (itemText != other.itemText) return false
        return true
    }

    override fun hashCode(): Int {
        var result = itemId.hashCode()
        result = 31 * result + itemText.hashCode()
        result = 31 * result + itemIsChecked.hashCode()
        return result
    }

    override fun toString(): String {
        return itemText
    }
}


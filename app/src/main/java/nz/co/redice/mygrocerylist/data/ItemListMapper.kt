package nz.co.redice.mygrocerylist.data

import nz.co.redice.mygrocerylist.domain.Item

class ItemListMapper {

    fun mapEntityToDbModel(item: Item): ItemDbModel {
        return ItemDbModel(
            id = item.id,
            name = item.name,
            count = item.count,
            enabled = item.enabled
        )
    }

    fun mapDbModelToEntity(itemDbModel: ItemDbModel): Item {
        return Item(
            id = itemDbModel.id,
            name = itemDbModel.name,
            count = itemDbModel.count,
            enabled = itemDbModel.enabled
        )
    }

    fun mapListDbModelToListEntity(list: List<ItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
package com.example.daggerhilt.retrofit

import com.example.daggerhilt.model.Blog
import com.example.daggerhilt.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(): EntityMapper<BlogNetworkEntity, Blog> {

    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {

        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image =  entity.image,
            category = entity.category
        )

    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {

        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image =  domainModel.image,
            category = domainModel.category
        )

    }


    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog>{
        return entities.map { mapFromEntity(it) }
    }

}
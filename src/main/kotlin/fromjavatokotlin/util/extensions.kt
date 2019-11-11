package fromjavatokotlin.util

//import fromjavatokotlin.constant.ID_FIELD
//import fromjavatokotlin.model.Identifiable
//import org.springframework.data.mongodb.core.MongoOperations
//import org.springframework.data.mongodb.core.query.Criteria
//import org.springframework.data.mongodb.core.query.Query
//import org.springframework.data.mongodb.core.query.Update
//import java.util.*
//
//val <T> Optional<T>.asNullable
//    get() = this.orElse(null)
//
//inline fun <reified T: Identifiable> MongoOperations.updateById(id: String, update: Update.() -> Unit) {
//    updateFirst(
//            Query(Criteria.where(ID_FIELD).`is`(id)),
//            Update().apply(update),
//            T::class.java
//    )
//}
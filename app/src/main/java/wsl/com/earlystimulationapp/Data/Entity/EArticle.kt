package wsl.com.earlystimulationapp.Data.Entity

data class EArticle(

    val id: String,
    val type: String?,
    val name: String?,
    val min_age: Int?,
    val max_age: Int?,
    val picture: String?,
    val area_id: Int?,
    val short_description: String?

)
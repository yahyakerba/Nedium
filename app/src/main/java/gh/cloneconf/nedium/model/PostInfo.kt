package gh.cloneconf.nedium.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostInfo (
    @PrimaryKey
    val id : String,

    @ColumnInfo
    val title : String,

    @ColumnInfo
    val desc : String
)
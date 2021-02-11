package `in`.madera.kotlin.Sqlite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserRole (
    @PrimaryKey var id: Int,
    var role: String?,
    var est_admin: Boolean
)
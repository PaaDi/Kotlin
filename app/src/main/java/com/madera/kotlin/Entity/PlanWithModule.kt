package com.madera.kotlin.Entity

import androidx.room.*

data class PlanWithModule(
        @Embedded val plan: Plan,
        @Relation(
                parentColumn = "idPlan",
                entityColumn = "idModule",
                associateBy = Junction(PlanModuleCrossRef::class)
        )
        val module: List<Module>
)


package com.madera.kotlin.Entity

import androidx.room.*

data class ModuleWithPlan(
        @Embedded val module: Module,
        @Relation(
                parentColumn = "idModule",
                entityColumn = "idPlan",
                associateBy = Junction(PlanModuleCrossRef::class)
        )
        val plan: List<Plan>
)


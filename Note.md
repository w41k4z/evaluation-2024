- When creating a new quote, the finition_type_majoration, finition_type_id and house_types_id must be denormalized together (do not forget it in the logic layer of the application)

- When creating a new quote details, the works_id and work_details_id are denormalized instead of just using a house_construction_details_id (this is because in the future, a work_details_id may not be part of a house_construction_detail)

- Deletion should be avoid, instead of deleting a house_construction_details for example, just update its default_quantity to 0

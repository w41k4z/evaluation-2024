package proj.eval.app.entity;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "house_construction_details")
public class HouseConstructionDetail
  extends Repository<HouseConstructionDetail, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "house_types_id")
  private Long houseTypesId;

  @Column(name = "work_details_id")
  private Long workDetailsId;

  @Column(name = "default_quantity")
  private Double defaultQuantity;
}

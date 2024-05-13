package proj.eval.app.entity;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "house_type_details")
public class HouseTypeDetail extends Repository<HouseTypeDetail, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "house_types_id")
  private Long houseTypesId;

  @Column
  private String name;

  @Column
  private Integer quantity;
}

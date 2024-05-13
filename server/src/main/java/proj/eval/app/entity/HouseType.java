package proj.eval.app.entity;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.annotation.relationship.Join;
import proj.w41k4z.orm.annotation.relationship.OneToMany;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "house_types")
public class HouseType extends Repository<HouseType, Long> {

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private Double duration;

  @Column(name = "total_price")
  private Double totalPrice;

  @OneToMany
  @Join(inverseJoinColumn = "house_types_id")
  private HouseTypeDetail[] houseTypeDetails;
}

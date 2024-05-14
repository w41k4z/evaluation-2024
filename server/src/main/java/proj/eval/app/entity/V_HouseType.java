package proj.eval.app.entity;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.annotation.ReadOnly;
import proj.w41k4z.orm.annotation.relationship.Join;
import proj.w41k4z.orm.annotation.relationship.OneToMany;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "v_house_types")
@ReadOnly
public class V_HouseType extends Repository<V_HouseType, Long> {

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private Long duration; // in days

  @Column(name = "total_price")
  private Double totalPrice;

  @OneToMany
  @Join(inverseJoinColumn = "house_types_id")
  private HouseTypeDetail[] houseTypeDetails;
}
